package com.csdig.cms.model;

public class ContentTxt implements java.io.Serializable{

    private static final long serialVersionUID = 0L;
    
   //public static final String DB_NAME = "cms";
    
    //columns START
    /**
     * contentId       db_column: content_id 
     */     
    private java.lang.Integer contentId;
    /**
     * txt       db_column: txt 
     */     
    private java.lang.String txt;
    /**
     * txt1       db_column: txt1 
     */     
    private java.lang.String txt1;
    //columns END
    
    // constructors
    public ContentTxt(){
    }
    

    public void setContentId(java.lang.Integer value) {
        this.contentId = value;
    }
    
    public java.lang.Integer getContentId() {
        return this.contentId;
    }

    public void setTxt(java.lang.String value) {
        this.txt = value;
    }
    
    public java.lang.String getTxt() {
        return this.txt;
    }

    public void setTxt1(java.lang.String value) {
        this.txt1 = value;
    }
    
    public java.lang.String getTxt1() {
        return this.txt1;
    }
    
}

    
