package ren.aiernory.blog.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import ren.aiernory.blog.tool.CookieLogin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.filter.LoginFilter
 * @date 2020/3/13 15:44
 * @Description:
 */
@Order(1)
@WebFilter(filterName = "loginFilter",urlPatterns = "/")
public class LoginFilter implements Filter {
    @Autowired
    private CookieLogin cookieLogin;
    //前端页面链接地址
    @Value("${github.action.href}")
    private String href;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    //判断cookie，设置登录状态
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.getSession().setAttribute("githubHref",href);
        cookieLogin.cookieVerify(request);
        filterChain.doFilter(servletRequest,servletResponse);
    }
    
    @Override
    public void destroy() {
    
    }
    

    
    
}
