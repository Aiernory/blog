package ren.aiernory.blog.advice;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ren.aiernory.blog.resultMessage.ErrorMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.advice.CustomizeExceptionHandler
 * @date 2020/3/20 15:55
 * @Description:
 */

@ControllerAdvice
public class CustomizeExceptionHandler {
    
    
    @ExceptionHandler(ErrorMessage.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("error");
        String type = request.getContentType();
        if (e instanceof ErrorMessage) {
            //ErrorMessage errorMessage = (ErrorMessage) e;
            modelAndView.addObject("message", e.getMessage());
            if (type.contains("application/json")) {
                //返回json
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json");
                try (PrintWriter writer = response.getWriter()) {
                    writer.write(JSON.toJSONString(e));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return null;
            } else {
                //返回页面
            }
        } else {
            modelAndView.addObject("message", "(´･ᴗ･`)");
        }
        
        modelAndView.addObject("status", getStatus(request));
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
