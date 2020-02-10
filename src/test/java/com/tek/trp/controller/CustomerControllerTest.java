package com.tek.trp.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tek.trp.CustomerServiceApplication;
import com.tek.trp.controller.CustomerController;
import com.tek.trp.model.Address;
import com.tek.trp.model.Customer;
import com.tek.trp.model.Email;
import com.tek.trp.model.PhoneNumber;
import com.tek.trp.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @MockBean
    private CustomerService customerService;

    ObjectMapper mapper = new ObjectMapper();

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


    @Test
    public void verifySearchCustomer() throws Exception {
        Customer customer = getCustomerForSearch();
        String inputString = mapper.writeValueAsString(customer);
        List<Customer> customers = Arrays.asList(getCustomerForSearch());
        Mockito.when(customerService.searchCustomer(any(Customer.class))).thenReturn(customers);
        MockHttpServletRequestBuilder requestBuilder = get("/api/searchCustomer").
                accept(MediaType.APPLICATION_JSON).content(inputString).
                contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String responseString = response.getContentAsString();
        String expected = "[{\"id\":0,\"customerName\":\"CPP1\",\"customerId\":\"CPP2\",\"lastname\":null,\"middleName\":null,\"branchId\":123,\"branchName\":\"Madhapur\",\"branchCity\":null,\"branchState\":null,\"branchCountry\":\"Hyderbad\",\"ifscCode\":null,\"customerStatus\":null,\"email\":[{\"id\":0,\"emailType\":\"Personal\",\"emailAddress\":\"abc@gmail.com\",\"customerStatus\":\"Active\",\"CID\":null}],\"phoneNumber\":null,\"createdBy\":null,\"createdOn\":null,\"modifiedBy\":null,\"modifiedOn\":null,\"address\":[{\"id\":0,\"doorNumber\":null,\"addressType\":\"Current\",\"landMark\":null,\"city\":\"Odisha\",\"state\":null,\"country\":\"India\",\"pincode\":null,\"customerStatus\":null,\"CID\":null},{\"id\":0,\"doorNumber\":null,\"addressType\":\"Permananent\",\"landMark\":null,\"city\":\"Hyderabad\",\"state\":null,\"country\":\"India\",\"pincode\":null,\"customerStatus\":null,\"CID\":null}]}]";
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertEquals(expected, responseString);
        JSONAssert.assertEquals(expected, responseString, false);
    }


    private Customer getCustomerForSearch() {
        Customer customer = new Customer();
        customer.setBranchName("Madhapur");
        customer.setBranchId(123);
        customer.setBranchCountry("Hyderbad");
        customer.setCustomerName("CPP1");
        customer.setCustomerId("CPP2");

        Address address = new Address();
        address.setAddressType("Permananent");
        address.setCity("Hyderabad");
        address.setCountry("India");
        Address address2 = new Address();
        address2.setAddressType("Current");
        address2.setCity("Odisha");
        address2.setCountry("India");
        Set<Address> addressSet = new HashSet<>();
        addressSet.add(address);
        addressSet.add(address2);
        customer.setAddress(addressSet);

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setCityCode(040);
        phoneNumber.setCustomerStatus("Active");
        PhoneNumber phoneNumber2 = new PhoneNumber();
        phoneNumber2.setCityCode(040);
        phoneNumber2.setCustomerStatus("Active");
        Set<PhoneNumber> phoneNumberSet = new HashSet<>();
        phoneNumberSet.add(phoneNumber);
        phoneNumberSet.add(phoneNumber2);
        customer.setAddress(addressSet);

        Email email = new Email();
        email.setCustomerStatus("Active");
        email.setEmailAddress("abc@gmail.com");
        email.setEmailType("Personal");
        Email email2 = new Email();
        email2.setCustomerStatus("Active");
        email2.setEmailAddress("abc@gmail.com");
        email2.setEmailType("Personal");
        Set<Email> emails = new HashSet<>();
        emails.add(email);
        emails.add(email2);
        customer.setEmail(emails);
        return customer;
    }




}
