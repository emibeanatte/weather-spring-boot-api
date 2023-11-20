package com.weather.api.emibeanatte.security.controller;

import com.weather.api.emibeanatte.security.entity.User;
import com.weather.api.emibeanatte.security.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    
    @Operation(summary = "Get Current user data.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Current user succesfully displayed.",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
        @ApiResponse(responseCode = "404", description = "User not found.",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "User not authenticated.",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error.",
                content = @Content)})
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> authenticatedUser() {
        User currentUser = userService.authenticatedUser();
        return ResponseEntity.ok(currentUser);
    }
    
}
