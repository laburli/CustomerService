package com.tek.trp.exception;

import java.io.Serializable;
/**
 * @author raadari
 *
 */
public class ErrorDetail implements Serializable  {


	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;

	public ErrorDetail(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
