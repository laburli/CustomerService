package com.tek.trp.CustomerService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    private int phoneId;
    private String phoneType;
    private String cityCode;
    private String countryCode;
    private int phoneNumber;
    private int id;
}
