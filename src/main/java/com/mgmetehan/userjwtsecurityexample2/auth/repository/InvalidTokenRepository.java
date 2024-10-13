package com.mgmetehan.userjwtsecurityexample2.auth.repository;

import com.mgmetehan.userjwtsecurityexample2.auth.model.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvalidTokenRepository extends JpaRepository<InvalidToken, String> {
    Optional<InvalidToken> findByTokenId(final String tokenId);
}
