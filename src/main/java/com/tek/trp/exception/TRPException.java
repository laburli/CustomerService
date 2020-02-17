package com.tek.trp.exception;


/**
 * @author raadari
 *
 */
public class TRPException extends BusinessException {

	private static final long serialVersionUID = -6177939907218052963L;

	public TRPException(ErrorCode errorCode) {
		super(errorCode.getHttpStatus(), errorCode.name(), errorCode.getMessage());
	}

	public TRPException(ErrorCode errorCode, String customMessage) {
		super(errorCode.getHttpStatus(), errorCode.name(), errorCode.getMessage(), customMessage);
	}

	public TRPException(Throwable cause, ErrorCode errorCode) {
		super(cause, errorCode.getHttpStatus(), errorCode.name(), errorCode.getMessage());
	}


}
