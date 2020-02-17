/**
 * 
 */
package com.tek.trp.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * @author raadari
 *
 */
@Getter
@Builder
public class CustomerDTO {
	
	private String customerId;
	private String customerName;
	private String lastName;
	private String middleName;
	

}
