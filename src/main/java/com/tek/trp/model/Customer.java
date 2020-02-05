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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@Id
	@Column(name = "CID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "CustomerName")
	private String customerName;
	@Column(name = "CustomerId")
	private int customerId;
	@Column(name = "Lastname")
	private String lastname;
	@Column(name = "MiddleName")
	private String middleName;
	@Column(name = "BranchId")
	private int branchId;
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
	@Column(name = "CustomerStatus")
	private String customerStatus;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer",
			cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
    @ToString.Exclude	
	private Set<Email> email;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer",
			cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
    @ToString.Exclude	
	private Set<PhoneNumber> phoneNumber;
	@Column(name = "CreatedBy")
	private String createdBy;
	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer",
			cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
    @ToString.Exclude	
	private Set<Address> address;

}
