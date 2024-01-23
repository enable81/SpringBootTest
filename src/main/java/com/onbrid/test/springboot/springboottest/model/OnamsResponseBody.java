package com.onbrid.test.springboot.springboottest.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Setter
@Getter
@NoArgsConstructor
public class OnamsResponseBody {

    private String result;

    private String message;

    private Object data;

    private String description;

    // private HttpStatus httpStatus;

    @Builder
    public OnamsResponseBody(String result, String message, Object data, String description) {
        this.result = result;
        this.message = message;
        this.data = data;
        this.description = description;
    }
}
