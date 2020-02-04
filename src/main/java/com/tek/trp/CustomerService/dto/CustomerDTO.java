package com.tek.trp.CustomerService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {


    private Long id;
    private String customerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String branchId;
    private String branchName;
    private String branchCity;
    private String branchState;
    private String branchCountry;
    private String ifsCode;

}
