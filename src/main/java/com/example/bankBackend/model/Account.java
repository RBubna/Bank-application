package com.example.bankBackend.model;
import com.example.bankBackend.exception.InsufficientFunds;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Account")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bank_account;
    @Column(name = "name")
    private String name;
    @Column(name = "balance")
    private int balance = 0;
    @Column(name = "account_type")
    private String account_type;

}





