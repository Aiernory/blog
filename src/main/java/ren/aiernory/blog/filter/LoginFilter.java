//package ren.aiernory.blog.filter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import ren.aiernory.blog.tool.CookieLogin;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
///**
// * @author Aiernory
// * @className: ren.aiernory.blog.filter.LoginFilter
// * @date 2020/3/13 15:44
// * @Description:
// */
//
//
////用不到了
//
//
////拦截全部
//@WebFilter
//public class LoginFilter implements Filter {
//    @Autowired
//    private CookieLogin cookieLogin;
//    //前端页面链接地址
//    @Value("${github.action.href}")
//    private String href;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("filter初始化");
//    }
//    //判断cookie，设置登录状态
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//
//
//
//}
