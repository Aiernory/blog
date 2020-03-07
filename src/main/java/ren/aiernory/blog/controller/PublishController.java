package ren.aiernory.blog.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ren.aiernory.blog.model.Publish;
import ren.aiernory.blog.model.User;
import ren.aiernory.blog.service.PublishService;
import ren.aiernory.blog.tool.CookieLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

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
    private CookieLogin cookieLogin;
    @Autowired
    private PublishService publishService;
    @Value("${localMusicPath}")
    private String path;
    
    @GetMapping
    public String publish() {
        return "publish";
    }
    
    @PostMapping
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("test-editormd-html-code") String descriptionCode,
            @RequestParam("test-editormd-markdown-doc") String descriptionDoc,
            @RequestParam("music") String music,
            HttpServletRequest request,
            Model model
    ) {
        Publish publish = new Publish();
        User user = (User) request.getSession().getAttribute("user");
        //登录检验
        if (user == null) {
            //检测cookie
            cookieLogin.cookieVerify(request);
            user = (User) request.getSession().getAttribute("user");
        }
        if (user == null) {
            model.addAttribute("error", "用户未登录!");
            return "publish";
        }
        //music解析处理  0没有 1网易云 2本地
        if (music != null) {
            String[] musicMsg = music.split("&");
            String type = musicMsg[0].split("=")[1];
            String musicId = musicMsg[1].split("=")[1];
            publish.setMusicContent(musicId);
            if ("local".equals(type)) {
                publish.setMusicStatus(2);
            } else if ("netease".equals(type)) {
                publish.setMusicStatus(1);
            } else {
                publish.setMusicStatus(0);//这个错误如果出了默默吞掉
            }
        }
        //有默认值，空就不管了
        publish.setTitle(title);
        publish.setCreator(user.getId());
        publish.setDescriptionCode(descriptionCode);
        publish.setDescriptionDoc(descriptionDoc);
        if (publishService.addPublish(publish) != 1) {
            System.out.println("插入出错");
        }
        return "index";
        
    }
    
    @ResponseBody
    @PostMapping("/localMusic")
    public String[] loadLoaclMusic(Model model) {
        //列出指定文件夹的所有音乐文件，列表形式发送，前端处理成为可选择的标签
        File file = new File(path);
        String[] strings = file.list();
        return strings;
    }
    
    @ResponseBody
    @PostMapping("/uploadMusic")
    public String uploadMusic(HttpServletRequest request) {
        MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = mulRequest.getFile("music");
        
        if (file.isEmpty()) {
            return "上传失败，文件内容为空...";
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(path , fileName);
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败...你没有错，都是代码的错";
    }
}
