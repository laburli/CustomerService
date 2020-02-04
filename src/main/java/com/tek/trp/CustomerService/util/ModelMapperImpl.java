package com.tek.trp.CustomerService.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("restModelMapperImp")
public class ModelMapperImpl {


    @Autowired
    ModelMapper modelMapper;


    public <T> Object convert(Object source,Class<T> clazz) {
        Configuration configuration = this.modelMapper.getConfiguration();
        configuration.setAmbiguityIgnored(true);
        return modelMapper.map(source, clazz);
    }
}
