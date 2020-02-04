package com.tek.trp.CustomerService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCustomerDTO {

    private String customerId;
    private String firstName;
}
