package com.csdig.cms.model;

public class ChannelAttr implements java.io.Serializable{

    private static final long serialVersionUID = 0L;
    
   //public static final String DB_NAME = "cms";
    
    //columns START
    /**
     * attrId       db_column: attr_id 
     */     
    private java.lang.Integer attrId;
    /**
     * channelId       db_column: channel_id 
     */     
    private java.lang.Integer channelId;
    /**
     * attrName       db_column: attr_name 
     */     
    private java.lang.String attrName;
    /**
     * attrValue       db_column: attr_value 
     */     
    private java.lang.String attrValue;
    /**
     * priority       db_column: priority 
     */     
    private java.lang.Integer priority;
    //columns END
    
    // constructors
    public ChannelAttr(){
    }
    

    public void setAttrId(java.lang.Integer value) {
        this.attrId = value;
    }
    
    public java.lang.Integer getAttrId() {
        return this.attrId;
    }

    public void setChannelId(java.lang.Integer value) {
        this.channelId = value;
    }
    
    public java.lang.Integer getChannelId() {
        return this.channelId;
    }

    public void setAttrName(java.lang.String value) {
        this.attrName = value;
    }
    
    public java.lang.String getAttrName() {
        return this.attrName;
    }

    public void setAttrValue(java.lang.String value) {
        this.attrValue = value;
    }
    
    public java.lang.String getAttrValue() {
        return this.attrValue;
    }

    public void setPriority(java.lang.Integer value) {
        this.priority = value;
    }
    
    public java.lang.Integer getPriority() {
        return this.priority;
    }
    
}

    
