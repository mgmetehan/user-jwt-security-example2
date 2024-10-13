package com.mgmetehan.userjwtsecurityexample2.user.repository;

import com.mgmetehan.userjwtsecurityexample2.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsUserByEmail(final String email);
    Optional<User> findUserByEmail(final String email);
}