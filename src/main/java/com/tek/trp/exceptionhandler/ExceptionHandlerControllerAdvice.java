package com.tek.trp.exceptionhandler;

import javax.servlet.http.HttpServletRequest;

import com.tek.trp.exception.AlreadyDeactivateAccountException;
import com.tek.trp.exception.CustomerCreationException;
import com.tek.trp.exception.CustomerNotFoundException;
import com.tek.trp.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ExceptionResponse handleResourceNotFound(final CustomerNotFoundException exception,
                                             final HttpServletRequest request) {

        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.callerURL(request.getRequestURI());

        return error;
    }

    @ExceptionHandler(CustomerCreationException.class)
    public ResponseEntity<Object> exception(CustomerCreationException e) {
        return new ResponseEntity<>("Customer creation failed because of missing information", HttpStatus.PARTIAL_CONTENT);
    }
    @ControllerAdvice
    public static class CustomerExceptionHandler
    {
        @ExceptionHandler(value = CustomerNotFoundException.class)
        public static ResponseEntity<Object> exceptionCNFE(CustomerNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
        @ExceptionHandler(value = AlreadyDeactivateAccountException.class)
        public static ResponseEntity<Object> exceptionADAE(AlreadyDeactivateAccountException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}