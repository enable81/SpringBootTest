package com.onbrid.test.springboot.springboottest.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * https://wonsjung.tistory.com/584
 */
@OpenAPIDefinition(
        info = @Info(title = "OnAMS API 명세서",
                description = "OnAMS API 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig {

}
