package com.uzeyirapaydin.weatherforecastapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherForecastResponse {
    private double maxFeelsLike;
    private double maxTemperature;
    private int maxHumidity;
}
