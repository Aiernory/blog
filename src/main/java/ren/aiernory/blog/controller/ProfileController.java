package ren.aiernory.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ren.aiernory.blog.dto.PageHelper;
import ren.aiernory.blog.model.User;
import ren.aiernory.blog.service.PublishService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.ProfileController
 * @date 2020/3/16 0:44
 * @Description:
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    private PublishService publishService;
    
  
    
    @GetMapping("/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          HttpServletRequest request, Model model) {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer size=10;
        if ("article".equals(action)) {
            model.addAttribute("section", "article");
            model.addAttribute("sectionName", "我的文章");
            PageHelper pageHelper = publishService.listByCreator(user, page, size);
            model.addAttribute("pageHelper", pageHelper);
        } else if ("message".equals(action)) {
            model.addAttribute("section", "message");
            model.addAttribute("sectionName", "消息中心");
        } else if ("reply".equals(action)) {
            model.addAttribute("section", "reply");
            model.addAttribute("sectionName", "最新回复");
        } else {
            model.addAttribute("section", "nothing");
            model.addAttribute("sectionName", "建设中");
        }
        return "profile";
    }
}
