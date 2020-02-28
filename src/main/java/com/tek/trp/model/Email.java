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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
	private Long emailId;
	@Column(name = "EmailType")
	String emailType ;
	@Column(name = "EmailAddress")
	String emailAddress ;
	@Column(name = "IsPrimaryEmail")
	private Boolean IsPrimaryEmail;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CustomerId")
	 @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="customerId")
    @JsonIdentityReference(alwaysAsId=true)
	@JsonProperty("customerId")
	@EqualsAndHashCode.Exclude
    @ToString.Exclude		
	private Customer customer;

}
