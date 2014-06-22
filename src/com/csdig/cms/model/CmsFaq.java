package com.csdig.cms.model;

public class CmsFaq implements java.io.Serializable{

    private static final long serialVersionUID = 0L;
    
   //public static final String DB_NAME = "user";
    
    //columns START
    /**
     * faqId       db_column: faq_id 
     */     
    private java.lang.Integer faqId;
    /**
     * channelId       db_column: channel_id 
     */     
    private java.lang.Integer channelId;
    /**
     * ����       db_column: title 
     */     
    private java.lang.String title;
    /**
     * ����       db_column: desc 
     */     
    private java.lang.String desc;
    
    private String channelName;
    //columns END
    
    // constructors
    public CmsFaq(){
    }
    

    public void setFaqId(java.lang.Integer value) {
        this.faqId = value;
    }
    
    public java.lang.Integer getFaqId() {
        return this.faqId;
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

    public void setDesc(java.lang.String value) {
        this.desc = value;
    }
    
    public java.lang.String getDesc() {
        return this.desc;
    }


	public String getChannelName() {
		return channelName;
	}


	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
    
}

    
