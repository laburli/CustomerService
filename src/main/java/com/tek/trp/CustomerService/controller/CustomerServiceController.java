package com.tek.trp.CustomerService.controller;



import com.tek.trp.CustomerService.dto.CustomerDTO;
import com.tek.trp.CustomerService.dto.ResponseDTO;
import com.tek.trp.CustomerService.dto.SearchCustomerDTO;
import com.tek.trp.CustomerService.exception.CustomerNotFoundException;
import com.tek.trp.CustomerService.services.CustomerServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/customer-details", produces= MediaType.APPLICATION_JSON_VALUE)
public class CustomerServiceController {

    @Autowired
    private CustomerServiceI customerServiceI;

    @Lookup
    private ResponseDTO getResponseDTO() {
        return null;
    }


    @GetMapping("/searchCustomer")
    public ResponseDTO  searchCustomer(@RequestBody SearchCustomerDTO searchCustomerDTO) throws CustomerNotFoundException {
        List<CustomerDTO> customerDTOS = customerServiceI.searchCustomer(searchCustomerDTO);
        ResponseDTO responseDTO = getResponseDTO();
        if(customerDTOS != null){
            responseDTO.setObject(customerDTOS);
            responseDTO.setResponseStatus("Data Retrieved Successfully");
            responseDTO.setResponseCode("200");
        }else{
            responseDTO.setResponseStatus("No Data Found");
            responseDTO.setResponseCode("404");
        }
        return responseDTO;
    }



}
