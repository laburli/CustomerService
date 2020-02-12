/**
 * This class is all about create, update, fetch customer API end points.
 */
package com.tek.trp.controller;

import com.tek.trp.exception.AlreadyDeactivateAccountException;
import com.tek.trp.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tek.trp.model.Customer;
import com.tek.trp.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

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

	@GetMapping("/viewAll-Customers")
	public List<Customer> getAllCustomer(){
		return customerService.getCustomers();
	}

	@PostMapping(value = "/create-customer")
	public Customer createCustomer(@Valid @RequestBody Customer customer){
		return customerService.saveCustomer(customer);
	}

	@GetMapping("/softdelete")
	public HttpStatus mockCall(){
		return HttpStatus.OK;
	}

	@PutMapping(value = "/softdelete/{id}")
	public ResponseEntity<Object> softdeleteCustomer (@PathVariable int id) throws CustomerNotFoundException, AlreadyDeactivateAccountException {
		customerService.softDeleteCustomer(id);
		return new ResponseEntity<>( HttpStatus.OK );

	}

	@DeleteMapping(value = "/{id}")
	public void deleteCustomer
			(@PathVariable int id) throws CustomerNotFoundException { customerService.deleteCustomer(id);
	}


}
