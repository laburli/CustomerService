package com.tek.trp.CustomerService.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="address")
public class Address {

    @Id
    @Column(name = "address_id")
    private Long addressId;
    @Column(name = "address_type")
    private String addressType;
    @Column(name = "landmark")
    private String landmark;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "pincode")
    private int pinCode;

    @ManyToOne
    @JoinColumn
    private Customer customer;


}
