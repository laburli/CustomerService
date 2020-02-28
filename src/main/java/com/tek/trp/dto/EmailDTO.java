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
public class EmailDTO {
	
	
	private Long id;
	String customerId;
	String emailType ;
	String emailAddress ;
	private Boolean isPrimary;
	

}
