package com.onbrid.test.springboot.springboottest.service;

import com.onbrid.test.springboot.springboottest.dao.OnamsDao;
import com.onbrid.test.springboot.springboottest.model.OnBridOnamsData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Profile("dev")
@Service
public class TestAService extends TestService {

    public TestAService(OnamsDao onamsDao) {
        super(onamsDao);
    }

    public List testA(OnBridOnamsData onamsData) {
        log.debug("TestA testA");
        return null;
    }

}
