/**
 * 
 */
package com.tek.trp.service;

import org.springframework.stereotype.Service;

import com.tek.trp.model.Customer;

import java.util.List;

/**
 * @author raadari
 *
 */
@Service
public interface CustomerService {

	

	Customer saveCustomer(Customer c);

	Customer updateCustomer(Customer c);

	Customer addCustomer(Customer customer);

	List<Customer> getCustomers();
	

}
