/**
   * This entity define its  attributes and table mappings.
 */
package com.tek.trp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author raadari
 *
 */
@Entity
@Table(name = "Email")
@Data
public class Email {
	
	@Id
	@Column(name = "EmailId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "EmailType")
	String emailType ;
	@Column(name = "EmailAddress")
	String emailAddress ;
	@Column(name = "CustomerStatus")
	private String customerStatus;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId")
	@JsonProperty("customerId")
	@EqualsAndHashCode.Exclude
    @ToString.Exclude		
	private Customer customer;

}
