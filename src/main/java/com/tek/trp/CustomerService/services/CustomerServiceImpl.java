package com.tek.trp.CustomerService.services;

import com.tgs.digital.bank.Exception.CustomerNotFoundException;
import com.tgs.digital.bank.dto.CustomerDTO;
import com.tgs.digital.bank.dto.SearchCustomerDTO;
import com.tgs.digital.bank.entity.Customer;
import com.tgs.digital.bank.repository.CustomerRepository;
import com.tgs.digital.bank.util.ModelMapperImpl;
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
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> allCustomer = repository.findAll();
        List<CustomerDTO> collectCustomer = allCustomer.stream().map(customer -> (CustomerDTO) modelMapperImpl.convert(customer, CustomerDTO.class)).collect(Collectors.toList());
        return collectCustomer;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer create = (Customer) modelMapperImpl.convert(customerDTO, Customer.class);
        if(create.getId() == null){
            Long maxId = repository.getMaxId();
            create.setId(maxId+1);
        }
        Customer saveCustomer = repository.save(create);
        CustomerDTO crCustomer = (CustomerDTO) modelMapperImpl.convert(saveCustomer, CustomerDTO.class);
        return crCustomer;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Optional<Customer> byId = repository.findById(customerDTO.getId());
        if (byId.isPresent()) {
           return createCustomer(customerDTO);
        }
        return null;
    }

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
                    }else{
                        throw new CustomerNotFoundException("There is no customer with this id :"+searchCustomerDTO.getCustomerId()+" and name:"+searchCustomerDTO.getFirstName());
                    }
                } else {
                    // findById;
                    Optional<Customer> byCustomerId = repository.findByCustomerId(searchCustomerDTO.getCustomerId());
                    if (byCustomerId.isPresent()) {
                        Customer customerId = byCustomerId.get();
                        createCustomerDTO(listCustomerDto, customerId);
                    }
                    else{
                        throw new CustomerNotFoundException("There is no customer with this id :"+searchCustomerDTO.getCustomerId());

                    }
                }
            } else {
                // findByName;
                Optional<List<Customer>> byFirstName = repository.findByFirstName(searchCustomerDTO.getFirstName());
                if (byFirstName.isPresent()) {
                    List<Customer> customerList = byFirstName.get();
                    listCustomerDto = customerList.stream().map(customer -> (CustomerDTO) modelMapperImpl.convert(customer, CustomerDTO.class)).collect(Collectors.toList());
                }
                else{
                    throw new CustomerNotFoundException("There is no customer with this name:"+searchCustomerDTO.getFirstName());
                }
            }
        }catch (Exception e){
              throw new CustomerNotFoundException("There is a problem to find a customer,Please try after sometime");
        }
        return listCustomerDto;

    }

    private void createCustomerDTO(List<CustomerDTO> listCustomerDto, Customer customerId) {
        CustomerDTO convertCustomerId = (CustomerDTO) modelMapperImpl.convert(customerId, CustomerDTO.class);
        listCustomerDto.add(convertCustomerId);
    }


    //        Optional<List<Customer>> listCustomerData = Optional.of(null);
//        Optional<Customer> customerData = Optional.of(null);
//        if(type.equalsIgnoreCase("id")){
//            customerData = repository.findById(Long.parseLong(input));
//        }else if(type.equalsIgnoreCase("customerId")){
//            customerData = repository.findByCustomerId(input);
//        }else if(type.equalsIgnoreCase("firstName")){
//            listCustomerData = repository.findByFirstName(input);
//        } else if(type.equalsIgnoreCase("middleName")){
//            listCustomerData = repository.findByMiddleName(input);
//        } else if(type.equalsIgnoreCase("lastName")){
//            listCustomerData = repository.findByLastName(input);
//        } else if(type.equalsIgnoreCase("branchId")){
//            listCustomerData = repository.findByBranchId(input);
//        } else if(type.equalsIgnoreCase("branchName")){
//            listCustomerData = repository.findByBranchName(input);
//        } else if(type.equalsIgnoreCase("branchCity")){
//            listCustomerData = repository.findByBranchCity(input);
//        } else if(type.equalsIgnoreCase("branchState")){
//            listCustomerData = repository.findByBranchState(input);
//        } else if(type.equalsIgnoreCase("branchCountry")){
//            listCustomerData = repository.findByBranchCountry(input);
//        } else if(type.equalsIgnoreCase("ifscCode")){
//            listCustomerData = repository.findByIfscCode(input);
//        }
//        if(customerData.isPresent()){
//            Customer customer = customerData.get();
//            List<Customer> customers = Arrays.asList(customer);
//            return customers.stream().map(customersList -> (CustomerDTO) modelMapperImpl.convert(customersList, CustomerDTO.class)).collect(Collectors.toList());
//        }else if(listCustomerData.isPresent()){
//            List<Customer> customers = listCustomerData.get();
//            return customers.stream().map(customer -> (CustomerDTO) modelMapperImpl.convert(customer, CustomerDTO.class)).collect(Collectors.toList());
//        }
//        return null;


    @Override
    public void deleteCustomer(CustomerDTO customerDTO) {
        Customer deleteCustomer=(Customer) modelMapperImpl.convert(customerDTO, Customer.class);
        repository.delete(deleteCustomer);
    }
}
