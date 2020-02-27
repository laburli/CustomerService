/**
 * 
 */
package com.tek.trp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tek.trp.dto.AddressDTO;
import com.tek.trp.dto.CustomerDTO;
import com.tek.trp.dto.EmailDTO;
import com.tek.trp.dto.PhoneNumberDTO;

import com.tek.trp.model.Customer;

/**
 * @author raadari
 *
 */
@Service
public interface CustomerService {

	Customer saveCustomer();

	Customer saveCustomer(Customer c);

	

	String updateAddress(List<AddressDTO> a);

	String updateEmail(List<EmailDTO> emailDto);

	String updatePhoneNumber(List<PhoneNumberDTO> pnDto);

	String updateCustomer(CustomerDTO cdto);

	List<Customer> getCustomers();

	public List<Customer> searchCustomer(Customer c);
	public void softDeleteCustomer(String cust_id);
	void deleteCustomer (String cust_id) ;

	List<Customer> searchCustomer(String customerId, String customerName);

}
