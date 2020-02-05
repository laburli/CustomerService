/**
 * This class is all about create, update, fetch customer API end points.
 */
package com.tek.trp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tek.trp.model.Customer;
import com.tek.trp.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author raadari
 *
 */
@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/save-customer-details1")
	public Customer saveCustomer(@RequestBody Customer c) {
		return customerService.saveCustomer(c);
	}

	@PutMapping(value = "/update-customer-details")
	public Customer updateCustomer(@RequestBody Customer c) {
		return customerService.updateCustomer(c);
	}

	@GetMapping("/viewAll-Customers")
	public List<Customer> getAllCustomer(){
		return customerService.getCustomers();
	}

	@PostMapping("/create-customer")
	public Customer createCustomer(@Valid @RequestBody Customer customer){
		return customerService.saveCustomer(customer);
	}


}
