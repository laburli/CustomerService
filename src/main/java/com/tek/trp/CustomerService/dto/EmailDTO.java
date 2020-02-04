package com.tek.trp.CustomerService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

    private int emId;
    private String emailType;
    private String emailAddress;
    private int id;
}
