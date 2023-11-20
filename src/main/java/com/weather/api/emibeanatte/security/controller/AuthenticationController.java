package com.weather.api.emibeanatte.security.controller;

import com.weather.api.emibeanatte.security.dto.LoginResponseDto;
import com.weather.api.emibeanatte.security.dto.LoginUserDto;
import com.weather.api.emibeanatte.security.dto.RegisterUserDto;
import com.weather.api.emibeanatte.security.entity.User;
import com.weather.api.emibeanatte.security.service.AuthenticationService;
import com.weather.api.emibeanatte.security.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JwtService jwtService;

    @Operation(summary = "Register new User.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User created succesfully.",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
        @ApiResponse(responseCode = "400", description = "Bad request or invalid data provided.",
                content = @Content),
        @ApiResponse(responseCode = "409", description = "Username is already taken.",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error.",
                content = @Content)})
    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @Operation(summary = "Login user and generate Authentication token.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User loged succesfully.",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponseDto.class))}),
        @ApiResponse(responseCode = "400", description = "Bad request.",
                content = @Content),
        @ApiResponse(responseCode = "401", description = "Bad credentials.",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "User not found.",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error.",
                content = @Content)})
    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {

        User authenticatedUser = authenticationService.signin(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiration(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
