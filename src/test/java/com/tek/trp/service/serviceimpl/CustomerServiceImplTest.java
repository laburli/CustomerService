/**
 * 
 */
package com.tek.trp.service.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;

import com.tek.trp.dto.AddressDTO;
import com.tek.trp.dto.CustomerDTO;
import com.tek.trp.dto.EmailDTO;
import com.tek.trp.dto.PhoneNumberDTO;
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

/**
 * @author raadari
 *
 */
@TestPropertySource(locations = "/application.properties")
public class CustomerServiceImplTest {

	@Mock
	private AddressRepository addressRepository;
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private EmailRepository emailRepository;
	@Mock
	private PhoneNumberRepository phoneNumberRepository;

	@InjectMocks
	private CustomerServiceImpl customerService;

	private Customer customer;
	private List<Email> emails;
	private List<PhoneNumber> pNumbers;
	private List<Address> addresses;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

		customer = new Customer();
		customer.setCustomerId("62167833");
		customer.setCustomerName("CCP User1");
		customer.setCustomerStatus("Active");
		customer.setLastname("adari");
		customer.setMiddleName("c");
		customer.setCreatedBy("raviteja");
		customer.setCreatedOn(LocalDateTime.now());
		customer.setModifiedBy("none");
		customer.setModifiedOn(LocalDateTime.now());

		Address a = new Address();

		a.setAddressType("Permenent");
		a.setCity("Hyderabad");
		a.setCountry("GSP");
		a.setDoorNumber("88/134");
		a.setLandMark("Sub Register office lane");
		a.setPincode("500032");
		a.setState("Telangana");
		a.setIsPrimary(true);
		a.setCustomer(customer);

		Address a1 = new Address();

		a1.setAddressType("Tempm");
		a1.setCity("Hyderabad");
		a1.setCountry("india");
		a1.setDoorNumber("HIG-624");
		a1.setLandMark("Near Vijetha Super Market");
		a1.setPincode("500072");
		a1.setState("Telangana");
		a1.setCustomer(customer);
		a1.setIsPrimary(true);
		addresses = new ArrayList<>();
		addresses.add(a);
		addresses.add(a1);

		Email e = new Email();

		e.setEmailAddress("raadari@teksystem.com");
		e.setEmailType("office");
		e.setCustomer(customer);
		e.setIsPrimary(true);

		Email e1 = new Email();

		e1.setEmailAddress("raavitejaadaricse@gmail.com");
		e1.setEmailType("personal");
		e1.setCustomer(customer);
		e1.setIsPrimary(true);

		emails = new ArrayList<>();
		emails.add(e);
		emails.add(e1);

		PhoneNumber pn = new PhoneNumber();
		pn.setCityCode(40);
		pn.setCountryCode(91);
		pn.setPhoneType("Mobile");
		pn.setNumber("9492946341");
		pn.setCustomer(customer);
		pn.setIsPrimary(true);

		PhoneNumber pn1 = new PhoneNumber();
		pn1.setCityCode(40);
		pn1.setCountryCode(91);
		pn1.setPhoneType("Land");
		pn1.setNumber("98674373");
		pn1.setCustomer(customer);
		pn1.setIsPrimary(true);

		pNumbers = new ArrayList<>();
		pNumbers.add(pn);
		pNumbers.add(pn1);

		// Set<Account> accSet=new HashSet<>();
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
		acc.setCustomer(customer);

		Set<Account> accSet = new HashSet<>();
		accSet.add(acc);

