package com.trustrace.security30.corsConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CORSConfig implements WebMvcConfigurer {
    public void addCorsMapping(CorsRegistry registry){
        registry.addMapping ("/**")
                .allowedOrigins ("/http://localhost:9999")
                .allowedMethods ("GET","POST","PUT")
                .exposedHeaders ("JWTToken");
    }
}
