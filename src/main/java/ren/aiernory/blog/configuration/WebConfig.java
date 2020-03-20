//package ren.aiernory.blog.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import ren.aiernory.blog.interceptor.LoginInterceptor;
//
///**
// * @author Aiernory
// * @className: ren.aiernory.blog.configuration.MvcConfig
// * @date 2020/3/17 19:17
// * @Description:
// */
////配置类通用注解
//@Configuration
////完全接管mvc配置
//@EnableWebMvc
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
//    }
//
////过滤静态资源
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/static/js");
//    }
//}

