package com.csdig.cms.model;

public class CmsAppChangeLogItem implements java.io.Serializable{

    private static final long serialVersionUID = 0L;
    
   //public static final String DB_NAME = "user";
    
    //columns START
    /**
     * changeLogItemId       db_column: change_log_item_id 
     */     
    private java.lang.Integer changeLogItemId;
    /**
     * changeLogId       db_column: change_log_id 
     */     
    private java.lang.Integer changeLogId;
    /**
     * ����       db_column: desc 
     */     
    private java.lang.String desc;
    /**
     * ����˳��       db_column: priority 
     */     
    private java.lang.Integer priority;
    //columns END
    
    // constructors
    public CmsAppChangeLogItem(){
    }
    

    public void setChangeLogItemId(java.lang.Integer value) {
        this.changeLogItemId = value;
    }
    
    public java.lang.Integer getChangeLogItemId() {
        return this.changeLogItemId;
    }

    public void setChangeLogId(java.lang.Integer value) {
        this.changeLogId = value;
    }
    
    public java.lang.Integer getChangeLogId() {
        return this.changeLogId;
    }

    public void setDesc(java.lang.String value) {
        this.desc = value;
    }
    
    public java.lang.String getDesc() {
        return this.desc;
    }

    public void setPriority(java.lang.Integer value) {
        this.priority = value;
    }
    
    public java.lang.Integer getPriority() {
        return this.priority;
    }
    
}

    
