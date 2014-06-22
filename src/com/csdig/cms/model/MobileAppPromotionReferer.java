package com.csdig.cms.model;

public class MobileAppPromotionReferer implements java.io.Serializable{

    private static final long serialVersionUID = 0L;
    
   //public static final String DB_NAME = "user";
    
    //columns START
    /**
     * id       db_column: id 
     */     
    private java.lang.Long id;
    /**
     * Ӧ�����       db_column: appid 
     */     
    private java.lang.String appid;
    /**
     * ��Դurl       db_column: referer 
     */     
    private java.lang.String referer;
    /**
     * ��Ӧapk����       db_column: apkname 
     */     
    private java.lang.String apkname;
    /**
     * ���༭ʱ��       db_column: editdate 
     */     
    private java.util.Date editdate;
    /**
     * �Ƿ�����: 0-������; 1-����       db_column: active 
     */     
    private Integer active;
    /**
     * ����       db_column: description 
     */     
    private java.lang.String description;
    //columns END
    
    // constructors
    public MobileAppPromotionReferer(){
    }
    

    public void setId(java.lang.Long value) {
        this.id = value;
    }
    
    public java.lang.Long getId() {
        return this.id;
    }

    public void setAppid(java.lang.String value) {
        this.appid = value;
    }
    
    public java.lang.String getAppid() {
        return this.appid;
    }

    public void setReferer(java.lang.String value) {
        this.referer = value;
    }
    
    public java.lang.String getReferer() {
        return this.referer;
    }

    public void setApkname(java.lang.String value) {
        this.apkname = value;
    }
    
    public java.lang.String getApkname() {
        return this.apkname;
    }

    public void setEditdate(java.util.Date value) {
        this.editdate = value;
    }
    
    public java.util.Date getEditdate() {
        return this.editdate;
    }

    public void setActive(Integer value) {
        this.active = value;
    }
    
    public Integer getActive() {
        return this.active;
    }

    public void setDescription(java.lang.String value) {
        this.description = value;
    }
    
    public java.lang.String getDescription() {
        return this.description;
    }
    
}

    
