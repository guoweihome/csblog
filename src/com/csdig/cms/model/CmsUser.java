package com.csdig.cms.model;

import java.util.Date;

public class CmsUser implements java.io.Serializable {
	private static final long serialVersionUID = 0L;
	
	private java.lang.Integer id;
	private String telNum;
	private String pwd;
	private Date generateTime;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getTelNum() {
		return telNum;
	}

	public void setTelNum(java.lang.String telNum) {
		this.telNum = telNum;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}

}
