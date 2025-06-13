package com.dandaraemiliano.api_segura.repository;

import com.dandaraemiliano.api_segura.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
