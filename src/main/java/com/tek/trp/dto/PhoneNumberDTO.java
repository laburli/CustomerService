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
	private Long id;
	private String customerId;
	private String phoneType;
	private int countryCode;
	private int cityCode;
	private String number;
	private Boolean isPrimary;

}