		Set<Address> aset = new HashSet<>();
		Set<Email> eset = new HashSet<>();
		Set<PhoneNumber> pnSet = new HashSet<>();
		aset.add(a);
		aset.add(a1);
		eset.add(e);
		eset.add(e1);
		pnSet.add(pn);
		pnSet.add(pn1);
		customer.setAccount(accSet);
		customer.setAddress(aset);
		customer.setEmail(eset);
		customer.setPhoneNumber(pnSet);

	}

	@Test
	public void customerShouldSave1ThenReturnMessageFromService() throws Exception {

		when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

		Customer custom = customerService.saveCustomer(customer);
		assertEquals(customer, custom);
	}

	@Test
	public void customerShouldSaveThenReturnMessageFromService() throws Exception {

		when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

		Customer cust = customerService.saveCustomer(customer);
		assertEquals(customer, cust);
	}

	@Test
	public void AddressShouldUpdateThenReturnMessageFromService() throws Exception {

		List<AddressDTO> adtoList = new ArrayList<>();
		AddressDTO adto = new AddressDTO();
		adto.setAddressType("Permenent");
		adto.setCity("Hyderabad");
		adto.setCountry("GSP");
		adto.setDoorNumber("88/134");
		adto.setLandMark("Sub Register office lane");
		adto.setPincode("500032");
		adto.setState("Telangana");
		adto.setIsPrimary(true);
		adto.setCustomerId("62167833");
		adto.setId(1L);
		adtoList.add(adto);
		AddressDTO adto1 = new AddressDTO();
		adto1.setAddressType("Tempm");
		adto1.setCity("Hyderabad");
		adto1.setCountry("india");
		adto1.setDoorNumber("HIG-624");
		adto1.setLandMark("Near Vijetha Super Market");
		adto1.setPincode("500072");
		adto1.setState("Telangana");
		adto1.setCustomerId("62167833");
		adto1.setIsPrimary(true);

		when(customerRepository.findByCustomerId(Mockito.anyString())).thenReturn(customer);
		when(addressRepository.saveAll(Mockito.anyList())).thenReturn(addresses);

		String result = customerService.updateAddress(adtoList);
		assertEquals("Updated Successfully", result);
	}

	@Test
	public void EmailShouldUpdateThenReturnMessageFromService() throws Exception {

		List<EmailDTO> edtoList = new ArrayList<>();
		edtoList.add(EmailDTO.builder().id(2L).emailType("office").emailAddress("raadari@teksystems.com")
				.isPrimary(true).customerId("62167833").build());

		edtoList.add(EmailDTO.builder().id(1L).emailType("personal").emailAddress("raavitejaadaricse@gmail.com")
				.isPrimary(true).customerId("62167833").build());

		when(customerRepository.findByCustomerId(Mockito.anyString())).thenReturn(customer);

		when(emailRepository.saveAll(Mockito.anyList())).thenReturn(emails);

		String result = customerService.updateEmail(edtoList);
		assertEquals("Updated Successfully", result);
	}

	@Test
	public void customerShouldUpdateThenReturnMessageFromService() throws Exception {

		CustomerDTO cdtoList = CustomerDTO.builder().customerId("62167833").customerName("raviteja").middleName("c")
				.lastName("adari").build();

		when(customerRepository.findByCustomerId(Mockito.anyString())).thenReturn(customer);

		when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

		String result = customerService.updateCustomer(cdtoList);
		assertEquals("Customer data Updated", result);
	}
	
	
	@Test
	public void customerShouldSoftDeleteThenReturnMessageFromService() throws Exception {

		String customerId="62167833";

		when(customerRepository.findByCustomerId(Mockito.anyString())).thenReturn(customer);

		when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

		 customerService.softDeleteCustomer(customerId);
		
	}
	
	@Test
	public void customerShouldDeleteThenReturnMessageFromService() throws Exception {

		String customerId="62167833";

		when(customerRepository.findByCustomerId(Mockito.anyString())).thenReturn(customer);

	

		 customerService.deleteCustomer(customerId);
		 verify(customerRepository, times(1)).delete(customer);
		
	}

	@Test
	public void PhoneNumberShouldUpdateThenReturnMessageFromService() throws Exception {

		List<PhoneNumberDTO> pnDtoList = new ArrayList<>();

		pnDtoList.add(PhoneNumberDTO.builder().id(2L).phoneType("office").number("040325634").isPrimary(true).cityCode(40).countryCode(91)
				.customerId("62167833").build());

		pnDtoList.add(PhoneNumberDTO.builder().id(1L).phoneType("personal").number("9492946341").isPrimary(true).cityCode(40).countryCode(91)
				.customerId("62167833").build());

		when(customerRepository.findByCustomerId(Mockito.anyString())).thenReturn(customer);

		when(phoneNumberRepository.saveAll(Mockito.anyList())).thenReturn(pNumbers);

		String result = customerService.updatePhoneNumber(pnDtoList);
		assertEquals("Updated Successfully", result);
	}

	@Test
	public void getCustomers() throws TRPException {
		//Customer customer = getCustomerForId();
		// Customer optionalCustomer = Optional.of(customer);
		Mockito.when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
		List<Customer> cust = customerService.getCustomers();
		assertEquals(cust, Arrays.asList(customer));
	}

	
	@Test
	public void searchCustomerTestByIdAndName() throws TRPException {
		Customer customer = getCustomerByIdAndName();
		Optional<Customer> optionalCustomer = Optional.of(customer);
		when(customerRepository.findByCustomerIdAndCustomerName(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(optionalCustomer);
		List<Customer> cust = customerService.searchCustomer(customer);
		assertEquals(cust, Arrays.asList(customer));

	}

	@Test

	public void searchCustomerTestByIdAndNameThrowException() throws TRPException {
		// thrown.expect(CustomerNotFoundException.class);
		Customer customer = getCustomerByIdAndName();
		Optional<Customer> optionalCustomer = Optional.empty();
		when(customerRepository.findByCustomerIdAndCustomerName(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(optionalCustomer);
		assertThrows(TRPException.class, () -> {
			customerService.searchCustomer(customer);
		});
		// customerService.searchCustomer(customer);
	}

	@Test
	public void searchCustomerTestByName() throws TRPException {
		Customer customer = new Customer();

		customer.setCustomerName("CPP1");
		List<Customer> customerList = Arrays.asList(customer);
		Optional<List<Customer>> optionalCustomer = Optional.of(customerList);
		Mockito.when(customerRepository.findByCustomerName(Mockito.anyString())).thenReturn(optionalCustomer);
		List<Customer> cust = customerService.searchCustomer(customer);
		assertEquals(cust, Arrays.asList(customer));
	}

	@Test
	public void searchCustomerTestByNameThrowException() throws TRPException {
		// thrown.expect(CustomerNotFoundException.class);
		Customer customer = getCustomerForName();
		Optional<List<Customer>> optionalCustomer = Optional.empty();
		Mockito.when(customerRepository.findByCustomerName(Mockito.anyString())).thenReturn(optionalCustomer);
		assertThrows(TRPException.class, () -> {
			customerService.searchCustomer(customer);
		});
		// customerService.searchCustomer(customer);
	}

	@Test
	public void searchCustomerTestById() throws TRPException {
		Customer customer = getCustomerForId();
		// Customer optionalCustomer = Optional.of(customer);
		Mockito.when(customerRepository.findByCustomerId(Mockito.anyString())).thenReturn(customer);
		List<Customer> cust = customerService.searchCustomer(customer);
		assertEquals(cust, Arrays.asList(customer));
	}

	@Test

	public void searchCustomerTestByIdThrowException() throws TRPException {
		// thrown.expect(CustomerNotFoundException.class);
		Customer customer = getCustomerForId();

		Mockito.when(customerRepository.findByCustomerId(Mockito.anyString())).thenReturn(null);

		assertThrows(TRPException.class, () -> {
			customerService.searchCustomer(customer);
		});

	}

	private Customer getCustomerForId() {
		Customer customer = new Customer();

		customer.setCustomerId("CPP2");
		return customer;
	}

	private Customer getCustomerByIdAndName() {
		Customer customer = new Customer();

		customer.setCustomerName("CPP1");
		customer.setCustomerId("CPP2");
		return customer;
	}

	private Customer getCustomerForName() {
		Customer customer = new Customer();

		customer.setCustomerName("CPP1");
		return customer;
	}

	@Test
	public void updateCustomerExceptionTest() {

		CustomerDTO cdto = CustomerDTO.builder().customerId(null).customerName("Raviteja").middleName(null)
				.lastName(null).build();
		when(customerRepository.findByCustomerId(Mockito.anyString())).thenThrow(TRPException.class);

		assertThrows(TRPException.class, () -> {
			customerService.updateCustomer(cdto);
		});

	}

	@Test
	public void updateCustomerExceptionWhenNotFoundInputTest() {

		CustomerDTO cdto1 = CustomerDTO.builder().customerId("621").customerName("Raviteja").middleName(null)
				.lastName(null).build();
		when(customerRepository.findByCustomerId(Mockito.anyString())).thenThrow(TRPException.class);

		assertThrows(TRPException.class, () -> {
			customerService.updateCustomer(cdto1);
		});

	}

}
