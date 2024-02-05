package com.onbrid.test.springboot.springboottest.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * <a href="https://wonsjung.tistory.com/584">Spring Boot OpenAPI 3.0 + Swagger version 3 적용</a><br/>
 * spring boot 3 swagger 3 404 not found WebMvcConfigurationSupport<br/>
 *  - <a href="https://stackoverflow.com/questions/77500663/swagger-ui-index-html-does-not-exists-404-spring-boot-3-1-5">swagger-ui -> index.html does not exists 404 spring boot 3.1.5</a>
 */
@OpenAPIDefinition(
        info = @Info(title = "OnAMS API 명세서",
                description = "OnAMS API 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig {

}
