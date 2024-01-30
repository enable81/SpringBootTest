package com.onbrid.test.springboot.springboottest.config;

import com.onbrid.test.springboot.springboottest.interceptor.OnamsHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@RequiredArgsConstructor
public class WebConfig extends WebMvcConfigurationSupport {

    final OnamsHandlerInterceptor onamsHandlerInterceptor;

//    private final ObjectMapper objectMapper;
//
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(0, new OnamsHttpMessageConverter(objectMapper));
//    }


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(onamsHandlerInterceptor).excludePathPatterns("/");
    }
}
