package com.csdig.db.model;

import com.csdig.db.core.Parameter;


public class Sql {

    private StringBuffer sqlStr = new StringBuffer();

    private Parameter param;

    public Sql() {
    }

    public Sql(String sql) {
        sqlStr.append(sql);
    }

    public Sql append(String sql) {
        sqlStr.append(sql);
        return this;
    }

    public Sql preAppend(String sql) {
        sqlStr = new StringBuffer(sql + sqlStr.toString());
        return this;
    }

    public Sql set(String name, Object value) {
        if (param == null) {
            param = new Parameter();
        }
        param.append(name, value);
        return this;
    }

    public Parameter getParam() {
        return param;
    }

    public Sql setParam(Parameter param) {
        this.param = param;
        return this;
    }

    public String toString() {
        return sqlStr.toString();
    }

    public String getCountSql() {
        return "select count(*) from (" + sqlStr + ")count_tb";
    }
}
