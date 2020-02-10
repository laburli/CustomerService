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
@Table(name = "Account")
@Data	
public class Account {
	@Id
	@Column(name = "accountId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "AccountType")
	String accountType ;
	@Column(name = "BranchId")
	private int branchId;
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
	@JoinColumn(name = "customerId")
	@JsonProperty("customerId")
	@EqualsAndHashCode.Exclude
    @ToString.Exclude		
	private Customer customer;
}
