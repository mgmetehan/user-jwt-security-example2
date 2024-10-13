package com.mgmetehan.userjwtsecurityexample2.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mgmetehan.userjwtsecurityexample2.core.exception.ErrorStatusCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }

    @Override
    public void commence(final HttpServletRequest httpServletRequest,
                         final HttpServletResponse httpServletResponse,
                         final AuthenticationException authenticationException) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

        Map<String, Object> errors = new HashMap<>();
        errors.put("error", "Unauthorized access");
        errors.put("status", ErrorStatusCode.UNAUTHORIZED.getValue());
        errors.put("repeatable", ErrorStatusCode.UNAUTHORIZED.isRepeatable());

        final String responseBody = OBJECT_MAPPER.writeValueAsString(errors);
        httpServletResponse.getOutputStream().write(responseBody.getBytes(StandardCharsets.UTF_8));
    }
}
