package com.napicu.napicuwebapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Collections;

@SpringBootApplication
public class NapicuWebApiApplication {

    public static void main(String[] args) {

//        Dotenv dotenv = null;
//        dotenv = Dotenv.configure().load();
//
//        if(dotenv == null){
//            System.out.println("Není");
//        }
        SpringApplication.run(NapicuWebApiApplication.class, args);

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
