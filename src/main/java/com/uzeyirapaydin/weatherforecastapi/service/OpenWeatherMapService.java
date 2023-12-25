package com.uzeyirapaydin.weatherforecastapi.service;

import com.uzeyirapaydin.weatherforecastapi.model.openweathermap.Weather;
import com.uzeyirapaydin.weatherforecastapi.model.openweathermap.WeatherResponse;
import com.uzeyirapaydin.weatherforecastapi.model.WeatherForecastHourly;
import com.uzeyirapaydin.weatherforecastapi.model.WeatherForecastHourlyResponse;
import com.uzeyirapaydin.weatherforecastapi.model.WeatherForecastResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpenWeatherMapService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherMapService.class.getName());

    private final String OPENWEATHERMAP_API_KEY;
    private final String OPENWEATHERMAP_API_URL;
    private final WebClient webClient;
    private final String units;
    private final String numberOf3Hours;


    public OpenWeatherMapService(Environment environment, WebClient webClient) {
        this.webClient = webClient;
        this.OPENWEATHERMAP_API_KEY = environment.getProperty("openweathermap.api.key");
        this.OPENWEATHERMAP_API_URL = environment.getProperty("openweathermap.api.url");
        this.numberOf3Hours = environment.getProperty("openweathermap.api.number-of-3-hours");
        this.units=environment.getProperty("openweathermap.api.units");
    }

    public WeatherForecastHourlyResponse getWeatherForecastHourlyByCityName(String cityName) {
        //https://api.openweathermap.org/data/2.5/forecast?q=Istanbul&units=metric&cnt=16&appid=24d8a66dddd8dbc2c00cc58b592d3b1c&lang=tr
        String url = OPENWEATHERMAP_API_URL + "?q=" + cityName + "&units="+this.units+"&cnt="+ this.numberOf3Hours +"&appid=" +  this.OPENWEATHERMAP_API_KEY;
        return getWeatherForecastHourlyResponse(url);
    }

    private WeatherForecastHourlyResponse getWeatherForecastHourlyResponse(String url) {

        Mono<WeatherResponse> responseWebClient = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(WeatherResponse.class);

        WeatherResponse weatherResponse = responseWebClient.block();

        List<Weather> weatherList = weatherResponse.getList();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime currentPlus48Hours = now.plusHours(48);

        WeatherForecastResponse maxForecast=new WeatherForecastResponse();
        maxForecast.setMaxTemperature( Integer.MIN_VALUE);
        maxForecast.setMaxFeelsLike(Double.MIN_VALUE);
        maxForecast.setMaxHumidity( Integer.MIN_VALUE);

        WeatherForecastHourlyResponse weatherForecastResponse = new WeatherForecastHourlyResponse();
        List<WeatherForecastHourly> hourlyList=new ArrayList<>();
        for( Weather weather : weatherList ) {

            String weatherDateStr = weather.getDt_txt();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime currentWeatherLocalDateTime = LocalDateTime.parse(weatherDateStr, formatter);

            WeatherForecastHourly hourly=new WeatherForecastHourly();
            hourly.setForecastDate(currentWeatherLocalDateTime);
            hourly.setHumidity(weather.getMain().getHumidity());
            hourly.setMaximum(weather.getMain().getTemp_max());
            hourly.setFeelsLike(weather.getMain().getFeels_like());
            hourlyList.add(hourly);

            int humidityCurrent = weather.getMain().getHumidity();
            double feelsLikeCurrent = weather.getMain().getFeels_like();
            double maxTemperatureCurrent = weather.getMain().getTemp_max();

            if( feelsLikeCurrent > maxForecast.getMaxFeelsLike() ) {
                maxForecast.setMaxFeelsLike(feelsLikeCurrent);
            }
            if( humidityCurrent > maxForecast.getMaxHumidity() ) {
                maxForecast.setMaxHumidity(humidityCurrent);
            }
            if( maxTemperatureCurrent > maxForecast.getMaxTemperature() ) {
                maxForecast.setMaxTemperature(maxTemperatureCurrent);
            }
        }

        weatherForecastResponse.setMaxForecasts(maxForecast);
        weatherForecastResponse.setForecasts(hourlyList);

        return weatherForecastResponse;
    }
}
