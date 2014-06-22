package com.csdig.cms.model;

import java.util.List;

public class CmsChannel implements java.io.Serializable{

    private static final long serialVersionUID = 0L;
    
   //public static final String DB_NAME = "user";
    
    //columns START
    /**
     * channelId       db_column: channel_id 
     */     
    private java.lang.Integer channelId;
    /**
     * ģ��ID       db_column: model_id 
     */     
    private java.lang.Integer modelId;
    /**
     * ����·��       db_column: channel_path 
     */     
    private java.lang.String channelPath;
    /**
     * ����˳��       db_column: priority 
     */     
    private java.lang.Integer priority;
    /**
     * name       db_column: name 
     */     
    private java.lang.String name;
    /**
     * appType       db_column: app_type 
     */     
    private java.lang.Integer appType;
    /**
     * ����ĿID       db_column: parent_id 
     */     
    private java.lang.Integer parentId;
    /**
     * ���ڵ���ӽڵ��Ƿ���ڹ��� 0.û�й��� 1.�й���       db_column: is_pc_relat 
     */     
    private java.lang.Boolean isPcRelat;
    
    private String modelName;
    
    private String parentName;
    
    private List<CmsChannelAttr> channelAttrs;
    
    private java.lang.Integer channelType;
    //columns END
    
    // constructors
    public CmsChannel(){
    }
    

    public void setChannelId(java.lang.Integer value) {
        this.channelId = value;
    }
    
    public java.lang.Integer getChannelId() {
        return this.channelId;
    }

    public void setModelId(java.lang.Integer value) {
        this.modelId = value;
    }
    
    public java.lang.Integer getModelId() {
        return this.modelId;
    }

    public void setChannelPath(java.lang.String value) {
        this.channelPath = value;
    }
    
    public java.lang.String getChannelPath() {
        return this.channelPath;
    }

    public void setPriority(java.lang.Integer value) {
        this.priority = value;
    }
    
    public java.lang.Integer getPriority() {
        return this.priority;
    }

    public void setName(java.lang.String value) {
        this.name = value;
    }
    
    public java.lang.String getName() {
        return this.name;
    }

  

    public java.lang.Integer getAppType() {
		return appType;
	}


	public void setAppType(java.lang.Integer appType) {
		this.appType = appType;
	}


	public void setParentId(java.lang.Integer value) {
        this.parentId = value;
    }
    
    public java.lang.Integer getParentId() {
        return this.parentId;
    }

    public void setIsPcRelat(java.lang.Boolean value) {
        this.isPcRelat = value;
    }
    
    public java.lang.Boolean getIsPcRelat() {
        return this.isPcRelat;
    }


	public String getModelName() {
		return modelName;
	}


	public void setModelName(String modelName) {
		this.modelName = modelName;
	}


	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	public List<CmsChannelAttr> getChannelAttrs() {
		return channelAttrs;
	}


	public void setChannelAttrs(List<CmsChannelAttr> channelAttrs) {
		this.channelAttrs = channelAttrs;
	}


	public java.lang.Integer getChannelType() {
		return channelType;
	}


	public void setChannelType(java.lang.Integer channelType) {
		this.channelType = channelType;
	}



    
}

    
