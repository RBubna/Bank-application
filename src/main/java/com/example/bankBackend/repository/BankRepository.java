package com.example.bankBackend.repository;

import com.example.bankBackend.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface BankRepository extends JpaRepository<Account, Long> {


  // all crud database methods
}
