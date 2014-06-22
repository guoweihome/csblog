package com.csdig.cms.model;

public class CmsModel implements java.io.Serializable{

    private static final long serialVersionUID = 0L;
    
   //public static final String DB_NAME = "user";
    
    //columns START
    /**
     * modelId       db_column: model_id 
     */     
    private java.lang.Integer modelId;
    /**
     * ���       db_column: model_name 
     */     
    private java.lang.String modelName;
    /**
     * ·��       db_column: model_path 
     */     
    private java.lang.String modelPath;
    /**
     * ����˳��       db_column: priority 
     */     
    private java.lang.Integer priority;
    /**
     * �Ƿ����       db_column: is_disabled 
     */     
    private java.lang.Boolean isDisabled;
    /**
     * �Ƿ�Ĭ��ģ��       db_column: is_def 
     */     
    private java.lang.Boolean isDef=Boolean.TRUE;
    //columns END
    
    // constructors
    public CmsModel(){
    }
    

    public void setModelId(java.lang.Integer value) {
        this.modelId = value;
    }
    
    public java.lang.Integer getModelId() {
        return this.modelId;
    }

    public void setModelName(java.lang.String value) {
        this.modelName = value;
    }
    
    public java.lang.String getModelName() {
        return this.modelName;
    }

    public void setModelPath(java.lang.String value) {
        this.modelPath = value;
    }
    
    public java.lang.String getModelPath() {
        return this.modelPath;
    }

    public void setPriority(java.lang.Integer value) {
        this.priority = value;
    }
    
    public java.lang.Integer getPriority() {
        return this.priority;
    }

    public void setIsDisabled(java.lang.Boolean value) {
        this.isDisabled = value;
    }
    
    public java.lang.Boolean getIsDisabled() {
        return this.isDisabled;
    }

    public void setIsDef(java.lang.Boolean value) {
        this.isDef = value;
    }
    
    public java.lang.Boolean getIsDef() {
        return this.isDef;
    }
    
}

    
