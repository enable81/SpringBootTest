package com.onbrid.test.springboot.springboottest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onbrid.test.springboot.springboottest.handler.OnamsHttpMessageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    final ObjectMapper objectMapper;


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 스트링 문자열 "abcd" 를 {ObjectMapper} 로 변환
        // converters.add(0, new OnamsHttpMessageConverter(objectMapper));
    }
}
