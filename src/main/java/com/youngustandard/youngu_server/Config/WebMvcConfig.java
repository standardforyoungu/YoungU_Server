package com.youngustandard.youngu_server.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 스프링 빈으로 등록
public class WebMvcConfig implements WebMvcConfigurer {
    private final long MAX_AGE_SECS=3000;

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        //모든 경로에 대해
        corsRegistry.addMapping("/**")
        //Origin이 http:localhost:3000에 대해
        .allowedOrigins("http://localhost:3000","http://young-u-standard.site")
                .allowedMethods("GET","POST","PUT","PATCH","DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);
    }
}
