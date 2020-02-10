package com.tek.trp.service;


import com.tek.trp.exception.CustomerNotFoundException;
import com.tek.trp.model.Customer;
import com.tek.trp.repository.CustomerRepository;
import com.tek.trp.service.serviceimpl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServicesTest {


    @Mock
    public CustomerRepository customerRepository;

    @InjectMocks
    public CustomerServiceImpl customerService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void searchCustomerTestByIdAndName() throws CustomerNotFoundException {
        Customer customer = getCustomerByIdAndName();
        Optional<Customer> optionalCustomer = Optional.of(customer);
        Mockito.when(customerRepository.findByCustomerIdAndCustomerName(any(String.class),any(String.class))).thenReturn(optionalCustomer);
        customerService.searchCustomer(customer);
    }


    @Test
    public void searchCustomerTestByIdAndNameThrowException() throws CustomerNotFoundException {
        thrown.expect(CustomerNotFoundException.class);
        Customer customer = getCustomerByIdAndName();
        Optional<Customer> optionalCustomer = Optional.empty();
        Mockito.when(customerRepository.findByCustomerIdAndCustomerName(any(String.class),any(String.class))).thenReturn(optionalCustomer);
        customerService.searchCustomer(customer);
    }

    @Test
    public void searchCustomerTestByName() throws CustomerNotFoundException {
        Customer customer = new Customer();
        customer.setBranchName("Madhapur");
        customer.setBranchId(123);
        customer.setBranchCountry("Hyderbad");
        customer.setCustomerName("CPP1");
        List<Customer> customerList = Arrays.asList(customer);
        Optional<List<Customer>> optionalCustomer = Optional.of(customerList);
        Mockito.when(customerRepository.findByCustomerName(any(String.class))).thenReturn(optionalCustomer);
        customerService.searchCustomer(customer);
    }

    @Test
    public void searchCustomerTestByNameThrowException() throws CustomerNotFoundException {
        thrown.expect(CustomerNotFoundException.class);
        Customer customer = getCustomerForName();
        Optional<List<Customer>> optionalCustomer = Optional.empty();
        Mockito.when(customerRepository.findByCustomerName(any(String.class))).thenReturn(optionalCustomer);
        customerService.searchCustomer(customer);
    }

    @Test
    public void searchCustomerTestById() throws CustomerNotFoundException {
        Customer customer = getCustomerForId();
        Optional<Customer> optionalCustomer = Optional.of(customer);
        Mockito.when(customerRepository.findByCustomerId(any(String.class))).thenReturn(optionalCustomer);
        customerService.searchCustomer(customer);
    }

    @Test
    public void searchCustomerTestByIdThrowException() throws CustomerNotFoundException {
        thrown.expect(CustomerNotFoundException.class);
        Customer customer = getCustomerForId();
        Optional<Customer> optionalCustomer = Optional.empty();
        Mockito.when(customerRepository.findByCustomerId(any(String.class))).thenReturn(optionalCustomer);
        customerService.searchCustomer(customer);
    }

    private Customer getCustomerForId() {
        Customer customer = new Customer();
        customer.setBranchName("Madhapur");
        customer.setBranchId(123);
        customer.setBranchCountry("Hyderbad");
        customer.setCustomerId("CPP2");
        return customer;
    }

    private Customer getCustomerByIdAndName() {
        Customer customer = new Customer();
        customer.setBranchName("Madhapur");
        customer.setBranchId(123);
        customer.setBranchCountry("Hyderbad");
        customer.setCustomerName("CPP1");
        customer.setCustomerId("CPP2");
        return customer;
    }

    private Customer getCustomerForName() {
        Customer customer = new Customer();
        customer.setBranchName("Madhapur");
        customer.setBranchId(123);
        customer.setBranchCountry("Hyderbad");
        customer.setCustomerName("CPP1");
        return customer;
    }


}
