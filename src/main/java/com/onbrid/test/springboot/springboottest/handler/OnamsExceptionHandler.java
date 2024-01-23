package com.onbrid.test.springboot.springboottest.handler;

import com.onbrid.test.springboot.springboottest.model.OnamsResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class OnamsExceptionHandler {

    @ExceptionHandler(Exception.class)
    OnamsResponseBody defaultErrorHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return OnamsResponseBody.builder()
                .result("error")
                .message(e.getMessage())
                .data(null)
                .description(e.toString())
                .build();
    }


}
