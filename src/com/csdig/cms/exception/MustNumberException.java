package com.csdig.cms.exception;

import freemarker.template.TemplateModelException;

public class MustNumberException extends TemplateModelException {

	private static final long serialVersionUID = 1L;

	public MustNumberException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a number.");
	}
}
