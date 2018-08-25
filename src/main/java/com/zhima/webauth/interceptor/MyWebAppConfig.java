package com.zhima.webauth.interceptor;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by superz on 2018/8/8.
 */
//@Configuration
public class MyWebAppConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new IPLimitInterceptor(null)).addPathPatterns("/**");
        registry.addInterceptor(new SmoothBurstyInterceptor(100, SmoothBurstyInterceptor.LimitType.DROP)).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
