package com.dandaraemiliano.api_segura.service;

import com.dandaraemiliano.api_segura.model.User;
import com.dandaraemiliano.api_segura.repository.UserRepository;
import com.dandaraemiliano.api_segura.dto.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.dandaraemiliano.api_segura.exception.UsuarioNaoEncontradoException;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User buscarUsuarioAtual(UserDetails userDetails) {
        return repository.findByUsername(userDetails.getUsername())
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }

    public UserResponse buscarUsuarioAtualResponse(UserDetails userDetails) {
        User user = buscarUsuarioAtual(userDetails);
        return new UserResponse(user.getId(), user.getUsername(), user.isEnabled(), user.getRoles());
    }
} 