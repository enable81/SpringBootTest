package com.onbrid.test.springboot.springboottest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Slf4j
@Primary
public class TestServiceImpl implements TestService {

    @Override
    public List selectCommList() {
        log.info("TestServiceImpl.selectCommList");
        return null;
    }
}
