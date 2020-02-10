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
@Table(name = "Address")
@Data
public class Address {
	@Id
	@Column(name = "AddressId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
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
	private String pincode;
	@Column(name = "customerStatus")
	private String customerStatus;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId")
	@JsonProperty("customerId")
	@EqualsAndHashCode.Exclude
    @ToString.Exclude		
	private Customer customer;

}
