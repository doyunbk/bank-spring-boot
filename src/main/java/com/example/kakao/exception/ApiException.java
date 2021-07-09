package com.example.kakao.exception;

import lombok.Data;

@Data
public class ApiException {
    private final String message;
    private final int code;

}
