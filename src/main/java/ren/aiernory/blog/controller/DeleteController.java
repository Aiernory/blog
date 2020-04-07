package ren.aiernory.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ren.aiernory.blog.service.DeleteService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.DeleteController
 * @date 2020/4/7 15:52
 * @Description:
 */
@RestController
public class DeleteController {
    //所有的、合法的、删库操作
//因为最后才加的，所以....，....，...，...不同表、功能的删除操作就神奇的写到一起了
    //ajax 可以发送 del请求。其他都....  不，所有的都要del，全ajax
    
    //作者可以删除文章、删除标签、删除评论。。。。一下删除一楼层、仿贴吧！
    //评论者能删除评论，但是！！！不可以删除删除下面的子评论
    
    //验证不同，操作相同。
    
    //文章删除，标签删除，评论删除
    
    //文章删除，验证id，删除
    
    //评论删除，发送下面的所有的id号。然后删除、、、或者修改表，增加一个字段，是否可用。数字0，不可用，空或者其他都正常。查询的时候...
    //这得修改所有查询.....咋整....。。不删库，是为了留存记录。比如贴吧评论删除了，楼层还在。。。个人信息中也还在..
    //这里没啥必要，，，全删！！！！
    //前台包装好子评论id，减少后台查询。一并删了！
    
    
    //标签，标签id，删了。。引用数那里，后台搞。引用数没了，就删掉
    
    @Autowired
    private DeleteService deleteService;
    
    
    //public String delLabel(@RequestParam(name = "labelId")int labelId){
    //    int result = deleteService.delLabel(labelId);
    //
    //    return new String(""+result);
    //}
    
    @DeleteMapping("/del/comment")
    public String delComment(@RequestBody String body){
        List<Integer> ids =new ArrayList<>();
        String[] split = body.split(",");
        for (String s : split) {
            ids.add(Integer.parseInt(s));
        }
        //总之，得到ids，至少一个元素
        int result = deleteService.delComments(ids);
        return new String(""+result);
    }
    
   
    @DeleteMapping("/del/article/{id}")
    public String delArticle(@PathVariable(name = "id")int labelId){
        
        //总之，得到ids，至少一个元素
        int result = deleteService.delArticle(labelId);
        
        return new String(""+result);
    }
    
    
    
}
