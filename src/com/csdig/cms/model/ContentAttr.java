package com.csdig.cms.model;

public class ContentAttr implements java.io.Serializable{

    private static final long serialVersionUID = 0L;
    
   //public static final String DB_NAME = "cms";
    
    //columns START
    /**
     * contentId       db_column: content_id 
     */     
    private java.lang.Integer contentId;
    /**
     * attrName       db_column: attr_name 
     */     
    private java.lang.String attrName;
    /**
     * attrValue       db_column: attr_value 
     */     
    private java.lang.String attrValue;
    //columns END
    
    // constructors
    public ContentAttr(){
    }
    

    public void setContentId(java.lang.Integer value) {
        this.contentId = value;
    }
    
    public java.lang.Integer getContentId() {
        return this.contentId;
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
    
}

    
