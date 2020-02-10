package com.tek.trp.CustomerService.controller;


import com.google.gson.Gson;
import com.tek.trp.CustomerServiceApplication;
import com.tek.trp.controller.CustomerController;
import com.tek.trp.model.Address;
import com.tek.trp.model.Customer;
import com.tek.trp.model.Email;
import com.tek.trp.model.PhoneNumber;
import com.tek.trp.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private MockMvc mockMvc;

    private Customer c1;
    private Set<Email> emails;
    private Set<PhoneNumber> pNos;
    private Set<Address> addresses;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        MockitoAnnotations.initMocks(this);
        c1 = new Customer();
        emails = new HashSet<Email>();
        pNos = new HashSet<PhoneNumber>();
        addresses = new HashSet<Address>();
        Email e1 = new Email();
        e1.setCustomerStatus("active");
        e1.setEmailAddress("test1@teksystems.com");
        PhoneNumber p1 = new PhoneNumber();
        p1.setCustomerStatus("active");
        p1.setNumber("0999901232");
        Address a1= new Address();
        a1.setCustomerStatus("active");
        a1.setAddressType("home");
        a1.setCity("bangalore");
        emails.add(e1);
        pNos.add(p1);
        addresses.add(a1);
        c1.setEmail(emails);
        c1.setPhoneNumber(pNos);
        c1.setAddress(addresses);
        c1.setCustomerName("Test User");
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


    @Test
    public void verifyCreateCustomer() throws Exception {
        Gson gson = new Gson();
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/create-customer")
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(c1))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println(response.toString());
        /*mockMvc.perform(post("/api/create-customer"))
                .
                .andExpect(status().isOk());*/
    }




}
