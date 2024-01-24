package com.onbrid.test.springboot.springboottest.handler;

import com.onbrid.test.springboot.springboottest.model.OnamsResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class OnamsExceptionHandler {

    // TODO: Error 일때 httpStatus 를 처리해주는 방법...

    @ExceptionHandler(Exception.class)
    OnamsResponseBody defaultErrorHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return OnamsResponseBody.builder()
                .result("error")
                .message(e.getMessage())
                .data(null)
                .description(e.toString())
                .path(request.getRequestURI())
                .httpStatus(-1)
                .build();
    }


}
