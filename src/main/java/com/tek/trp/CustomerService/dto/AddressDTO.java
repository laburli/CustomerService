package com.tek.trp.CustomerService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {


    private int addressId;
    private String addressType;
    private String landmark;
    private String city;
    private String state;
    private String country;
    private int pinCode;
    private int id;
}
