/**
 * 
 */
package com.tek.trp.service;

import org.springframework.stereotype.Service;

import com.tek.trp.model.Customer;

/**
 * @author raadari
 *
 */
@Service
public interface CustomerService {

	

	Customer saveCustomer(Customer c);

	Customer updateCustomer(Customer c);


	

}
