

package com.weather.api.emibeanatte.security.dto;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class LoginResponseDto {
    private String token;
    private long expiration;
}
