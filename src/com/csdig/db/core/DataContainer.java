/*
 *  Date: 2011-7-31 下午03:10:32
 *  Copyright (c) 2011 asiainfo-linkage
 */
package com.csdig.db.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataContainer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> data;

    public DataContainer() {
    }

    public DataContainer(Map<String, Object> map) {
        this.data = map;
    }

    public void set(String name, String value) {
        if (data == null) {
            data = new HashMap<String, Object>();
        }
        if (!data.containsKey(name)) {
            data.put(name, value);
        }
    }

    public Object get(String name) {
        return data == null ? null : data.get(name);
    }

    public void setData(Map<String, Object> map) {
        data = map;
    }

}
