package com.as200.bsbd.config;


import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@Configuration
public class MyCorsConfig {
//    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration configuration = new CorsConfiguration();
//        允许的域
        configuration.addAllowedOrigin("http://localhost:9528");
//        是否发送cookie信息
        configuration.setAllowCredentials(true);
//        允许的请求方式
        configuration.addAllowedMethod("*");
//        允许的头信息
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}

//// 第二种全局跨域方法
//@Configuration
//public class MyCorsConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowCredentials(true)
//                .allowedOrigins("*")
//                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
//                .allowedHeaders("*")
//                .exposedHeaders("*");
//    }
//}
