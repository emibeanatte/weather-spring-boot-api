package com.weather.api.emibeanatte.controller;

import com.weather.api.emibeanatte.config.CacheConfig;
import com.weather.api.emibeanatte.config.RateLimitConfig;
import com.weather.api.emibeanatte.config.WebMvcConfig;
import com.weather.api.emibeanatte.dto.AirPollutionDto;
import com.weather.api.emibeanatte.dto.ForecastDto;
import com.weather.api.emibeanatte.dto.WeatherDto;
import com.weather.api.emibeanatte.security.entity.User;
import com.weather.api.emibeanatte.security.service.UserService;
import com.weather.api.emibeanatte.service.QueryRecordService;
import com.weather.api.emibeanatte.service.WeatherServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("weather")
@Import({CacheConfig.class, WeatherServiceImpl.class, RateLimitConfig.class, WebMvcConfig.class})
@EnableCaching
@CrossOrigin(origins = "*")
public class WeatherController {

    @Autowired
    private WeatherServiceImpl weatherServiceImpl;

    @Autowired
    private UserService userService;

    @Autowired
    private QueryRecordService queryRecordService;

    @Operation(summary = "Get current weather from the provided city and api key.", description = "In order to get an api Key see: https://openweathermap.org/api")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Current weather succesfully displayed.",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WeatherDto.class))}),
        @ApiResponse(responseCode = "404", description = "No data found for the city provided",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "User not authenticated.",
                content = @Content),
        @ApiResponse(responseCode = "429", description = "Too many requests.",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error.",
                content = @Content)})
    @Cacheable(value = "weatherCache")
    @GetMapping(value = "/current/", produces = MediaType.APPLICATION_JSON_VALUE)
    public WeatherDto getWeatherByCityName(@RequestParam(required = true) String city, @RequestParam String apiKey, @AuthenticationPrincipal UserDetails userDetails) {

        WeatherDto result = weatherServiceImpl.getCurrentWeather(city, apiKey);

        if (result != null) {

            User currentUser = userService.authenticatedUser();
            String query = "Query: " + city;
            String response = "Response: Current Weather. " + result.toString();
            queryRecordService.registerQuery(currentUser, query, response);

            return result;
        } else {

            User currentUser = userService.authenticatedUser();
            String query = "Query: " + city;
            String response = "Response: No data found.";
            queryRecordService.registerQuery(currentUser, query, response);

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No weather data found for the city provided");
        }

    }
    
    
    @Operation(summary = "Get 5 day forecast weather from the provided city and api key.", description = "In order to get an api Key see: https://openweathermap.org/api")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Forecast weather succesfully displayed.",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ForecastDto.class))}),
        @ApiResponse(responseCode = "404", description = "No forecast data found for the city provided",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "User not authenticated.",
                content = @Content),
        @ApiResponse(responseCode = "429", description = "Too many requests.",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error.",
                content = @Content)})
    @Cacheable(value = "forecastCache")
    @GetMapping(value = "/forecast/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ForecastDto getForecastByCityName(@RequestParam(required = true) String city, @RequestParam String apiKey, @AuthenticationPrincipal UserDetails userDetails) {

        ForecastDto result = weatherServiceImpl.getForecastWeather(city, apiKey);
        
        if (result != null) {
            User currentUser = userService.authenticatedUser();
            String query = "Query: " + city;
            String response = "Response: 5 Days Weather Forecast for: " + result.getList().get(0).getWeather().get(0).toString();
            queryRecordService.registerQuery(currentUser, query, response);

            return result;
        } else {
            User currentUser = userService.authenticatedUser();
            String query = "Query: " + city;
            String response = "Response: No data found.";
            queryRecordService.registerQuery(currentUser, query, response);

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No forecast data found for the city provided");
        }

    }
    
    
    @Operation(summary = "Get Air Pollution from the provided city and api key.", description = "In order to get an api Key see: https://openweathermap.org/api")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Air pollution succesfully displayed.",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AirPollutionDto.class))}),
        @ApiResponse(responseCode = "404", description = "No Air pollution data found for the city provided",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "User not authenticated.",
                content = @Content),
        @ApiResponse(responseCode = "429", description = "Too many requests.",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error.",
                content = @Content)})
    @GetMapping(value = "/air_pollution/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "airPollutionCache")
    public AirPollutionDto getAirPollutionByCityName(@RequestParam(required = true) String city, @RequestParam String apiKey, @AuthenticationPrincipal UserDetails userDetails) {
        
        AirPollutionDto result = weatherServiceImpl.getCoordsByCityName(city, apiKey);
        
        if (result != null) {
            User currentUser = userService.authenticatedUser();
            String query = "Query: " + city;
            String response = "Response: Air pollution. " + result.getList().get(0).getMain().getAirQualityIndex();
            queryRecordService.registerQuery(currentUser, query, response);

            return result;   
        } else {
            User currentUser = userService.authenticatedUser();
            String query = "Query: " + city;
            String response = "Response: No data found.";
            queryRecordService.registerQuery(currentUser, query, response);

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Air Pollution data found for the city provided");
        }     
    }
}
