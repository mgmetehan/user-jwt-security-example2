package com.mgmetehan.userjwtsecurityexample2.auth.service;

import com.mgmetehan.userjwtsecurityexample2.auth.constants.TokenConfigurationConstants;
import com.mgmetehan.userjwtsecurityexample2.auth.model.Token;
import com.mgmetehan.userjwtsecurityexample2.auth.model.type.TokenClaims;
import com.mgmetehan.userjwtsecurityexample2.auth.model.type.TokenType;
import com.mgmetehan.userjwtsecurityexample2.auth.model.type.UserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final InvalidTokenService invalidTokenService;

    public Token generateToken(final Map<String, Object> claims) {

        final long currentTimeMillis = System.currentTimeMillis();

        final Date tokenIssuedAt = new Date(currentTimeMillis);

        final Date accessTokenExpiresAt = DateUtils.addMinutes(
                new Date(currentTimeMillis),
                TokenConfigurationConstants.ACCESS_TOKEN_EXPIRE_MINUTE
        );

        final String accessToken = Jwts.builder()
                .header()
                .type(TokenType.BEARER.getValue())
                .and()
                .id(UUID.randomUUID().toString())
                .issuer(TokenConfigurationConstants.ISSUER)
                .issuedAt(tokenIssuedAt)
                .expiration(accessTokenExpiresAt)
                .signWith(TokenConfigurationConstants.PRIVATE_KEY)
                .claims(claims)
                .compact();

        final Date refreshTokenExpiresAt = DateUtils.addDays(
                new Date(currentTimeMillis),
                TokenConfigurationConstants.REFRESH_TOKEN_EXPIRE_DAY
        );

        final String refreshToken = Jwts.builder()
                .header()
                .type(TokenType.BEARER.getValue())
                .and()
                .id(UUID.randomUUID().toString())
                .issuer(TokenConfigurationConstants.ISSUER)
                .issuedAt(tokenIssuedAt)
                .expiration(refreshTokenExpiresAt)
                .signWith(TokenConfigurationConstants.PRIVATE_KEY)
                .claim(TokenClaims.USER_ID.getValue(), claims.get(TokenClaims.USER_ID.getValue()))
                .compact();

        return Token.builder()
                .accessToken(accessToken)
                .accessTokenExpiresAt(accessTokenExpiresAt.toInstant().getEpochSecond())
                .refreshToken(refreshToken)
                .build();

    }

    public Token generateToken(final Map<String, Object> claims, final String refreshToken) {
        final long currentTimeMillis = System.currentTimeMillis();

        final String refreshTokenId = this.getId(refreshToken);

        invalidTokenService.checkForInvalidityOfToken(refreshTokenId);

        final Date accessTokenIssuedAt = new Date(currentTimeMillis);

        final Date accessTokenExpiresAt = DateUtils.addMinutes(
                new Date(currentTimeMillis),
                TokenConfigurationConstants.ACCESS_TOKEN_EXPIRE_MINUTE
        );

        final String accessToken = Jwts.builder()
                .header()
                .type(TokenType.BEARER.getValue())
                .and()
                .id(UUID.randomUUID().toString())
                .issuer(TokenConfigurationConstants.ISSUER)
                .issuedAt(accessTokenIssuedAt)
                .expiration(accessTokenExpiresAt)
                .signWith(TokenConfigurationConstants.PRIVATE_KEY)
                .claims(claims)
                .compact();

        return Token.builder()
                .accessToken(accessToken)
                .accessTokenExpiresAt(accessTokenExpiresAt.toInstant().getEpochSecond())
                .refreshToken(refreshToken)
                .build();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(final String token) {

        final Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(TokenConfigurationConstants.PUBLIC_KEY)
                .build()
                .parseSignedClaims(token);

        final JwsHeader jwsHeader = claimsJws.getHeader();
        final Claims payload = claimsJws.getPayload();

        final Jwt jwt = new Jwt(
                token,
                payload.getIssuedAt().toInstant(),
                payload.getExpiration().toInstant(),
                Map.of(
                        TokenClaims.TYP.getValue(), jwsHeader.getType(),
                        TokenClaims.ALGORITHM.getValue(), jwsHeader.getAlgorithm()
                ),
                payload
        );

        final UserType userType = UserType.valueOf(payload.get(TokenClaims.USER_TYPE.getValue()).toString());

        final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userType.name()));

        return UsernamePasswordAuthenticationToken
                .authenticated(jwt, null, authorities);

    }

    public void verifyAndValidate(final String jwt) {
        Jwts.parser()
                .verifyWith(TokenConfigurationConstants.PUBLIC_KEY)
                .build()
                .parseSignedClaims(jwt);
    }

    public void verifyAndValidate(final Set<String> jwts) {
        jwts.forEach(this::verifyAndValidate);
    }

    public Jws<Claims> getClaims(final String jwt) {
        return Jwts.parser()
                .verifyWith(TokenConfigurationConstants.PUBLIC_KEY)
                .build()
                .parseSignedClaims(jwt);

    }

    public Claims getPayload(final String jwt) {
        return Jwts.parser()
                .verifyWith(TokenConfigurationConstants.PUBLIC_KEY)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public String getId(final String jwt) {
        return Jwts.parser()
                .verifyWith(TokenConfigurationConstants.PUBLIC_KEY)
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .getId();
    }
}
