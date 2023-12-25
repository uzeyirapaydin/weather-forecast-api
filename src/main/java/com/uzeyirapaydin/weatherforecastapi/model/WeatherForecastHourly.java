package com.uzeyirapaydin.weatherforecastapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherForecastHourly {
    private double maximum;
    private double feelsLike;
    private int humidity;
    private LocalDateTime forecastDate;
}
