package com.csdig.cms.exception;

import freemarker.template.TemplateModelException;

public class MustBooleanException extends TemplateModelException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MustBooleanException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a boolean.");
	}
}
