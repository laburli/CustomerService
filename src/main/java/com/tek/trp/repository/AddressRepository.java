/**
 * This repository to access predefined  and implement custom queries of Address table.
 */
package com.tek.trp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tek.trp.model.Address;

/**
 * @author raadari
 *
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
