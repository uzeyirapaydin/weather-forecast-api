package com.uzeyirapaydin.weatherforecastapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WeatherForecastApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherForecastApiApplication.class, args);
    }

}
