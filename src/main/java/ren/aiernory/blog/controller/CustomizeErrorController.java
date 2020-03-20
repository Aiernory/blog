package ren.aiernory.blog.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.CustomizeErrorController
 * @date 2020/3/20 18:38
 * @Description:
 */
@Controller
@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class CustomizeErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        
        return "error";
    }
    
    
    @RequestMapping(produces = {"text/html"})
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView =new ModelAndView("error");
        HttpStatus status = this.getStatus(request);
        if(status.is4xxClientError()){
            modelAndView.addObject("message", "请求错了，请求的页面不存在。");
        }else if(status.is5xxServerError()){
            modelAndView.addObject("message", "服务器出错了。。。");
        }else {
            modelAndView.addObject("message", "我的代码有大错误..尽可能先绕过这里吧...");
        }
        modelAndView.addObject("status", status);
        return modelAndView;
    }
    
    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception var4) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
    }
    
    
}
