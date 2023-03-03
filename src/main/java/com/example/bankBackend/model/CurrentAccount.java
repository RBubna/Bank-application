package com.example.bankBackend.model;

import com.example.bankBackend.exception.InsufficientFunds;

public class CurrentAccount extends Account {
    public void deposit(Account a, int amount) {
        a.setBalance(a.getBalance() + amount);
    }

    public boolean withdraw(Account a, int amount) {
        int amt = a.getBalance();

        if (amt - amount < 0) {
           return false;
        } else {
            a.setBalance(amt - amount);
            return true;
        }

    }
}
