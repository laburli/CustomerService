package com.tek.trp.CustomerService.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    public ModelMapper getModelMapper(){
        return new ModelMapper();

    }

}
