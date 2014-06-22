package com.csdig.cms.exception;

import freemarker.template.TemplateModelException;

public class MustDateException extends TemplateModelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public MustDateException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a date.");
	}
}
