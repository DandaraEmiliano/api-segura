package com.dandaraemiliano.api_segura.service;

import com.dandaraemiliano.api_segura.dto.LoginRequest;
import com.dandaraemiliano.api_segura.dto.TokenResponse;
import com.dandaraemiliano.api_segura.model.User;
import com.dandaraemiliano.api_segura.repository.UserRepository;
import com.dandaraemiliano.api_segura.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void autenticar_DeveRetornarToken_QuandoCredenciaisValidas() {
        LoginRequest request = new LoginRequest();
        request.setUsername("user");
        request.setPassword("senha");

        User user = new User();
        user.setUsername("user");
        user.setPassword("senhaCriptografada");
        user.setRoles(Set.of("USER"));
        user.setEnabled(true);

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
        when(encoder.matches("senha", "senhaCriptografada")).thenReturn(true);
        when(jwtUtil.generateToken("user", user.getRoles())).thenReturn("token123");

        TokenResponse response = authService.autenticar(request);
        assertEquals("token123", response.getToken());
    }
} 