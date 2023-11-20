package com.weather.api.emibeanatte.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordsDto implements Serializable {

    private String name;
    private LocalNames local_names;
    private double lat;
    private double lon;
    private String country;
    private String state;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocalNames implements Serializable {

        private String ms;
        private String gu;
    }
}
