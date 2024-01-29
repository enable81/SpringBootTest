package com.onbrid.test.springboot.springboottest.handler;

import com.onbrid.test.springboot.springboottest.model.OnamsResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 봉투 패턴(Envelope Pattern)<br/>
 * 컨트롤러 메서드가 @ResponseBody나 ResponseEntity로 반환할 때의 응답을 커스터마이징 할 수 있다.<br/>
 * 이는 메시지 컨버터가 해당 응답 객체를 변환하기 전에 일어난다.<br/>
 */
@RestControllerAdvice(basePackages = {"com.onbrid.test.springboot.springboottest.controller"})
@Slf4j
public class OnamsResponseHandler implements ResponseBodyAdvice<Object> {

    /**
     * https://velog.io/@kylekim2123/SpringBoot-ResponseBodyAdvice%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EA%B3%B5%ED%86%B5-%EC%9D%91%EB%8B%B5-%EC%B2%98%EB%A6%AC%EC%99%80-%EA%B4%80%EB%A0%A8-%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {


        return OnamsResponseBody.builder()
                .result("success")
                .code(((ServletServerHttpResponse) response).getServletResponse().getStatus())
                .message("")
                .data(body)
                .description("")
                .path(request.getURI().getPath())
                .build();
    }


}
