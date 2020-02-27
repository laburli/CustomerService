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
@Table(name = "Address")
@Data
public class Address {
	@Id
	@Column(name = "AddressId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	@Column(name = "DoorNumber")
	private String doorNumber;
	@Column(name = "AddressType")
	private String addressType;
	@Column(name = "LandMark")
	private String landMark;
	@Column(name = "City")
	private String city;
	@Column(name = "State")
	private String state;
	@Column(name = "Country")
	private String country;
	@Column(name = "Pincode")
	private String pinCode;
	@Column(name = "IsPrimaryAddress")
	private Boolean isPrimary;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CustomerId")	
	 @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="customerId")
    @JsonIdentityReference(alwaysAsId=true)
	@JsonProperty("customerId")
	@EqualsAndHashCode.Exclude
    @ToString.Exclude		
	private Customer customer;

}
