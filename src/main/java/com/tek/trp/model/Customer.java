/**
 * This entity define its  attributes and table mappings.
 */
package com.tek.trp.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author raadari
 *
 */
@Entity
@Table(name = "Customer")
@Data
public class Customer {

	@Column(name = "CustomerName")
	private String customerName;
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CustomerId", unique = true, nullable = false)
	
	private String customerId;
	@Column(name = "Lastname")
	private String lastname;
	@Column(name = "MiddleName")
	private String middleName;
	@Column(name = "CustomerStatus")
	private String customerStatus;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Email> email;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<PhoneNumber> phoneNumber;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Address> address;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Account> account;
	
	@Column(name = "CreatedBy")
	private String createdBy;
	@Column(name = "CreatedOn")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss:SSS")
	private LocalDateTime createdOn;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "ModifiedOn")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss:SSS")
	private LocalDateTime modifiedOn;

	/*
	 * public void setAddress(Set<Address> address) { if (this.address == null) {
	 * this.address = address; } else { this.address.retainAll(address);
	 * this.address.addAll(address); } }
	 */

}
