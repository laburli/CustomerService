package com.tek.trp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global Exception Handler class
 * 
 * @author raadari
 * 
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Business Exception handling.
	 *
	 * @param exception Exception
	 * @param request   Request
	 * @return ResponseEntity
	 */
	@ExceptionHandler(TRPException.class)
	public ResponseEntity<Object> handleBusinessException(TRPException exception, WebRequest request) {
		logger.error("Exception ", exception);
		return new ResponseEntity<>(exception.getErrorDetail(), exception.getHttpStatus());
	}

	/**
	 * Handle NoHandlerFoundException.
	 *
	 * @param exception Exception
	 * @param headers   Header
	 * @param status    Status
	 * @param request   Request
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("Exception ", exception);
		ErrorDetail errorDetail = new ErrorDetail("RESOURCE_NOT_FOUND",
				"The requested resource does not exist or is not supported by this version of the API.");
		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	}

	/**
	 * MissingServletRequestParameterException.
	 *
	 * @param exception Exception
	 * @param headers   Header
	 * @param status    Status
	 * @param request   Request
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException exception, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		logger.error("Exception", exception);
		ErrorDetail errorDetail = new ErrorDetail("SRV_GENERIC_ERROR",
				"Request parameters are not valid:" + exception.getMessage());
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}

}
