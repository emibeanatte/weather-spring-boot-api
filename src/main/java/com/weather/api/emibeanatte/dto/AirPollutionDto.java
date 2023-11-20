package com.weather.api.emibeanatte.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirPollutionDto implements Serializable {

    @JsonProperty("list")
    private List<AirpollutionData> list;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AirpollutionData implements Serializable {

        @JsonProperty("main")
        private Main main;

        @JsonProperty("components")
        private Component components;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Main implements Serializable {

        @JsonProperty("aqi")
        private int airQualityIndex;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Component implements Serializable {

        @JsonProperty("co")
        private double carbonMonoxide;

        @JsonProperty("no")
        private double nitrogenMonoxide;

        @JsonProperty("no2")
        private double nitrogenDioxide;

        @JsonProperty("o3")
        private double ozone;

        @JsonProperty("so2")
        private double sulfurDioxide;

        @JsonProperty("pm2_5")
        private double particulateMatter2_5;

        @JsonProperty("pm10")
        private double particulateMatter10;

        @JsonProperty("nh3")
        private double ammonia;

    }
}
