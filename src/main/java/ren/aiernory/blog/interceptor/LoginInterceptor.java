//package ren.aiernory.blog.interceptor;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author Aiernory
// * @className: ren.aiernory.blog.interceptor.LoginInterceptor
// * @date 2020/3/17 19:26
// * @Description:
// */
//public class LoginInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        /*
//        System.out.println("是服务器开启，还是每次新的会话创建？？");//结果每次请求都会执行
//        我现在的登录逻辑是，点击登录按钮了，去检验cookie，如果cookie检测失败，重定向到github授权登录页面
//        退出登录可以选择删除cookie。。。导致了每次重新建立连接必须点击一下登录，才能登录
//        能不能在会话刚建立的时候，检查一次cookie，之后不再检查。。。filter的初始化是启动服务器时启动，拦截器是每次请求
//        登录逻辑先保持这样，找到好当发了再修改..
//         */
//        //先不在config中注册这个
//        return false;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//}
