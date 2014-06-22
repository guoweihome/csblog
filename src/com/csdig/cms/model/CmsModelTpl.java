package com.csdig.cms.model;

public class CmsModelTpl implements java.io.Serializable{

    private static final long serialVersionUID = 0L;
    
   //public static final String DB_NAME = "user";
    
    //columns START
    /**
     * id       db_column: id 
     */     
    private java.lang.Integer id;
    /**
     * modelId       db_column: model_id 
     */     
    private java.lang.Integer modelId;
    /**
     * ����       db_column: txt 
     */     
    private java.lang.String txt;
    //columns END
    
    // constructors
    public CmsModelTpl(){
    }
    

    public void setId(java.lang.Integer value) {
        this.id = value;
    }
    
    public java.lang.Integer getId() {
        return this.id;
    }

    public void setModelId(java.lang.Integer value) {
        this.modelId = value;
    }
    
    public java.lang.Integer getModelId() {
        return this.modelId;
    }

    public void setTxt(java.lang.String value) {
        this.txt = value;
    }
    
    public java.lang.String getTxt() {
        return this.txt;
    }
    
}

    
