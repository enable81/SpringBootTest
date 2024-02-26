package com.onbrid.test.springboot.springboottest;

import com.onbrid.test.springboot.springboottest.properties.FilePathProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Map;

@RequiredArgsConstructor
@SpringBootApplication
public class SpringBootTestApplication {

    // TODO: Exception ㅊㅓ리
    // TODO:   - 전체적인(컨트롤러 말고)에러 를 받아줄 에러랜들러  BasicErrorController ??
    // Message param 처리..
    // TODO: JWT / security
    // TODO: file upload/download
    // excel
    // TODO: report

    // log..
    // TODO: root cause stack log을 DB에 저장.

    // TODO: Project 모듈화 구성.
    // TODO: gradle 빌드.
    // TODO: 도커 배포 파일 만들기 & 배포. & 실행?


    final FilePathProperties filePathProperties;


    @PostConstruct
    public void init() {
        File dir = new File(filePathProperties.getFilePath());
        if (dir != null && !dir.exists()) {
            dir.mkdirs();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestApplication.class, args);
    }

}
