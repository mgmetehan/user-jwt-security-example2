package com.mgmetehan.userjwtsecurityexample2.auth.service;

import com.mgmetehan.userjwtsecurityexample2.auth.model.InvalidToken;
import com.mgmetehan.userjwtsecurityexample2.auth.repository.InvalidTokenRepository;
import com.mgmetehan.userjwtsecurityexample2.core.exception.ErrorStatusCode;
import com.mgmetehan.userjwtsecurityexample2.core.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvalidTokenService {

    private final InvalidTokenRepository invalidTokenRepository;

    public void invalidateTokens(Set<String> tokenIds) {
        final Set<InvalidToken> enocaInvalidTokenEntities = tokenIds.stream()
                .map(tokenId -> InvalidToken.builder()
                        .tokenId(tokenId)
                        .build()
                )
                .collect(Collectors.toSet());

        invalidTokenRepository.saveAll(enocaInvalidTokenEntities);
    }

    public void checkForInvalidityOfToken(String tokenId) {
        final boolean isTokenInvalid = invalidTokenRepository.findByTokenId(tokenId).isPresent();

        if (isTokenInvalid) {
            throw new GeneralException(ErrorStatusCode.TOKEN_ALREADY_INVALIDATED);
        }
    }
}
