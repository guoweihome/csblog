package com.csdig.cms.model;

public class AppType implements java.io.Serializable{

    private static final long serialVersionUID = 0L;
    
   //public static final String DB_NAME = "user";
    
    //columns START
    /**
     * id       db_column: id 
     */     
    private java.lang.Integer id;
    /**
     * name       db_column: name 
     */     
    private java.lang.String name;
    /**
     * tplFile       db_column: tpl_file 
     */     
    private java.lang.String tplFile;
    /**
     * path       db_column: path 
     */     
    private java.lang.String path;
    /**
     * displayOrder       db_column: display_order 
     */     
    private java.lang.Integer displayOrder;
    /**
     * blank       db_column: blank 
     */     
    private java.lang.Integer blank;
    /**
     * tplModel       db_column: tpl_model 
     */     
    private java.lang.String tplModel;
    //columns END
    
    // constructors
    public AppType(){
    }
    

    public void setId(java.lang.Integer value) {
        this.id = value;
    }
    
    public java.lang.Integer getId() {
        return this.id;
    }

    public void setName(java.lang.String value) {
        this.name = value;
    }
    
    public java.lang.String getName() {
        return this.name;
    }

    public void setTplFile(java.lang.String value) {
        this.tplFile = value;
    }
    
    public java.lang.String getTplFile() {
        return this.tplFile;
    }

    public void setPath(java.lang.String value) {
        this.path = value;
    }
    
    public java.lang.String getPath() {
        return this.path;
    }

    public void setDisplayOrder(java.lang.Integer value) {
        this.displayOrder = value;
    }
    
    public java.lang.Integer getDisplayOrder() {
        return this.displayOrder;
    }

    public void setBlank(java.lang.Integer value) {
        this.blank = value;
    }
    
    public java.lang.Integer getBlank() {
        return this.blank;
    }

    public void setTplModel(java.lang.String value) {
        this.tplModel = value;
    }
    
    public java.lang.String getTplModel() {
        return this.tplModel;
    }
    
}

    
