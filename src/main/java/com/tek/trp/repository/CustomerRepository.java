/**
 * This repository to access predefined  and implement custom queries of Customer table.
 */
package com.tek.trp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tek.trp.model.Customer;

/**
 * @author raadari
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	

		Customer findById(Optional<Integer> id);
		
		@Query("SELECT c FROM Customer c WHERE (:customerName is null or c.customerName = :name) and (:customerId is null"
				  + " or c.customerId = :customerId)")
		List<Customer> findCustomerByNameAndCustomerId(@Param("name") String customerName, @Param("customerId") String customerId);


	    Optional<Customer> findByCustomerId(String customerId);

	    Optional<Customer> findByCustomerIdAndCustomerName(String customerId, String customerName);

	    Optional<List<Customer>> findByCustomerName(String firstName);


}
