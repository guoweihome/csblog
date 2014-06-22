package com.csdig.cms.exception;

import freemarker.template.TemplateModelException;

public class MustStringException extends TemplateModelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MustStringException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a string.");
	}
}
