package com.tek.trp.CustomerService.controller;


import com.tek.trp.CustomerServiceApplication;
import com.tek.trp.controller.CustomerController;
import com.tek.trp.model.Customer;
import com.tek.trp.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CustomerServiceApplication.class)
@SpringBootTest
public class CustomerControllerTest {


    @Autowired
    private WebApplicationContext wac;

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void verifySearchCustomer() throws Exception {
        List<Customer> customers = Arrays.asList(getCustomer());
        Mockito.when(customerService.searchCustomer(any(Customer.class))).thenReturn(customers);
        customerController.searchCustomer(getCustomer());
    }

    private Customer getCustomer() {
        Customer customer = new Customer();
        customer.setBranchName("Madhapur");
        customer.setBranchId(123);
        customer.setBranchCountry("Hyderbad");
        customer.setCustomerName("CPP1");
        customer.setCustomerId("CPP2");
        return customer;
    }







}
