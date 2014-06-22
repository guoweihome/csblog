package com.csdig.cms.model;

import java.util.List;

public class CmsAppChangeLog implements java.io.Serializable{

    private static final long serialVersionUID = 0L;
    
   //public static final String DB_NAME = "user";
    
    //columns START
    /**
     * changeLogId       db_column: change_log_id 
     */     
    private java.lang.Integer changeLogId;
    /**
     * channelId       db_column: channel_id 
     */     
    private java.lang.Integer channelId;
    /**
     * ����       db_column: title 
     */     
    private java.lang.String title;
    /**
     * ��������       db_column: dis_date 
     */     
    private java.lang.String disDate;
    /**
     * app���� 1.android 2.IOS       db_column: app_type 
     */     
    private java.lang.Integer appType;
    
    private String channelName;
    
    private List<CmsAppChangeLogItem> logItems;
    
    private String appTypeName;
    
    //columns END
    
    // constructors
    public CmsAppChangeLog(){
    }
    

    public void setChangeLogId(java.lang.Integer value) {
        this.changeLogId = value;
    }
    
    public java.lang.Integer getChangeLogId() {
        return this.changeLogId;
    }

    public void setChannelId(java.lang.Integer value) {
        this.channelId = value;
    }
    
    public java.lang.Integer getChannelId() {
        return this.channelId;
    }

    public void setTitle(java.lang.String value) {
        this.title = value;
    }
    
    public java.lang.String getTitle() {
        return this.title;
    }

    public void setDisDate(java.lang.String value) {
        this.disDate = value;
    }
    
    public java.lang.String getDisDate() {
        return this.disDate;
    }


	public java.lang.Integer getAppType() {
		return appType;
	}


	public void setAppType(java.lang.Integer appType) {
		this.appType = appType;
	}


	public List<CmsAppChangeLogItem> getLogItems() {
		return logItems;
	}


	public void setLogItems(List<CmsAppChangeLogItem> logItems) {
		this.logItems = logItems;
	}


	public String getChannelName() {
		return channelName;
	}


	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}


	public String getAppTypeName() {
		return appTypeName;
	}


	public void setAppTypeName(String appTypeName) {
		this.appTypeName = appTypeName;
	}

    
}

    
