package com.tek.trp.CustomerService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="email")
public class Email {

    @Id
    @Column(name = "em_id")
    private Long emId;
    @Column(name = "email_type")
    private String emailType;
    @Column(name = "email_address")
    private String emailAddress;

    @ManyToOne
    @JoinColumn(name = "id")
    private Customer customer;
}
