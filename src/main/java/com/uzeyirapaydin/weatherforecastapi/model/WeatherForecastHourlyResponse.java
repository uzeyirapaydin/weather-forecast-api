package com.uzeyirapaydin.weatherforecastapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherForecastHourlyResponse {
    private WeatherForecastResponse maxForecasts = new WeatherForecastResponse();
    private List<WeatherForecastHourly> forecasts = new ArrayList<>();
}
