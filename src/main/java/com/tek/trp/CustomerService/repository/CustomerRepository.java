package com.tek.trp.CustomerService.repository;

import com.tek.trp.CustomerService.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByCustomerId(String customerId);

    Optional<Customer> findByCustomerIdAndFirstName(String customerId, String firstName);

    Optional<List<Customer>> findByFirstName(String firstName);

    @Query("SELECT coalesce(max(customer.id), 0) FROM  Customer customer")
    Long getMaxId();

}
