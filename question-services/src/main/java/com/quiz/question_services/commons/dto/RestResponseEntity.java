package com.quiz.question_services.commons.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class RestResponseEntity {
    private RestResponseEntity(){
    }

    private static MultiValueMap<String, String> getJsonHeader() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set("Content-Type", MediaType.APPLICATION_JSON.toString());
        return headers;
    }

    public static <T> ResponseEntity<T> getResponse(T body, HttpStatus status) {
        return new ResponseEntity<>(body, getJsonHeader(), status);
    }

    public static <T> ResponseEntity<T> getResponse(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        headers.addAll(getJsonHeader());
        return new ResponseEntity<>(body, headers, status);
    }
}
