package com.onbrid.test.springboot.springboottest.service;

import com.onbrid.test.springboot.springboottest.dao.OnamsDao;
import com.onbrid.test.springboot.springboottest.model.ReqData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestService extends OnamsService {

    final OnamsDao onamsDao;

    public List selectCommList(ReqData reqData) {
        return onamsDao.customMethod();
    }
}
