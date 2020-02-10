package com.tek.trp.controller;

import com.google.gson.Gson;
import com.tek.trp.model.Address;
import com.tek.trp.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "/application.properties")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCustomerController {
@Autowired
    private MockMvc mockMvc;
   /* @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }*/


    @Test
    public void shouldCreateCustomer() throws Exception {
        Gson gson = new Gson();
        Customer customer = new Customer();
        customer.setCustomerName("laxman");
        customer.setCustomerId("1111-2222");
        Address address = new Address();
        address.setCity("Hyderabad");
        Set addresses = new HashSet(Arrays.asList(address));
        customer.setAddress(addresses);
        String json = gson.toJson(customer);
        mockMvc.perform(post("/api/create-customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
               /* .andExpect(header().string("Location", "/api/account/12345"))
                .andExpect(jsonPath("$.accountId").value("12345"))
                .andExpect(jsonPath("$.accountType").value("SAVINGS"))
                .andExpect(jsonPath("$.balance").value(5000)*//*);
*/
    }
}
