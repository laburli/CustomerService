/**
 * 
 */
package com.tek.trp.service;

import com.tek.trp.exception.CustomerNotFoundException;
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

	List<Customer> getCustomers();

	public List<Customer> searchCustomer(Customer c) throws CustomerNotFoundException;

}
