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
public class PhoneNumberDTO {
	private Long phoneNumberId;
	private String customerId;
	private String phoneNumberType;
	private int countryCode;
	private int cityCode;
	private String number;
	private Boolean isPrimaryNumber;
	

}
