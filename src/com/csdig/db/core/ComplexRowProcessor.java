/*
*  Date: Feb 15, 2012 9:56:28 PM
*  Copyright (c) 2012 asiainfo-linkage
*/
package com.csdig.db.core;

public class ComplexRowProcessor extends BasicRowProcessor {

    private ColumnFilter filter;
    
    public ComplexRowProcessor(ColumnFilter filter){
        super(filter);
    }

    public ColumnFilter getFilter() {
        return filter;
    }

    public void setFilter(ColumnFilter filter) {
        this.filter = filter;
    } 
    
    
    
}
