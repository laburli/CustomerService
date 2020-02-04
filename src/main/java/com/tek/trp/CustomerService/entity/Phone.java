package com.tek.trp.CustomerService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="phone")
public class Phone {
    @Id
    @Column(name = "phone_id")
    private Long phoneId;
    @Column(name = "phone_type")
    private String phoneType;
    @Column(name = "city_code")
    private String cityCode;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "phone_number")
    private int phoneNumber;

    @ManyToOne
    @JoinColumn
    private Customer customer;

}
