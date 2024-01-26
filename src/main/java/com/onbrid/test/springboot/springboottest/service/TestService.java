package com.onbrid.test.springboot.springboottest.service;

import com.onbrid.test.springboot.springboottest.model.ReqData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService extends OnamsService {

    public ReqData selectCommList(ReqData reqData) {
        log.debug("TestServiceImpl.selectCommList");
        return null;
    }
}
