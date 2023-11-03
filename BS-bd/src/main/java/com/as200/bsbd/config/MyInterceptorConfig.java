package com.as200.bsbd.config;

import com.as200.bsbd.interceptor.JwtValidateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private JwtValidateInterceptor jwtValidateInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(jwtValidateInterceptor);
        registration.addPathPatterns("/**")
                .excludePathPatterns(
                        "/**",
                        "/user/login",
                        "/user/info",
                        "/user/logout",
                        "/user/*",
                        "/user",
                        "/room",
                        "/room/*",
                        "/roommanagement/*",
                        "/role",
                        "/role/*",
                        "/customerRoom/last/*",
                        "/customer",
                        "/customer/*",
                        "/error",
                        "/menu",
                        "/menu/*",
                        "/userRole",
                        "/userRole/*",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/**"
                );

    }
}
