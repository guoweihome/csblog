package com.csdig.cms.model;

public class Content implements java.io.Serializable{

    private static final long serialVersionUID = 0L;
    
   //public static final String DB_NAME = "cms";
    
    //columns START
    /**
     * contentId       db_column: content_id 
     */     
    private java.lang.Integer contentId;
    /**
     * channelId       db_column: channel_id 
     */     
    private java.lang.Integer channelId;
    /**
     * title       db_column: title 
     */     
    private java.lang.String title;
    /**
     * description       db_column: description 
     */     
    private java.lang.String description;
    /**
     * link       db_column: link 
     */     
    private java.lang.String link;
    /**
     * releaseDate       db_column: release_date 
     */     
    private java.util.Date releaseDate;
    /**
     * viewCount       db_column: view_count 
     */     
    private java.lang.Integer viewCount;
    /**
     * ups       db_column: ups 
     */     
    private java.lang.Integer ups;
    /**
     * downs       db_column: downs 
     */     
    private java.lang.Integer downs;
    //columns END
    
    // constructors
    public Content(){
    }
    

    public void setContentId(java.lang.Integer value) {
        this.contentId = value;
    }
    
    public java.lang.Integer getContentId() {
        return this.contentId;
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

    public void setDescription(java.lang.String value) {
        this.description = value;
    }
    
    public java.lang.String getDescription() {
        return this.description;
    }

    public void setLink(java.lang.String value) {
        this.link = value;
    }
    
    public java.lang.String getLink() {
        return this.link;
    }

    public void setReleaseDate(java.util.Date value) {
        this.releaseDate = value;
    }
    
    public java.util.Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setViewCount(java.lang.Integer value) {
        this.viewCount = value;
    }
    
    public java.lang.Integer getViewCount() {
        return this.viewCount;
    }

    public void setUps(java.lang.Integer value) {
        this.ups = value;
    }
    
    public java.lang.Integer getUps() {
        return this.ups;
    }

    public void setDowns(java.lang.Integer value) {
        this.downs = value;
    }
    
    public java.lang.Integer getDowns() {
        return this.downs;
    }
    
}

    
