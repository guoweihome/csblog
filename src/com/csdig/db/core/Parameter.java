/*
 *  Date: 2011-7-20 下午11:46:08
 *  Copyright (c) 2011 asiainfo-linkage
 */
package com.csdig.db.core;

import java.util.HashMap;
import java.util.Map;

public class Parameter {

    private Map<String, Object> paramMap;

    public Parameter() {
        paramMap = new HashMap<String, Object>();
    }

    public void append(String key, Object value) {
        if (!paramMap.containsKey(key)) {
            paramMap.put(key, value);
        }
    }

    public Map<String, Object> getParamMap() {
        return this.paramMap;
    }

    
    
}
