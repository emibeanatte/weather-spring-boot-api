package com.weather.api.emibeanatte.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto implements Serializable {

    @JsonProperty("name")
    private String city;

    @JsonProperty("main")
    private Main main;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("weather")
    private List<WeatherDescription> weather;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Main {

        @JsonProperty("temp")
        private double temp;

        @JsonProperty("humidity")
        private int humidity;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Wind {

        @JsonProperty("speed")
        private double speed;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WeatherDescription {

        private String description;
    }

}
