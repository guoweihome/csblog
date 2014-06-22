package com.csdig.cms.model;

public class CmsChannelAttr implements java.io.Serializable{

    private static final long serialVersionUID = 0L;
    
   //public static final String DB_NAME = "user";
    
    //columns START
    /**
     * channelAttrId       db_column: channel_attr_id 
     */     
    private java.lang.Integer channelAttrId;
    /**
     * channelId       db_column: channel_id 
     */     
    private java.lang.Integer channelId;
    /**
     * ���       db_column: attr_name 
     */     
    private java.lang.String attrName;
    /**
     * ֵ       db_column: attr_value 
     */     
    private java.lang.String attrValue;
    /**
     * priority       db_column: priority 
     */     
    private java.lang.Integer priority;
    
    private CmsModelItem modelItem;
    //columns END
    
    private java.lang.Boolean isSingle;
    private java.lang.Boolean isRequired;
    private java.lang.String defValue;
    private java.lang.String optValue;
    
    // constructors
    public CmsChannelAttr(){
    }
    

    public void setChannelAttrId(java.lang.Integer value) {
        this.channelAttrId = value;
    }
    
    public java.lang.Integer getChannelAttrId() {
        return this.channelAttrId;
    }

    public void setChannelId(java.lang.Integer value) {
        this.channelId = value;
    }
    
    public java.lang.Integer getChannelId() {
        return this.channelId;
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

    public void setPriority(java.lang.Integer value) {
        this.priority = value;
    }
    
    public java.lang.Integer getPriority() {
        return this.priority;
    }

	public CmsModelItem getModelItem() {
		return modelItem;
	}

	public void setModelItem(CmsModelItem modelItem) {
		this.modelItem = modelItem;
	}

	public java.lang.Boolean getIsSingle() {
		return isSingle;
	}


	public void setIsSingle(java.lang.Boolean isSingle) {
		this.isSingle = isSingle;
	}


	public java.lang.Boolean getIsRequired() {
		return isRequired;
	}


	public void setIsRequired(java.lang.Boolean isRequired) {
		this.isRequired = isRequired;
	}


	public java.lang.String getDefValue() {
		return defValue;
	}


	public void setDefValue(java.lang.String defValue) {
		this.defValue = defValue;
	}


	public java.lang.String getOptValue() {
		return optValue;
	}


	public void setOptValue(java.lang.String optValue) {
		this.optValue = optValue;
	}
    
}

    
