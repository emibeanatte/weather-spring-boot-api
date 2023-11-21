package com.weather.api.emibeanatte.service;


import com.weather.api.emibeanatte.dto.AirPollutionDto;
import com.weather.api.emibeanatte.dto.CoordsDto;
import com.weather.api.emibeanatte.dto.ForecastDto;
import com.weather.api.emibeanatte.dto.WeatherDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import com.weather.api.emibeanatte.security.config.ApplicationConfig;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Service
@Import(ApplicationConfig.class)
public class WeatherServiceImpl implements WeatherService {

    private static final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String FORECAST_API_URL = "http://api.openweathermap.org/data/2.5/forecast";
    private static final String AIR_POLLUTION_API_URL = "http://api.openweathermap.org/data/2.5/air_pollution";
    private static final String GEOCODING_API_URL = "http://api.openweathermap.org/geo/1.0/direct";


    private final RestTemplate restTemplate;

    @Autowired
    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public WeatherDto getCurrentWeather(String city, String apiKey) {
        return this.restTemplate.getForObject(weatherUrl(city, apiKey), WeatherDto.class);
    }

    @Override
    public ForecastDto getForecastWeather(String city, String apiKey) {

        ForecastDto forecastDto = this.restTemplate.getForObject(forecastUrl(city, apiKey), ForecastDto.class);

        if (forecastDto != null && !forecastDto.getList().isEmpty()) {
            List<ForecastDto.WeatherData> entries = new ArrayList<>();
            Set<LocalDate> uniqueDates = new HashSet<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (ForecastDto.WeatherData weatherData : forecastDto.getList()) {
                LocalDateTime dateTime = LocalDateTime.parse(weatherData.getDtTxt(), formatter);
                LocalDate date = dateTime.toLocalDate();

                if (uniqueDates.add(date)) {
                    entries.add(weatherData);
                }
            }

            forecastDto.setList(entries);
            return forecastDto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Forecast data found for the city provided");
        }

    }

    @Override
    public AirPollutionDto getAirPollution(double lat, double lon, String apiKey) {
        return this.restTemplate.getForObject(airPollutionUrl(lat, lon, apiKey), AirPollutionDto.class);
    }

    public AirPollutionDto getCoordsByCityName(String city, String apiKey) {

        CoordsDto[] response = this.restTemplate.getForObject(geocodeUrl(city, apiKey), CoordsDto[].class);

        if (response != null && response.length > 0 && response[0] != null) {

            double lat = response[0].getLat();
            double lon = response[0].getLon();


            AirPollutionDto airPollutionDto = getAirPollution(lat, lon, apiKey);

            return airPollutionDto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No coordinates data found for the city provided");
        }
    }


    private String weatherUrl(String city, String apiKey) {
        if (!apiKey.isEmpty() && apiKey != null) {
            return String.format(WEATHER_API_URL.concat("?q=%s").concat("&limit=1").concat("&appid=%s").concat("&units=metric"), city, apiKey);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid Api Key.");
        }
    }

    private String forecastUrl(String city, String apiKey) {
        if (!apiKey.isEmpty() && apiKey != null) {
            return String.format(FORECAST_API_URL.concat("?q=%s").concat("&appid=%s").concat("&units=metric"), city, apiKey);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid Api Key.");
        }
    }

    private String airPollutionUrl(double lat, double lon, String apiKey) {
        if (!apiKey.isEmpty() && apiKey != null) {
            return String.format(AIR_POLLUTION_API_URL.concat("?lat=%s").concat("&lon=%s").concat("&appid=%s"), lat, lon, apiKey);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid Api Key.");
        } 
    }

    private String geocodeUrl(String city, String apiKey) {
        if (!apiKey.isEmpty() && apiKey != null) {
            return String.format(GEOCODING_API_URL.concat("?q=%s").concat("&appid=%s"), city, apiKey);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid Api Key.");
        }

    }

}
