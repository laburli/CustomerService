/**
 * 
 */
package com.tek.trp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tek.trp.model.Email;

/**
 * @author raadari
 *
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {

}
