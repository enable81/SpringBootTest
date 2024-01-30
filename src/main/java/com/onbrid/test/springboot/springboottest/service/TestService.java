package com.onbrid.test.springboot.springboottest.service;

import com.onbrid.test.springboot.springboottest.dao.OnamsDao;
import com.onbrid.test.springboot.springboottest.model.OnBridOnamsData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestService extends OnamsService {

    final OnamsDao onamsDao;

    public List selectCommList(OnBridOnamsData onamsData) {
        log.debug(onamsData.toString());
        return onamsDao.customMethod();
    }
}
