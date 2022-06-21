package com.napicu.napicuwebapi.Config;


import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class OpenAPI {
    @Bean
    public io.swagger.v3.oas.models.OpenAPI openApi() {
        return new io.swagger.v3.oas.models.OpenAPI ()
                .info(new Info()
                        .title("NapicuWebAPI")
                        .description("NapicuAPI pro NapicuWebs")
                        .version("v2.0")
                        .contact(new Contact()
                                .name("Numax")
                                .url("https://napicu.eu")
                                .email("numax@napicu.eu"))
                        .license(new License().name("MIT").url("https://opensource.org/licenses/mit-license.php"))
                ).addServersItem(new Server()
                        .url("https://api2.napicu.eu")
                        .description("URL Pro NapicuAPI")
                );
    }
}
