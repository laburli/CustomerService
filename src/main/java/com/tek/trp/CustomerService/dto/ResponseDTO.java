package com.tek.trp.CustomerService.dto;


import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope(value="prototype")
public class ResponseDTO {
    private String responseStatus;
    private String  responseCode;
    private Object object;


}
