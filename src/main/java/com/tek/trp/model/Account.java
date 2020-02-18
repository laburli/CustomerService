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

import org.hibernate.annotations.NaturalId;

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
@Table(name = "Account")
@Data
public class Account {
	@Id
	@Column(name = "accountId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "AccountType")
	String accountType;
	@Column(name = "BranchId")
	private int branchId;
	@NaturalId
	@Column(name = "AccountNumber")
	private String accountNumber;
	@Column(name = "BranchName")
	private String branchName;
	@Column(name = "BranchCity")
	private String branchCity;
	@Column(name = "BranchState")
	private String branchState;
	@Column(name = "BranchCountry")
	private String branchCountry;
	@Column(name = "IFSCCode")
	private String ifscCode;
	@Column(name = "AccountStatus")
	private String accountStatus;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CustomerId")
	 @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="customerId")
    @JsonIdentityReference(alwaysAsId=true)
	@JsonProperty("customerId")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Customer customer;
}
