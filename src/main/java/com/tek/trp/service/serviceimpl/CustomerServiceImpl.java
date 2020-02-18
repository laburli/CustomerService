/**
 * 
 */
package com.tek.trp.service.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tek.trp.dto.AddressDTO;
import com.tek.trp.dto.CustomerDTO;
import com.tek.trp.dto.EmailDTO;
import com.tek.trp.dto.PhoneNumberDTO;

import com.tek.trp.exception.ErrorCode;
import com.tek.trp.exception.TRPException;
import com.tek.trp.model.Account;
import com.tek.trp.model.Address;
import com.tek.trp.model.Customer;
import com.tek.trp.model.Email;
import com.tek.trp.model.PhoneNumber;
import com.tek.trp.repository.AddressRepository;
import com.tek.trp.repository.CustomerRepository;
import com.tek.trp.repository.EmailRepository;
import com.tek.trp.repository.PhoneNumberRepository;
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
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private EmailRepository emailRepository;
	@Autowired
	private PhoneNumberRepository phoneNumberRepository;

	public Customer saveCustomer() {
		logger.info("Save customer info");

		Customer c = new Customer();

		c.setCreatedBy("raviteja");
		c.setCreatedOn(LocalDateTime.now());
		c.setCustomerId("62167833");
		c.setCustomerName("CCP User1");
		c.setLastname("adari");
		c.setMiddleName("c");
		c.setModifiedBy("none");
		c.setModifiedOn(LocalDateTime.now());
		c.setCustomerStatus("Active");

		Email e = new Email();

		e.setEmailAddress("raadari@teksystem.com");
		e.setEmailType("office");
		e.setCustomer(c);
		e.setIsPrimary(true);

		Email e1 = new Email();

		e1.setEmailAddress("raavitejaadaricse@gmail.com");
		e1.setEmailType("personal");
		e1.setCustomer(c);
		e1.setIsPrimary(true);

		PhoneNumber pn = new PhoneNumber();
		pn.setCityCode(40);
		pn.setCountryCode(91);
		pn.setPhoneType("Mobile");
		pn.setNumber("9492946341");
		pn.setCustomer(c);
		pn.setIsPrimary(true);

		PhoneNumber pn1 = new PhoneNumber();
		pn1.setCityCode(40);
		pn1.setCountryCode(91);
		pn1.setPhoneType("Land");
		pn1.setNumber("98674373");
		pn1.setCustomer(c);
		pn1.setIsPrimary(true);

		Address a = new Address();

		a.setAddressType("Permenent");
		a.setCity("Hyderabad");
		a.setCountry("GSP");
		a.setDoorNumber("88/134");
		a.setLandMark("Sub Register office lane");
		a.setPincode("500032");
		a.setState("Telangana");
		a.setIsPrimary(true);
		a.setCustomer(c);

		Address a1 = new Address();

		a1.setAddressType("Tempm");
		a1.setCity("Hyderabad");
		a1.setCountry("india");
		a1.setDoorNumber("HIG-624");
		a1.setLandMark("Near Vijetha Super Market");
		a1.setPincode("500072");
		a1.setState("Telangana");
		a1.setCustomer(c);
		a1.setIsPrimary(true);

		Account acc = new Account();
		acc.setAccountNumber(String.valueOf(Math.random() * 90000000 + 1));
		acc.setAccountType("Savings");
		acc.setAccountStatus("Active");
		acc.setBranchCity("Hyderabad");
		acc.setBranchCountry("India");
		acc.setBranchId(1234);
		acc.setBranchName("Gachibowli");
		acc.setBranchState("Telangana");
		acc.setIfscCode("TRP01CCP");
		acc.setCustomer(c);

		Set<Address> addressSet = new HashSet<>();
		addressSet.add(a);
		addressSet.add(a1);
		c.setAddress(addressSet);

		Set<Email> emailSet = new HashSet<>();
		emailSet.add(e);
		emailSet.add(e1);
		c.setEmail(emailSet);

		Set<PhoneNumber> pnSet = new HashSet<>();
		pnSet.add(pn);
		pnSet.add(pn1);
		c.setPhoneNumber(pnSet);

		Set<Account> accset = new HashSet<>();
		accset.add(acc);
		c.setAccount(accset);

		return customerRepository.save(c);

	}

	@Override
	public Customer saveCustomer(Customer c) {
		Set<Address> a = c.getAddress();
		Set<Email> e = c.getEmail();
		Set<PhoneNumber> pn = c.getPhoneNumber();
		Set<Account> acc = c.getAccount();
		a.forEach(o -> o.setCustomer(c));
		e.forEach(o -> o.setCustomer(c));
		pn.forEach(o -> o.setCustomer(c));
		acc.forEach(o -> o.setCustomer(c));
		c.setAddress(a);
		c.setEmail(e);
		c.setPhoneNumber(pn);
		c.setAccount(acc);

		return customerRepository.save(c);
	}

	/*
	 * @Override public Customer updateCustomer(Customer c) {
	 * 
	 * final Customer custom = c.getCustomerId() != null ?
	 * customerRepository.findByCustomerId(c.getCustomerId()) : c; Set<Address> a =
	 * custom.getAddress(); Set<Email> e = custom.getEmail(); Set<PhoneNumber> pn =
	 * custom.getPhoneNumber(); a.forEach(o -> { o.setCustomer(custom); Customer
	 * cust = o.getCustomer().getCustomerId() == null ? c :
	 * customerRepository.findByCustomerId(o.getCustomer().getCustomerId());
	 * cust.setModifiedOn(LocalDateTime.now()); cust.setModifiedBy("User");
	 * o.setCustomer(cust); }); e.forEach(o -> { o.setCustomer(custom); Customer
	 * cust = o.getCustomer().getCustomerId() == null ? c :
	 * customerRepository.findByCustomerId(o.getCustomer().getCustomerId());
	 * cust.setModifiedOn(LocalDateTime.now()); cust.setModifiedBy("User");
	 * o.setCustomer(cust); }); pn.forEach(o -> { o.setCustomer(custom); Customer
	 * cust = o.getCustomer().getCustomerId() == null ? c :
	 * customerRepository.findByCustomerId(o.getCustomer().getCustomerId());
	 * cust.setModifiedOn(LocalDateTime.now()); cust.setModifiedBy("User");
	 * o.setCustomer(cust); }); c.setAddress(a); c.setEmail(e);
	 * c.setPhoneNumber(pn); return customerRepository.save(c);
	 * 
	 * }
	 */

	@Override
	public String updateCustomer(CustomerDTO cdto) {

		if (cdto.getCustomerId() != null) {
			Customer custom = customerRepository.findByCustomerId(cdto.getCustomerId());

			if (custom != null) {
				custom.setCustomerName(cdto.getCustomerName());
				custom.setMiddleName(cdto.getMiddleName());
				custom.setLastname(cdto.getMiddleName());
				custom.setModifiedBy("User");
				custom.setModifiedOn(LocalDateTime.now());
				customerRepository.save(custom);
				return "Customer data Updated";
			} else {
				throw new TRPException(ErrorCode.CUSTOMERID_NOT_FOUND);
			}
		} else {
			throw new TRPException(ErrorCode.IN_VALID_INPUT);
		}

	}

	@Override
	public String updateAddress(List<AddressDTO> a) {

		List<Address> aset = new ArrayList<>();
		Address add = new Address();

		a.forEach(o -> {
			if (o.getCustomerId() != null) {
				setAddress(add, o);
				Customer c = customerRepository.findByCustomerId(o.getCustomerId());
				if (c != null) {
					c.setModifiedOn(LocalDateTime.now());
					add.setCustomer(c);
					aset.add(add);
				} else
					throw new TRPException(ErrorCode.CUSTOMERID_NOT_FOUND);

			} else {
				throw new TRPException(ErrorCode.IN_VALID_INPUT);
			}
		});

		addressRepository.saveAll(aset);

		return "Updated Successfully";
	}

	/**
	 * @param add
	 * @param o
	 */
	private void setAddress(Address add, AddressDTO o) {
		if (o.getAddressType() != null) {
			add.setAddressType(o.getAddressType());
		}
		if (o.getCity() != null) {
			add.setCity(o.getCity());
		}
		if (o.getCountry() != null) {
			add.setCountry(o.getCountry());
		}
		if (o.getIsPrimary() != null) {
			add.setIsPrimary(o.getIsPrimary());
		}
		if (o.getId() != null) {
			add.setId(o.getId());
		}
		if (o.getLandMark() != null) {
			add.setLandMark(o.getLandMark());
		}
		if (o.getPincode() != null) {
			add.setPincode(o.getPincode());
		}
		if (o.getState() != null) {
			add.setState(o.getState());
		}
		if (o.getDoorNumber() != null) {
			add.setDoorNumber(o.getDoorNumber());
		}
	}

	@Override
	public String updateEmail(List<EmailDTO> emailDto) {

		try {
			List<Email> aset = new ArrayList<>();
			Email email = new Email();

			emailDto.forEach(o -> {

				if (o.getCustomerId() != null) {
					setEmail(email, o);
					Customer c = customerRepository.findByCustomerId(o.getCustomerId());
					if (c != null) {
						c.setModifiedOn(LocalDateTime.now());
						email.setCustomer(c);
						aset.add(email);

					} else
						throw new TRPException(ErrorCode.CUSTOMERID_NOT_FOUND);

				} else {
					throw new TRPException(ErrorCode.IN_VALID_INPUT);
				}

			});

			emailRepository.saveAll(aset);
		} catch (Exception e) {
			logger.info("While updating exception occured {}", e.getCause());
			throw new TRPException(ErrorCode.ERROR_WHILE_RETRIEVING_DATA);

		}
		return "Updated Successfully";
	}

	/**
	 * @param add
	 * @param o
	 */
	private void setEmail(Email add, EmailDTO o) {
		if (o.getEmailType() != null) {
			add.setEmailType(o.getEmailType());
		}
		if (o.getEmailAddress() != null) {
			add.setEmailAddress(o.getEmailAddress());
		}

		if (o.getIsPrimary() != null) {
			add.setIsPrimary(o.getIsPrimary());
		}
		if (o.getId() != null) {
			add.setId(o.getId());
		}

	}

	@Override
	public String updatePhoneNumber(List<PhoneNumberDTO> pnDto) {

		try {
			List<PhoneNumber> aset = new ArrayList<>();
			PhoneNumber pn = new PhoneNumber();

			pnDto.forEach(o -> {
				if (o.getCustomerId() != null) {
					setPhoneNumber(pn, o);
					Customer c = customerRepository.findByCustomerId(o.getCustomerId());
					if (c != null) {
						c.setModifiedOn(LocalDateTime.now());
						pn.setCustomer(c);
						logger.info("customer-- {},ocustmerid{}", c.getCustomerId(), o.getCustomerId());
						aset.add(pn);

					} else
						throw new TRPException(ErrorCode.CUSTOMERID_NOT_FOUND);

				} else {
					throw new TRPException(ErrorCode.IN_VALID_INPUT);
				}

			});

			phoneNumberRepository.saveAll(aset);
		} catch (Exception e) {
			logger.info("While updating exception occured {}", e.getCause());
		}
		return "Updated Successfully";
	}

	private void setPhoneNumber(PhoneNumber pn, PhoneNumberDTO o) {
		if (o.getIsPrimary() != null) {
			pn.setIsPrimary(o.getIsPrimary());
		}
		if (o.getId() != null) {
			pn.setId(o.getId());
		}

		if (o.getCityCode() != 0) {
			pn.setCityCode(o.getCityCode());
		}

		if (o.getCountryCode() != 0) {
			pn.setCountryCode(o.getCountryCode());
		}

		if (o.getNumber() != null) {
			pn.setNumber(o.getNumber());
		}

	}

	@Override
	public List<Customer> getCustomers() {

		return customerRepository.findAll();

	}

	public List<Customer> searchCustomer(Customer customer) {
		List<Customer> listCustomer = new ArrayList<>();
		try {
			if (customer.getCustomerId() != null && !customer.getCustomerId().equalsIgnoreCase("")) {
				if (customer.getCustomerName() != null && !customer.getCustomerName().equalsIgnoreCase("")) {
					Optional<Customer> byCustomerIdAndFirstName = customerRepository
							.findByCustomerIdAndCustomerName(customer.getCustomerId(), customer.getCustomerName());
					if (byCustomerIdAndFirstName.isPresent()) {
						Customer getCustomer = byCustomerIdAndFirstName.get();
						listCustomer.add(getCustomer);
					} else {

						throw new TRPException(ErrorCode.CUSTOMERID_NOT_FOUND);
						// throw new TRPException("There is no customer with this id :" +
						// customer.getCustomerId() + " and name:" + customer.getCustomerName());
					}
				} else {
					Customer byCustomerId = customerRepository.findByCustomerId(customer.getCustomerId());
					if (byCustomerId != null) {
						listCustomer.add(byCustomerId);
					} else {
						throw new TRPException(ErrorCode.CUSTOMERID_NOT_FOUND);
						// throw new CustomerNotFoundException("There is no customer with this id :" +
						// customer.getCustomerId());
					}
				}
			} else {
				Optional<List<Customer>> byFirstName = customerRepository
						.findByCustomerName(customer.getCustomerName());
				if (byFirstName.isPresent()) {
					listCustomer = byFirstName.get();
				} else {
					throw new TRPException(ErrorCode.CUSTOMERID_NOT_FOUND);
					// throw new CustomerNotFoundException("There is no customer with this name:" +
					// customer.getCustomerName());
				}
			}
		} catch (Exception e) {

			throw new TRPException(ErrorCode.ERROR_WHILE_RETRIEVING_DATA);
			// throw new CustomerNotFoundException("There is a problem to find a
			// customer,Please try after sometime");
		}
		return listCustomer;
	}

	@Override
	public void softDeleteCustomer(String id) {

		Optional<Customer> customer = Optional.of(customerRepository.findByCustomerId(id));

		if (customer.isPresent()) {
			Customer cust = customer.get();
			if (cust.getCustomerStatus().toLowerCase().trim().equals("inactive")) {

				throw new TRPException(ErrorCode.CUSTOMERID_EXISTS);
				// throw new AlreadyDeactivateAccountException( "This account is already
				// inactive" );
				// throw a new Already Deactivate Account Exception method;
			} else {
				cust.setCustomerStatus("Inactive");
				cust.setModifiedBy("User");
				cust.setModifiedOn(LocalDateTime.now());
				customerRepository.save(cust);
			}

		} else {

			throw new TRPException(ErrorCode.CUSTOMERID_NOT_FOUND);
			// throw new CustomerNotFoundException( "There is no customer with this id :" +
			// id);
			// throw a new No Customer Found Exception method;

		}

	}

	@Override
	public void deleteCustomer(String cust_id) {

		String id = cust_id;
		Customer customer = customerRepository.findByCustomerId(id);
		if (customer == null) {

			throw new TRPException(ErrorCode.CUSTOMERID_NOT_FOUND);
			// throw new CustomerNotFoundException( "There is no customer with this id :" );
			// throw a new No Customer Found Exception method;
		} else
			customerRepository.delete(customerRepository.findByCustomerId(id));
	}

}
