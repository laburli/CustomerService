package com.tek.trp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tek.trp.dto.AddressDTO;
import com.tek.trp.dto.CustomerDTO;
import com.tek.trp.dto.EmailDTO;
import com.tek.trp.dto.PhoneNumberDTO;
import com.tek.trp.model.Account;
import com.tek.trp.model.Address;
import com.tek.trp.model.Customer;
import com.tek.trp.model.Email;
import com.tek.trp.model.PhoneNumber;
import com.tek.trp.service.CustomerService;

@WebMvcTest(CustomerController.class)
@TestPropertySource(locations = "/application.properties")
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;
	private Customer customer;
	
	  private Set<Email> emails; private Set<PhoneNumber> pNumbers; private
	  Set<Address> addresses;
	  Set<Account> account;
	 

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

		customer = new Customer();
		//customer.setCustomerId("62167833");
		customer.setCustomerName("CCP User1");
		customer.setCustomerStatus("Active");
		customer.setLastname("adari");
		customer.setMiddleName("c");
		customer.setCreatedBy("raviteja");
	//	customer.setCreatedOn(LocalDateTime.now());
		customer.setModifiedBy("none");
	//	customer.setModifiedOn(LocalDateTime.now());

		Address a = new Address();

		a.setAddressType("Permenent");
		a.setCity("Hyderabad");
		a.setCountry("GSP");
		a.setDoorNumber("88/134");
		a.setLandMark("Sub Register office lane");
		a.setPincode("500032");
		a.setState("Telangana");
		a.setIsPrimary(true);
		a.setCustomer(customer);

		Address a1 = new Address();

		a1.setAddressType("Tempm");
		a1.setCity("Hyderabad");
		a1.setCountry("india");
		a1.setDoorNumber("HIG-624");
		a1.setLandMark("Near Vijetha Super Market");
		a1.setPincode("500072");
		a1.setState("Telangana");
		a1.setCustomer(customer);
		a1.setIsPrimary(true);
		/*
		 * addresses = new ArrayList<>(); addresses.add(a); addresses.add(a1);
		 */

		Email e = new Email();

		e.setEmailAddress("raadari@teksystem.com");
		e.setEmailType("office");
		e.setCustomer(customer);
		e.setIsPrimary(true);

		Email e1 = new Email();

		e1.setEmailAddress("raavitejaadaricse@gmail.com");
		e1.setEmailType("personal");
		e1.setCustomer(customer);
		e1.setIsPrimary(true);

		/*
		 * emails = new ArrayList<>(); emails.add(e); emails.add(e1);
		 */

		PhoneNumber pn = new PhoneNumber();
		pn.setCityCode(40);
		pn.setCountryCode(91);
		pn.setPhoneType("Mobile");
		pn.setNumber("9492946341");
		pn.setCustomer(customer);
		pn.setIsPrimary(true);

		PhoneNumber pn1 = new PhoneNumber();
		pn1.setCityCode(40);
		pn1.setCountryCode(91);
		pn1.setPhoneType("Land");
		pn1.setNumber("98674373");
		pn1.setCustomer(customer);
		pn1.setIsPrimary(true);

		/*
		 * pNumbers = new ArrayList<>(); pNumbers.add(pn); pNumbers.add(pn1);
		 */

		// Set<Account> accSet=new HashSet<>();
		Account acc = new Account();
		acc.setAccountNumber(String.valueOf(Math.random() * 90000000 + 1));
		acc.setAccountType("Savings");
		acc.setAccountStatus("Active");
		acc.setBranchCity("Hyderabad");
		acc.setBranchCountry("India");
		acc.setBranchId(1234);
		acc.setBranchName("Gachibowli");
		acc.setBranchState("Telangana");
		acc.setIfscCode("TRP01CCP");
		acc.setCustomer(customer);

		account=new HashSet<>();
		account.add(acc);

		addresses=new HashSet<>();
		addresses.add(a);
		addresses.add(a1);
		emails=new HashSet<>();
		emails.add(e);
		emails.add(e1);
		pNumbers=new HashSet<>();
		pNumbers.add(pn);
		pNumbers.add(pn1);
		customer.setAccount(account);
		customer.setAddress(addresses);
		customer.setEmail(emails);
		customer.setPhoneNumber(pNumbers);

	}

	@Test
	public void AddressShouldUpdateThenReturnMessageFromService() throws Exception {

		List<AddressDTO> alist = new ArrayList<>();
		AddressDTO a = new AddressDTO();
		a.setAddressType("Permenent");
		a.setCity("Hyderabad");
		a.setCountry("GSP");
		a.setDoorNumber("88/134");
		a.setLandMark("Sub Register office lane");
		a.setPincode("500032");
		a.setState("Telangana");
		a.setIsPrimary(true);
		a.setCustomerId("1234");
		alist.add(a);

		byte[] input = toJson(alist);

		this.mockMvc.perform(put("/api/update-address-details").content(input).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
		when(customerService.updateAddress(alist)).thenReturn("Updated Successfully");

	}

	@Test
	public void EmailShouldUpdateThenReturnMessageFromService() throws Exception {

		List<EmailDTO> edtoList = new ArrayList<>();
		// EmailDTO adto = new EmailDTO(1L, "62167833", "personal", "", null);
		edtoList.add(EmailDTO.builder().id(2L).emailType("office").emailAddress("raadari@teksystems.com")
				.isPrimary(true).customerId("62167833").build());

		edtoList.add(EmailDTO.builder().id(1L).emailType("personal").emailAddress("raavitejaadaricse@gmail.com")
				.isPrimary(true).customerId("62167833").build());

		byte[] input = toJson(edtoList);

		this.mockMvc.perform(put("/api/update-email-details").content(input).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
		when(customerService.updateEmail(edtoList)).thenReturn("Updated Successfully");

	}

	@Test
	public void PhoneNumberShouldUpdateThenReturnMessageFromService() throws Exception {

		List<PhoneNumberDTO> pnDtoList = new ArrayList<>();

		pnDtoList.add(PhoneNumberDTO.builder().id(2L).phoneType("office").number("040325634").isPrimary(true)
				.customerId("62167833").build());

		pnDtoList.add(PhoneNumberDTO.builder().id(1L).phoneType("personal").number("9492946341").isPrimary(true)
				.customerId("62167833").build());

		byte[] input = toJson(pnDtoList);

		this.mockMvc.perform(put("/api/update-phonenumber-details").content(input)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
		when(customerService.updatePhoneNumber(pnDtoList)).thenReturn("Updated Successfully");

	}

	@Test
	public void CustomerShouldUpdateThenReturnMessageFromService() throws Exception {

		CustomerDTO cdtoList = CustomerDTO.builder().customerId("62167833").customerName("raviteja").middleName("c")
				.lastName("adari").build();

		byte[] input = toJson(cdtoList);

		this.mockMvc.perform(put("/api/update-customer-details").content(input).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
		when(customerService.updateCustomer(cdtoList)).thenReturn("Customer data Updated");

	}

	@Test
	public void CustomerShouldSaveThenReturnMessageFromService() throws Exception {

		byte[] input = toJson(customer);
		
		when(customerService.saveCustomer(customer)).thenReturn(customer);
		this.mockMvc.perform(post("/api/save-customer-details").content(input).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	

	}

	@Test
	public void CustomerShouldSave1ThenReturnMessageFromService() throws Exception {

		byte[] input = toJson(customer);
System.out.println(input.toString());
		this.mockMvc.perform(post("/api/save-customer-details1").content(input).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
		when(customerService.saveCustomer()).thenReturn(customer);

	}

	@Test
	public void verifyCreateCustomer() throws Exception {
		byte[] input = toJson(customer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/create-customer")
				.accept(MediaType.APPLICATION_JSON).content(input).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.toString());
		/*
		 * mockMvc.perform(post("/api/create-customer")) . .andExpect(status().isOk());
		 */
	}

	@Test
    public void verifySearchCustomer() throws Exception {
        Customer customer = getCustomerForSearch();
        byte[] input = toJson(customer);
        List<Customer> customers = Arrays.asList(customer);
        Mockito.when(customerService.searchCustomer(Mockito.any(Customer.class))).thenReturn(customers);
        MockHttpServletRequestBuilder requestBuilder = get("/api/searchCustomer").
                accept(MediaType.APPLICATION_JSON).content(input).
                contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String responseString = response.getContentAsString();
        String expected = new ObjectMapper().writeValueAsString(customers);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
       assertEquals(expected, responseString);
        JSONAssert.assertEquals(expected, responseString, false);
    }


    private Customer getCustomerForSearch() {
        Customer customer = new Customer();
       
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
      
        PhoneNumber phoneNumber2 = new PhoneNumber();
        phoneNumber2.setCityCode(040);
       
        Set<PhoneNumber> phoneNumberSet = new HashSet<>();
        phoneNumberSet.add(phoneNumber);
        phoneNumberSet.add(phoneNumber2);
        customer.setAddress(addressSet);

        Email email = new Email();
      
        email.setEmailAddress("abc@gmail.com");
        email.setEmailType("Personal");
        Email email2 = new Email();
      
        email2.setEmailAddress("abc@gmail.com");
        email2.setEmailType("Personal");
        Set<Email> emails = new HashSet<>();
        emails.add(email);
        emails.add(email2);
        customer.setEmail(emails);
        return customer;
    }


	private byte[] toJson(Object r) throws Exception {
		ObjectMapper map = new ObjectMapper();
		return map.writeValueAsString(r).getBytes();
	}
}