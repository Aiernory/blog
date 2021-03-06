package ren.aiernory.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import ren.aiernory.blog.enums.ErrorCodeEnum;
import ren.aiernory.blog.resultMessage.ErrorMessage;
import ren.aiernory.blog.model.Publish;
import ren.aiernory.blog.model.User;
import ren.aiernory.blog.service.PublishService;
import ren.aiernory.blog.service.SortService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.PublishController
 * @date 2020/2/20 13:30
 * @Description:
 */
@Controller
@RequestMapping("/publish")
public class PublishController {
    
    @Autowired
    private PublishService publishService;
    @Autowired
    private SortService sortService;
    
    
    @Value("${localMusicPath}")
    private String localPath;
    
    @Resource(name = "sortJson")
    private String sortJson;
    
    
    @GetMapping
    public ModelAndView publish(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("publish");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new ErrorMessage(ErrorCodeEnum.NOT_LOGIN_PUBLISH);
        }
        modelAndView.addObject("sortJson", sortJson);
        
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editor(@PathVariable(name = "id") Integer id,HttpServletRequest request, ModelAndView modelAndView) {
        HttpSession session = request.getSession();
        
        Publish article = publishService.getByIdWithAuthor(id);
        modelAndView.addObject("action", "edit");
        modelAndView.addObject("article", article);
        modelAndView.setViewName("publish");
        return modelAndView;
    }
    
    
    
    @PostMapping
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("test-editormd-html-code") String descriptionCode,
            @RequestParam("test-editormd-markdown-doc") String descriptionDoc,
            @RequestParam("music") String music,
            @RequestParam(value = "sort",defaultValue = "\"id\":1") String putSort,
            HttpServletRequest request,
            
            Model model
    ) {
        Publish publish = new Publish();
        //sort只要id和title。这里只要id。重新认真学习正则去...学成归来（滑稽
        User user = (User) request.getSession().getAttribute("user");
        
    
        //music解析处理  0没有 1网易云 2本地
        if (music != null && music.length() != 0) {
            String[] musicMsg = music.split("&");
            String type = musicMsg[0].split("=")[1];
            String musicId = musicMsg[1].split("=")[1];
            publish.setMusicContent(musicId);
            if ("local".equals(type)) {
                publish.setMusicStatus(2);
            } else if ("netease".equals(type)) {
                publish.setMusicStatus(1);
            }
        } else {
            publish.setMusicContent("");
            publish.setMusicStatus(0);
        }
        //有默认值，空就不管了
        publish.setTitle(title);
        publish.setCreator(user.getId());
        publish.setDescriptionCode(descriptionCode);
        publish.setDescriptionDoc(descriptionDoc);
     
        if (publishService.addPublish(publish) != 1) {
            System.out.println("插入出错");
        }else {
            //      [{"id":3,"title":"B","children":[]},{"id":4,"title":"C","children":[]}]
            //正则获取id，加入库中
            int articleId = publish.getId();
            List<Integer> sorts=new ArrayList<>();
            
            String regex="(?<=\"id\":)\\d+";
            Pattern pattern =Pattern.compile(regex);
            //putSort 的默认值指定为id:1
            Matcher matcher=pattern.matcher(putSort);
            
            while (matcher.find()){
                int s = Integer.parseInt(matcher.group());
                sorts.add(s);
            }
            sortService.addArticleSort(articleId, sorts);
        }
        return "redirect:/home";
    }
    
    
    //更新操作
    @PostMapping("/edit/{id}")
    public String editor(@PathVariable(name = "id") Integer id,
                         @RequestParam("title") String title,
                         @RequestParam("test-editormd-html-code") String descriptionCode,
                         @RequestParam("test-editormd-markdown-doc") String descriptionDoc,
                         @RequestParam("music") String music,
                         HttpServletRequest request) {
        HttpSession session = request.getSession();
        Publish article = publishService.getByIdWithAuthor(id);
        if (article == null) {
            throw new ErrorMessage(ErrorCodeEnum.QUESTION_UPDATE_FAILED);
        }
        User user = (User) session.getAttribute("user");
        if (user == null || !article.getCreator().equals(user.getId())) {
            throw new ErrorMessage(ErrorCodeEnum.QUESTION_UPDATE_NOT_AUTHOR);
        }
        article.setTitle(title);
        article.setDescriptionCode(descriptionCode);
        article.setDescriptionDoc(descriptionDoc);
        //music解析处理  0没有 1网易云 2本地
        if (music != null && music.length() != 0) {
            String[] musicMsg = music.split("&");
            String type = musicMsg[0].split("=")[1];
            String musicId = musicMsg[1].split("=")[1];
            article.setMusicContent(musicId);
            if ("local".equals(type)) {
                article.setMusicStatus(2);
            } else if ("netease".equals(type)) {
                article.setMusicStatus(1);
            }
        } else {
            article.setMusicContent("");
            article.setMusicStatus(0);
        }
        
        if (publishService.update(article) != 1) {
            throw new ErrorMessage(ErrorCodeEnum.QUESTION_UPDATE_FAILED);
        }
        return "redirect:/home";
    }
    
    @ResponseBody
    @GetMapping("/music")
    public String[] loadLocalMusic(Model model) {
        //列出指定文件夹的所有音乐文件，列表形式发送，前端处理成为可选择的标签
        File file = new File(localPath);
        String[] strings = file.list();
        return strings;
    }
    
    @ResponseBody
    @PostMapping("/music")
    public String uploadMusic(HttpServletRequest request) {
        MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = mulRequest.getFile("music");
        
        if (file.isEmpty()) {
            return "上传失败，文件内容为空...";
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(localPath, fileName);
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败...你没有错，都是代码的错";
    }
}
