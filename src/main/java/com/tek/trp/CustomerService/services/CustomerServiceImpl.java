package com.tek.trp.CustomerService.services;

import com.tek.trp.CustomerService.dto.CustomerDTO;
import com.tek.trp.CustomerService.dto.SearchCustomerDTO;
import com.tek.trp.CustomerService.entity.Customer;
import com.tek.trp.CustomerService.exception.CustomerNotFoundException;
import com.tek.trp.CustomerService.repository.CustomerRepository;
import com.tek.trp.CustomerService.util.ModelMapperImpl;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerServiceI {

    @Autowired
    private ModelMapperImpl modelMapperImpl;

    @Autowired
    CustomerRepository repository;

    @Override
    public List<CustomerDTO> searchCustomer(SearchCustomerDTO searchCustomerDTO) throws CustomerNotFoundException {
        List<CustomerDTO> listCustomerDto = new ArrayList();
        try {
            if (searchCustomerDTO.getCustomerId() != null && !searchCustomerDTO.getCustomerId().equalsIgnoreCase("")) {
                if (searchCustomerDTO.getFirstName() != null && !searchCustomerDTO.getFirstName().equalsIgnoreCase("")) {
                    //findByIdAndName
                    Optional<Customer> byCustomerIdAndFirstName = repository.findByCustomerIdAndFirstName(searchCustomerDTO.getCustomerId(), searchCustomerDTO.getFirstName());
                    if (byCustomerIdAndFirstName.isPresent()) {
                        Customer customer = byCustomerIdAndFirstName.get();
                        createCustomerDTO(listCustomerDto, customer);
                    } else {
                        throw new CustomerNotFoundException("There is no customer with this id :" + searchCustomerDTO.getCustomerId() + " and name:" + searchCustomerDTO.getFirstName());
                    }
                } else {
                    // findById;
                    Optional<Customer> byCustomerId = repository.findByCustomerId(searchCustomerDTO.getCustomerId());
                    if (byCustomerId.isPresent()) {
                        Customer customerId = byCustomerId.get();
                        createCustomerDTO(listCustomerDto, customerId);
                    } else {
                        throw new CustomerNotFoundException("There is no customer with this id :" + searchCustomerDTO.getCustomerId());

                    }
                }
            } else {
                // findByName;
                Optional<List<Customer>> byFirstName = repository.findByFirstName(searchCustomerDTO.getFirstName());
                if (byFirstName.isPresent()) {
                    List<Customer> customerList = byFirstName.get();
                    listCustomerDto = customerList.stream().map(customer -> (CustomerDTO) modelMapperImpl.convert(customer, CustomerDTO.class)).collect(Collectors.toList());
                } else {
                    throw new CustomerNotFoundException("There is no customer with this name:" + searchCustomerDTO.getFirstName());
                }
            }
        } catch (Exception e) {
            throw new CustomerNotFoundException("There is a problem to find a customer,Please try after sometime");
        }
        return listCustomerDto;

    }

    private void createCustomerDTO(List<CustomerDTO> listCustomerDto, Customer customerId) {
        CustomerDTO convertCustomerId = (CustomerDTO) modelMapperImpl.convert(customerId, CustomerDTO.class);
        listCustomerDto.add(convertCustomerId);
    }

}
