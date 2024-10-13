package com.mgmetehan.userjwtsecurityexample2.auth.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenType {
    BEARER("Bearer");

    private final String value;
}
