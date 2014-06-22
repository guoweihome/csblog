package com.csdig.cms.exception;

import freemarker.template.TemplateModelException;

public class MustSplitNumberException extends TemplateModelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MustSplitNumberException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a number split by ','");
	}

	public MustSplitNumberException(String paramName, Exception cause) {
		super("The \"" + paramName + "\" parameter must be a number split by ','", cause);
	}
}
