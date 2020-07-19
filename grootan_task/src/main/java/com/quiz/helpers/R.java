package com.quiz.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class R<T> extends ResponseEntity<T> {

    public R(HttpStatus status) {
        super(status);
    }

    public R(T body, HttpStatus status) {
        super(body, status);
    }

    public R(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    public R(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

}
