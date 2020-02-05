package com.tek.trp.CustomerService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customer")
public class Customer {


    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "branch_id")
    private String branchId;
    @Column(name = "branch_name")
    private String branchName;
    @Column(name = "branch_city")
    private String branchCity;
    @Column(name = "branch_state")
    private String branchState;
    @Column(name = "branch_country")
    private String branchCountry;
    @Column(name = "ifsc_code")
    private String ifsCode;
    @Column(name = "status")
    private String status;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addressList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Email> emailList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Phone> phoneList;


}
