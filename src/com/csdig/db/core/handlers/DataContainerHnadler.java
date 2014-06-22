/*
*  Date: 2011-7-31 下午03:19:27
*  Copyright (c) 2011 asiainfo-linkage
*/
package com.csdig.db.core.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.csdig.db.core.BasicRowProcessor;
import com.csdig.db.core.ColumnFilter;
import com.csdig.db.core.DataContainer;
import com.csdig.db.core.RowProcessor;

public class DataContainerHnadler extends AbstractListHandler<DataContainer> {
    
    /**
     * The RowProcessor implementation to use when converting rows 
     * into Maps.
     */
    private final RowProcessor convert;

    /** 
     * Creates a new instance of MapListHandler using a 
     * <code>BasicRowProcessor</code> for conversion.
     */
    public DataContainerHnadler() {
        this(ArrayHandler.ROW_PROCESSOR);
    }
    
    public DataContainerHnadler(ColumnFilter filter) {
        this(new BasicRowProcessor(filter));
    }

    /** 
     * Creates a new instance of MapListHandler.
     * 
     * @param convert The <code>RowProcessor</code> implementation 
     * to use when converting rows into Maps.
     */
    public DataContainerHnadler(RowProcessor convert) {
        super();
        this.convert = convert;
    }

    /**
     * Converts the <code>ResultSet</code> row into a <code>Map</code> object.
     * @param rs <code>ResultSet</code> to process.
     * @return A <code>Map</code>, never null.  
     * 
     * @throws SQLException if a database access error occurs
     * 
     * @see org.apache.commons.dbutils.handlers.AbstractListHandler#handle(ResultSet)
     */
    protected DataContainer handleRow(ResultSet rs) throws SQLException {
        return this.convert.toDataContainer(rs);
    }

}
