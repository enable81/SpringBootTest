package com.onbrid.test.springboot.springboottest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onbrid.test.springboot.springboottest.handler.OnamsHttpMessageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    final ObjectMapper objectMapper;


    /**
     * (Cross-Origin Resource Sharing,CORS) 란 다른 출처의 자원을 공유할 수 있도록 설정하는 권한 체제를 말합니다.
     * <a href="https://dev-pengun.tistory.com/entry/Spring-Boot-CORS-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0">[Spring Boot] CORS 설정하기</a>
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST, GET")
                // .allowedHeaders("Content-Type")
                // .maxAge(3600)                       // maxAge메소드를 이용해서 원하는 시간만큼 pre-flight 리퀘스트를 캐싱 해둘 수 있습니다.
                // .allowCredentials(false)            // 쿠키 요청을 허용한다(다른 도메인 서버에 인증하는 경우에만 사용해야하며, true 설정시 보안상 이슈가 발생할 수 있다)
                ;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 스트링 문자열 "abcd" 를 {ObjectMapper} 로 변환
        // converters.add(0, new OnamsHttpMessageConverter(objectMapper));
    }
}
