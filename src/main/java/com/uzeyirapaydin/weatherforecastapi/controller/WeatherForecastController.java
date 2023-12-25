package com.uzeyirapaydin.weatherforecastapi.controller;

import com.uzeyirapaydin.weatherforecastapi.model.WeatherForecastHourlyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uzeyirapaydin.weatherforecastapi.service.OpenWeatherMapService;

@RestController
@RequestMapping("/api/forecast")
public class WeatherForecastController {

    private final OpenWeatherMapService weatherForecastService;

    public WeatherForecastController(OpenWeatherMapService weatherForecastService) {
        this.weatherForecastService = weatherForecastService;
    }

    @Operation(summary = "Get Weather Forecast By City Name provided in http body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json") },
                    description = "Successfully retrieved weather forecasts"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found"),
            @ApiResponse(responseCode = "500", description = "City Name can not be null")
    } )

    @GetMapping("/next48h/{city}")
    @Cacheable(value="forecasts48hours", key="#city")

    public ResponseEntity<?> GetCityForecastNext48Hours(
            @RequestHeader(value="X-API-KEY") String apiKey,
            @RequestHeader(value="X-API-SECRET") String apiSecret,
            @Parameter(description = "City Name") @PathVariable("city") String city)
    {
        WeatherForecastHourlyResponse weatherForecastResponse =  weatherForecastService.getWeatherForecastHourlyByCityName(city);
        return new ResponseEntity<>(weatherForecastResponse, HttpStatus.OK);
    }
}
