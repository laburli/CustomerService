/**
* This class is implementation for customer service interface.
*/
package com.tek.trp.service.serviceimpl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tek.trp.model.Address;
import com.tek.trp.model.Customer;
import com.tek.trp.model.Email;
import com.tek.trp.model.PhoneNumber;
import com.tek.trp.repository.CustomerRepository;
import com.tek.trp.service.CustomerService;

/**
 * @author raadari
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer saveCustomer(Customer c) {
		Set<Address> a = c.getAddress();
		Set<Email> e = c.getEmail();
		Set<PhoneNumber> pn = c.getPhoneNumber();
		a.forEach(o -> o.setCustomer(c));
		e.forEach(o -> o.setCustomer(c));
		pn.forEach(o -> o.setCustomer(c));
		c.setAddress(a);
		c.setEmail(e);
		c.setPhoneNumber(pn);
		c.setCustomerId((int)(Math.random() * 90000000 + 1));
		logger.debug("Customer {}",c);
		return customerRepository.save(c);
	}

	@Override
	public Customer updateCustomer(Customer c) {

		Set<Address> a = c.getAddress();
		Set<Email> e = c.getEmail();
		Set<PhoneNumber> pn = c.getPhoneNumber();
		a.forEach(o -> o.setCustomer(c));
		e.forEach(o -> o.setCustomer(c));
		pn.forEach(o -> o.setCustomer(c));
		c.setAddress(a);
		c.setEmail(e);
		c.setPhoneNumber(pn);
		return customerRepository.save(c);

	}


	@Override
	public List<Customer> getCustomers(){
		return customerRepository.findAll();
	}

}
