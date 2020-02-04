package com.tek.trp.CustomerService.handler;

import com.tek.trp.CustomerService.dto.ResponseDTO;
import com.tek.trp.CustomerService.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomerExceptionHandler {

    @Autowired
    private ResponseDTO responseDTO;



    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseBody
    public ResponseDTO customerNotFoundException( CustomerNotFoundException ex) {
        responseDTO.setResponseCode("404");
        responseDTO.setResponseStatus(ex.getMessage());
        return responseDTO;

    }


}
