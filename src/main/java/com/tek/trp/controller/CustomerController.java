/**
 * This class is all about create, update, fetch customer API end points.
 */
package com.tek.trp.controller;

import com.tek.trp.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tek.trp.model.Customer;
import com.tek.trp.service.CustomerService;

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


	@GetMapping(value = "/searchCustomer")
	public @ResponseBody  List<Customer> searchCustomer(@RequestBody Customer searchCustomer) throws CustomerNotFoundException {
		return customerService.searchCustomer(searchCustomer);
	}
	
}
