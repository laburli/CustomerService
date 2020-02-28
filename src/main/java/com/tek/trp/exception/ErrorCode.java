
/**
 * Error Codes , Status and Messages.
 */
package com.tek.trp.exception;

import org.springframework.http.HttpStatus;

/**
 * @author raadari
 *
 */
public enum ErrorCode {

	CUSTOMERID_NOT_FOUND("CustomerId not found", HttpStatus.NOT_FOUND),
	EMPTY_INPUT("Input should not be Empty", HttpStatus.BAD_REQUEST),
	IN_VALID_INPUT("Input was not valid or Mandatory fileds should not be empty", HttpStatus.BAD_REQUEST),
	ERROR_WHILE_RETRIEVING_DATA("Error while retrieving data, please try again..!", HttpStatus.INTERNAL_SERVER_ERROR),
	NO_DATA_FOUND("No data found", HttpStatus.NOT_FOUND), CUSTOMERID_EXISTS("This account is already inactive",HttpStatus.CONFLICT),;

	private final String message;
	private final HttpStatus httpStatus;

	ErrorCode(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}