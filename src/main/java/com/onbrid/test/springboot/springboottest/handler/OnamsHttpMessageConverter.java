package com.onbrid.test.springboot.springboottest.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * <a href="https://velog.io/@kylekim2123/SpringBoot-ResponseBodyAdvice%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EA%B3%B5%ED%86%B5-%EC%9D%91%EB%8B%B5-%EC%B2%98%EB%A6%AC%EC%99%80-%EA%B4%80%EB%A0%A8-%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85">ResponseBodyAdvice를 이용한 공통 응답 처리와, 관련 트러블 슈팅</a><br/>
 * 컨트롤러에서 반환하는 타입이 String이든, DTO든, 그 외의 것들이든, <br/>
 * 모두 ApiResponse로 감싸서 반환하고 싶은 경우에는 해당 해결책이 효과가 없다.<br/>
 * 이럴 때는 MappingJackson2HttpMessageConverter를 상속받아 Custom MessageConverter를 만들어서<br/>
 * 해당 커스텀 컨버터를 Spring의 메시지 컨버터 우선순위 중 가장 높은 우선순위로 밀어넣어보니 원하는 대로 동작했다.<br/>
 *
 * <p>강명원</p>
 * OnamsResponseHandler 에서 모든 응답을 해주는데.<br/>
 * Map, List 등 DTO타입이 아닌<br/>
 * String 등의 리턴값에선 OnamsResponseHandler가 동작하지 않아서.<br/>
 * Custom MessageConverter를 만들어 우선순위 변경<br/>
 * <h3>WebConfig에 등록해 우선순위 변경</h3>
 */
public class OnamsHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public OnamsHttpMessageConverter(ObjectMapper objectMapper) {
        super(objectMapper);
        objectMapper.registerModule(new JavaTimeModule());
        setObjectMapper(objectMapper);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return canWrite(mediaType);
    }
}
