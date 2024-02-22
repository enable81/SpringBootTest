package com.onbrid.test.springboot.springboottest.model;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Setter
@Getter
@ToString
public class OnBridOnamsData implements Serializable {

    @Serial
    private static final long serialVersionUID = -7604534086257395588L;

    /**
     * {"UNIVNO": "0001", "ASSETNO": "1234", "LIST": [{}, {},,,{}]}
     */
    private Map paramMap;

    private List<Map> list;

    private List<Map> excelColumns;

    public HttpServletRequest request = null;
}
