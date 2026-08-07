package com.suhas.anivault.controller;

import com.suhas.anivault.dto.RegisterRequestDTO;
import com.suhas.anivault.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.suhas.anivault.dto.LoginRequestDTO;
import com.suhas.anivault.dto.LoginResponseDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(
            summary = "Register User",
            description = "Public endpoint to register a new user."
    )
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequestDTO requestDTO) {

        String response = authService.register(requestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login User",
            description = "Public endpoint that authenticates the user and returns a JWT token."
    )
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO requestDTO) {

        LoginResponseDTO response = authService.login(requestDTO);

        return ResponseEntity.ok(response);
    }
}
