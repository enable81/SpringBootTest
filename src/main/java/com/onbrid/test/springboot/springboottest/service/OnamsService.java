package com.onbrid.test.springboot.springboottest.service;

import com.onbrid.test.springboot.springboottest.exception.OnBridExceptionWithCommit;
import com.onbrid.test.springboot.springboottest.service.excute.OnBridServiceImpl;
import org.springframework.transaction.annotation.Transactional;

public class OnamsService extends OnBridServiceImpl {

    @Transactional(value="onamsTransactionManager", rollbackFor={Exception.class}, noRollbackFor={OnBridExceptionWithCommit.class})
    public Object execute(String methodName, Object param)
    {
        return invoke(methodName, param);
    }

}
