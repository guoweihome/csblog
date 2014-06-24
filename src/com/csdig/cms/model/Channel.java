package com.csdig.cms.model;

import java.util.ArrayList;
import java.util.List;

public class Channel implements java.io.Serializable{

    private static final long serialVersionUID = 0L;
    
   //public static final String DB_NAME = "cms";
    
    //columns START
    /**
     * channelId       db_column: channel_id 
     */     
    private java.lang.Integer channelId;
    /**
     * channelName       db_column: channel_name 
     */     
    private java.lang.String channelName;
    /**
     * channelPath       db_column: channel_path 
     */     
    private java.lang.String channelPath;
    /**
     * isDisplay       db_column: is_display 
     */     
    private java.lang.Boolean isDisplay;
    /**
     * tplPath       db_column: tpl_path 
     */     
    private java.lang.String tplPath;
    /**
     * priority       db_column: priority 
     */     
    private java.lang.Integer priority;
    /**
     * canStatic       db_column: can_static 
     */     
    private java.lang.Boolean canStatic;
    /**
     * parentId       db_column: parent_id 
     */     
    private java.lang.Integer parentId;
    /**
     * isSingle       db_column: is_single 
     */     
    private java.lang.Boolean isSingle;
    //columns END
    
    private List<ChannelAttr> attrs = new ArrayList<ChannelAttr>();
    
    // constructors
    public Channel(){
    }
    

    public void setChannelId(java.lang.Integer value) {
        this.channelId = value;
    }
    
    public java.lang.Integer getChannelId() {
        return this.channelId;
    }

    public void setChannelName(java.lang.String value) {
        this.channelName = value;
    }
    
    public java.lang.String getChannelName() {
        return this.channelName;
    }

    public void setChannelPath(java.lang.String value) {
        this.channelPath = value;
    }
    
    public java.lang.String getChannelPath() {
        return this.channelPath;
    }

    public void setIsDisplay(java.lang.Boolean value) {
        this.isDisplay = value;
    }
    
    public java.lang.Boolean getIsDisplay() {
        return this.isDisplay;
    }

    public void setTplPath(java.lang.String value) {
        this.tplPath = value;
    }
    
    public java.lang.String getTplPath() {
        return this.tplPath;
    }

    public void setPriority(java.lang.Integer value) {
        this.priority = value;
    }
    
    public java.lang.Integer getPriority() {
        return this.priority;
    }

    public void setCanStatic(java.lang.Boolean value) {
        this.canStatic = value;
    }
    
    public java.lang.Boolean getCanStatic() {
        return this.canStatic;
    }

    public void setParentId(java.lang.Integer value) {
        this.parentId = value;
    }
    
    public java.lang.Integer getParentId() {
        return this.parentId;
    }

    public void setIsSingle(java.lang.Boolean value) {
        this.isSingle = value;
    }
    
    public java.lang.Boolean getIsSingle() {
        return this.isSingle;
    }


	public List<ChannelAttr> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<ChannelAttr> attrs) {
		this.attrs = attrs;
	}
    
}

    
