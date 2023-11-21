package com.weather.api.emibeanatte.service;

import com.weather.api.emibeanatte.dto.AirPollutionDto;
import com.weather.api.emibeanatte.dto.ForecastDto;
import com.weather.api.emibeanatte.dto.WeatherDto;

public interface WeatherService {

    WeatherDto getCurrentWeather(String city, String apiKey);

    ForecastDto getForecastWeather(String city, String apiKey);

    AirPollutionDto getAirPollution(double lat, double lon, String apiKey);

}
