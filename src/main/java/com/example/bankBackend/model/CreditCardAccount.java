package com.example.bankBackend.model;

import com.example.bankBackend.exception.InsufficientFunds;
import com.example.bankBackend.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class CreditCardAccount extends Account {

    @Autowired
    private BankRepository bankRepository;

    public void deposit(Account a, int amount) {
        a.setBalance(a.getBalance() + amount);
    }


    public boolean withdraw(Account a, int amount) {
        int amt = a.getBalance();

        if (amt - amount < -100000) {
            return false;
        } else {
            a.setBalance(amt - amount);
            return true;
        }


    }
}
