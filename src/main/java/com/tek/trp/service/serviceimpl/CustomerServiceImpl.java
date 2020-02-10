/**
* This class is implementation for customer service interface.
*/
package com.tek.trp.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.tek.trp.exception.CustomerNotFoundException;
import com.tek.trp.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tek.trp.model.Address;
import com.tek.trp.model.Customer;
import com.tek.trp.model.Email;
import com.tek.trp.model.PhoneNumber;
import com.tek.trp.repository.CustomerRepository;

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
	public List<Customer> searchCustomer(Customer customer) throws CustomerNotFoundException {
		List<Customer> listCustomer = new ArrayList();
		try {
			if (customer.getCustomerId() != null && !customer.getCustomerId().equalsIgnoreCase("")) {
				if (customer.getCustomerName() != null && !customer.getCustomerName().equalsIgnoreCase("")) {
					Optional<Customer> byCustomerIdAndFirstName = customerRepository.findByCustomerIdAndCustomerName(customer.getCustomerId(), customer.getCustomerName());
					if (byCustomerIdAndFirstName.isPresent()) {
						Customer getCustomer = byCustomerIdAndFirstName.get();
						listCustomer.add(getCustomer);
					} else {
						throw new CustomerNotFoundException("There is no customer with this id :" + customer.getCustomerId() + " and name:" + customer.getCustomerName());
					}
				} else {
					Optional<Customer> byCustomerId = customerRepository.findByCustomerId(customer.getCustomerId());
					if (byCustomerId.isPresent()) {
						Customer customerId = byCustomerId.get();
						listCustomer.add(customerId);
					} else {
						throw new CustomerNotFoundException("There is no customer with this id :" + customer.getCustomerId());
					}
				}
			} else {
				Optional<List<Customer>> byFirstName = customerRepository.findByCustomerName(customer.getCustomerName());
				if (byFirstName.isPresent()) {
					listCustomer = byFirstName.get();
				} else {
					throw new CustomerNotFoundException("There is no customer with this name:" + customer.getCustomerName());
				}
			}
		} catch (Exception e) {
			throw new CustomerNotFoundException("There is a problem to find a customer,Please try after sometime");
		}
		return listCustomer;
	}

}
