package com.onbrid.test.springboot.springboottest.model;

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

    public static final String PARAM_KEY = "PARAM_MAP";

    /**
     * {"UNIVNO": "0001", "ASSETNO": "1234", "LIST": [{}, {},,,{}]}
     */
    private HashMap map;

    public OnBridOnamsData() {
        this.map = new HashMap();
    }

    public void setParameterMap(Map paramMap) {
        this.map.put(PARAM_KEY, paramMap);
    }

    public void setDataList(String id, List list) {
        this.map.put(id, list);
    }

}
