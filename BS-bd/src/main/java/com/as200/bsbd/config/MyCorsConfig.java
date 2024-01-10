package com.as200.bsbd.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
