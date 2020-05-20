package com.tensquare.qa.ApplicationConfig;

import com.tensquare.qa.intercepter.jwtintercepter.JwtIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author zhang
 * @Date 2020/5/17 20:10
 * @Version 1.0
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtIntercepter jwtIntercepter;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtIntercepter)
                .addPathPatterns("/**");
    }
}
