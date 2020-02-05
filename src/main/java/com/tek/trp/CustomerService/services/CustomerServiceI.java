package com.tek.trp.CustomerService.services;

import com.tek.trp.CustomerService.dto.CustomerDTO;
import com.tek.trp.CustomerService.dto.SearchCustomerDTO;
import com.tek.trp.CustomerService.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerServiceI {

    public List<CustomerDTO> searchCustomer(SearchCustomerDTO searchCustomerDTO) throws CustomerNotFoundException;


}
