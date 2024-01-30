package com.onbrid.test.springboot.springboottest.exception;

import java.io.Serial;

public class JsonParsingException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8586683350682455965L;


    public JsonParsingException(String msg) {
        super(msg);
    }

    public JsonParsingException(Exception e) {
        super(e);
    }

    public JsonParsingException(String msg, Exception e) {
        super(msg, e);
    }
}
