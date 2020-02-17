/**
 * 
 */
package com.tek.trp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author raadari
 *
 */

@Getter
@Setter
public class AddressDTO {
	
	private Long id;
	
	private String doorNumber;
	
	private String addressType;
	
	private String landMark;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String pincode;	
	
	
	private Boolean isPrimary;
	private String customerId;

}
