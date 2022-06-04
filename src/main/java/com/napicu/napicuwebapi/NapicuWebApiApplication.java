package com.napicu.napicuwebapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Collections;

@SpringBootApplication
public class NapicuWebApiApplication implements ApplicationRunner {



    public static void main(String[] args) {

//        Dotenv dotenv = null;
//        dotenv = Dotenv.configure().load();
//
//        if(dotenv == null){
//            System.out.println("Není");
//        }


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
