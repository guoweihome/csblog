package com.csdig.cms.model;

public class ContentTag implements java.io.Serializable{

    private static final long serialVersionUID = 0L;
    
   //public static final String DB_NAME = "cms";
    
    //columns START
    /**
     * contentId       db_column: content_id 
     */     
    private java.lang.Integer contentId;
    /**
     * tagId       db_column: tag_id 
     */     
    private java.lang.Integer tagId;
    //columns END
    
    // constructors
    public ContentTag(){
    }
    

    public void setContentId(java.lang.Integer value) {
        this.contentId = value;
    }
    
    public java.lang.Integer getContentId() {
        return this.contentId;
    }

    public void setTagId(java.lang.Integer value) {
        this.tagId = value;
    }
    
    public java.lang.Integer getTagId() {
        return this.tagId;
    }
    
}

    
