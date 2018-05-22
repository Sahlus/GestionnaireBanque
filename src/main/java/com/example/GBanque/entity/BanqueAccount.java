package com.example.GBanque.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="Bank_Account")
@Data
public class BanqueAccount {

    @Id
    @GeneratedValue
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="Full_Name",length=128,nullable=false)
    private String fullName;

    @Column(name="Balance",nullable=false)
    private double balance;

}
