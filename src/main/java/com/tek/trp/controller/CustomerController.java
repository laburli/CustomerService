/**
 * 
 */
package com.tek.trp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tek.trp.dto.AddressDTO;
import com.tek.trp.dto.CustomerDTO;
import com.tek.trp.dto.EmailDTO;
import com.tek.trp.dto.PhoneNumberDTO;

import com.tek.trp.model.Customer;
import com.tek.trp.service.CustomerService;

/**
 * @author raadari
 *
 */
@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/save-customer-details")
	public Customer saveCustomer(@RequestBody Customer c) {
		System.out.println("welcome to customer");
		return customerService.saveCustomer(c);
	}

	@PutMapping(value = "/update-customer-details")
	public String updateCustomer(@RequestBody CustomerDTO c) {
		return customerService.updateCustomer(c);
	}

	@PutMapping(value = "/update-address-details")
	public String updateAddress(@RequestBody List<AddressDTO> a) {

		return customerService.updateAddress(a);

	}

	@PutMapping(value = "/update-email-details")
	public String updateEmail(@RequestBody List<EmailDTO> a) {

		return customerService.updateEmail(a);

	}

	@PutMapping(value = "/update-phonenumber-details")
	public String updatePhoneNumber(@RequestBody List<PhoneNumberDTO> a) {

		return customerService.updatePhoneNumber(a);

	}

	@PostMapping(value = "/save-customer-details1")
	Customer saveCustomer() {
		return customerService.saveCustomer();
	}

	@GetMapping(value = "/searchCustomer")
	public List<Customer> searchCustomer(@RequestBody Customer searchCustomer) {
		return customerService.searchCustomer(searchCustomer);
	}

	@GetMapping("/softdelete")
	public HttpStatus mockCall() {
		return HttpStatus.OK;
	}

	@PutMapping(value = "/softdelete/{id}")
	public ResponseEntity<Object> softdeleteCustomer(@PathVariable String id) {
		customerService.softDeleteCustomer(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@DeleteMapping(value = "/{id}")
	public void deleteCustomer(@PathVariable String id) {
		customerService.deleteCustomer(id);
	}

	@GetMapping("/viewAll-Customers")
	public List<Customer> getAllCustomer() {
		return customerService.getCustomers();
	}

	@PostMapping(value = "/create-customer")
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
	}

}
