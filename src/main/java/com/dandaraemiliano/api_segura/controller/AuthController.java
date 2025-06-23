package com.dandaraemiliano.api_segura.controller;

import com.dandaraemiliano.api_segura.dto.LoginRequest;
import com.dandaraemiliano.api_segura.dto.TokenResponse;
import com.dandaraemiliano.api_segura.dto.RegisterRequest;
import com.dandaraemiliano.api_segura.repository.UserRepository;
import com.dandaraemiliano.api_segura.service.AuthService;
import com.dandaraemiliano.api_segura.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;
    private final AuthService authService;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder encoder, AuthService authService) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.autenticar(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok().build();
    }
}
