package com.onbrid.test.springboot.springboottest.handler;

import com.onbrid.test.springboot.springboottest.exception.OnBridException;
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
                .message("시스템에러가 발생하였습니다. 관리자에게 문의해 주세요.")
                .data(null)
                .description(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(OnBridException.class)
    OnamsResponseBody onbridErrorHandler(HttpServletRequest request, OnBridException e) {
        e.printStackTrace();
        return OnamsResponseBody.builder()
                .code(e.getCode())
                .result("error")
                .message(e.getMessage())
                .data(e.getArgs())
                .description(e.getDetailMessage())
                .path(request.getRequestURI())
                .build();

    }

}
