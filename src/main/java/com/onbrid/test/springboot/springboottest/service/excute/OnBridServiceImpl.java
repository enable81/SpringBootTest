package com.onbrid.test.springboot.springboottest.service.excute;

import com.onbrid.test.springboot.springboottest.exception.OnBridExceptionWithCommit;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class OnBridServiceImpl implements OnBridService {
    @Override
    @Transactional(rollbackFor={Exception.class}, noRollbackFor={OnBridExceptionWithCommit.class})
    public Object execute(String methodName, Object param) {
        return invoke(methodName, param);
    }


    protected final Object invoke(String methodName, Object param) {

        String[] methodsDoNotInvoke = new String[] {"execute","executeReadOnly","invoke","executeNewTx","executeNewTxAndReadUncommited","executeNestedTx"};
        List methodsDoNotInvokeList = Arrays.asList(methodsDoNotInvoke);

        try {

            if (!methodsDoNotInvokeList.contains(methodName)) {

                Method method = null;

                Exception exception = null;

                try {
                    method = this.getClass().getMethod(methodName, param.getClass());
                    return method.invoke(this, param);
                } catch (NoSuchMethodException e) {
                    exception = e;
                } catch (Exception e) {
                    throw e;
                }


                try {
                    method = this.getClass().getMethod(methodName, param.getClass().getSuperclass());
                    return method.invoke(this, param);
                } catch (NoSuchMethodException e) {
                    // Next
                } catch (Exception e) {
                    throw e;
                }


                Class[] clazz = param.getClass().getInterfaces();
                for(int n=0; n<clazz.length; n++) {
                    try {
                        method = this.getClass().getMethod(methodName, clazz[n]);
                        return method.invoke(this, param);
                    } catch (NoSuchMethodException e) {
                        // Next
                    } catch (Exception e) {
                        throw e;
                    }
                }

                throw new RuntimeException(exception);
            }
            else {
                throw new RuntimeException("It's forbidden to invoke method : " + methodName);
            }

        }
        // java reflection 으로 메서드 invoke 하기 때문에 예외는 InvocationTargetException 이다. root cause 를 찾아서 던진다.
        catch (Throwable ex) {
            Throwable cause = ex.getCause();
            Throwable rootCause = cause==null?ex:null;
            while(cause != null)
            {
                rootCause = cause;
                cause = cause.getCause();
            }

            if(rootCause != null && rootCause.toString().contains("ValueChainExceptionWithCommit"))
            {
                throw new OnBridExceptionWithCommit(rootCause);
            }
            else
            {
                throw new RuntimeException(rootCause);
            }
        }

    }

}
