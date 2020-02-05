/**
 * This repository to access predefined  and implement custom queries of PhoneNumber table.
 */
package com.tek.trp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tek.trp.model.PhoneNumber;

/**
 * @author raadari
 *
 */
@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {

}
