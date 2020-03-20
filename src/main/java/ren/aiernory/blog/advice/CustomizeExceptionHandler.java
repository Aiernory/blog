package ren.aiernory.blog.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ren.aiernory.blog.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.advice.CustomizeExceptionHandler
 * @date 2020/3/20 15:55
 * @Description:
 */

@ControllerAdvice
public class CustomizeExceptionHandler {
    
    
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e) {
        ModelAndView modelAndView=new ModelAndView("error");
        if(e instanceof CustomizeException){
            modelAndView.addObject("message", e.getMessage());
        }else {
            modelAndView.addObject("message", "服务器出问题了 (´･ᴗ･`)");
        }
        
        modelAndView.addObject("status",getStatus(request));
        return modelAndView;
    }
    
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
    
}
