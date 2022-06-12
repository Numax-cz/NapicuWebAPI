package com.napicu.napicuwebapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "NapicuAPI", version = "1.0", description = "API pro NapicuWebs"))
public class NapicuWebApiApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(NapicuWebApiApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
                registry.addMapping("/**").allowedHeaders("*");
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }

}
