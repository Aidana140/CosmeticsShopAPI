package com.cosmetic.shop.controller;

import com.cosmetic.shop.dto.AuthResponse;
import com.cosmetic.shop.dto.LoginRequest;
import com.cosmetic.shop.dto.UserRegisterRequest;
import com.cosmetic.shop.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth")
@SecurityRequirement(name = "Authorization")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(description = "")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody UserRegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    @Operation(description = "")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

}
