/*
 *  Date: Feb 9, 2012 10:59:14 AM
 *  Copyright (c) 2012 asiainfo-linkage
 */
package com.csdig.db.core;

import java.sql.SQLException;

public interface ColumnFilter {

    public Object doFilter(String columnName, Object value) throws SQLException;

}
