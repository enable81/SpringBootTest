package com.onbrid.test.springboot.springboottest.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
public class OnamsResponseBody<T> {

    private String result;

    private String message;

    private T data;

    private String description;

    private String path;

    private final LocalDateTime timeStamp = LocalDateTime.now();

    private int httpStatus;

    @Builder
    public OnamsResponseBody(String result, String message, T data, String description, String path, int httpStatus) {
        this.result = result;
        this.message = message;
        this.data = data;
        this.description = description;
        this.path = path;
        this.httpStatus = httpStatus;
    }
}
