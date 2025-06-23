package com.dandaraemiliano.api_segura.service;

import com.dandaraemiliano.api_segura.dto.LoginRequest;
import com.dandaraemiliano.api_segura.dto.TokenResponse;
import com.dandaraemiliano.api_segura.dto.RegisterRequest;
import com.dandaraemiliano.api_segura.model.User;
import com.dandaraemiliano.api_segura.repository.UserRepository;
import com.dandaraemiliano.api_segura.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.dandaraemiliano.api_segura.exception.UsuarioNaoEncontradoException;
import com.dandaraemiliano.api_segura.exception.SenhaInvalidaException;
import java.util.HashSet;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
    }

    public TokenResponse autenticar(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new SenhaInvalidaException();
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());
        return new TokenResponse(token);
    }

    public void register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setEnabled(true);
        HashSet<String> roles = new HashSet<>();
        roles.add("USER");
        user.setRoles(roles);
        userRepository.save(user);
    }
} 