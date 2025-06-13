package com.dandaraemiliano.api_segura.controller;

import com.dandaraemiliano.api_segura.dto.LoginRequest;
import com.dandaraemiliano.api_segura.dto.TokenResponse;
import com.dandaraemiliano.api_segura.model.User;
import com.dandaraemiliano.api_segura.repository.UserRepository;
import com.dandaraemiliano.api_segura.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
