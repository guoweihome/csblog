package com.csdig.cms.exception;


/**
 * @author virgilguo
 * 
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errcode;
	
	public BusinessException() {
		super();
	}

	public BusinessException(String errcode,String message) {
		super(message);
		this.errcode = errcode;
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	
}
