package com.uzeyirapaydin.weatherforecastapi.config.openapi;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {


    @Bean
    public OpenAPI myOpenAPI() {
//        Server devServer = new Server();
//        devServer.setUrl("http://localhost:8080");
//        devServer.setDescription("Server URL in Development environment");


        Contact contact = new Contact();
        contact.setEmail("uzeyirapaydin@gmail.com");
        contact.setName("Üzeyir Apaydın");
        contact.setUrl("https://www.quickcode.net");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Weather Forecast API")
                .version("1.0")
                .contact(contact)
                .description("Weather Forecast API Case Study")
                    .termsOfService("https://www.quickcode.net")
                .license(mitLicense);

        return new OpenAPI().info(info);
    }
}