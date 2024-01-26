package com.onbrid.test.springboot.springboottest.model;

import java.io.Serializable;
import java.util.Map;


public class ReqData implements Serializable {

    private Map map;


    public Map getVariableMap() {
        return (Map) this.map.get("VARIABLE_MAP");
    }

    public Map getAllData()
    {
        return this.map;
    }
}
