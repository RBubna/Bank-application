package com.example.bankBackend.controller;

import com.example.bankBackend.exception.InsufficientFunds;
import com.example.bankBackend.exception.ResourceNotFoundException;
import com.example.bankBackend.model.*;
import com.example.bankBackend.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankApplication")
public class BankController {
    @Autowired
    private BankRepository bankRepository;


    @GetMapping
    public List<Account> getAllAccount()
    {
        return bankRepository.findAll();
    }



    //POST -  CREATING ACCOUNT
    @PostMapping
    public Account createBankAccount(@RequestBody Account account)
    {
        return bankRepository.save(account);
    }



    //POST - DEPOSIT MONEY
    @PostMapping("{account_number}")
    public ResponseEntity<HttpStatus> depositAmount(@PathVariable long account_number, @RequestParam int deposit)
    {
        Account account = bankRepository.findById(account_number)
                .orElseThrow(() -> new ResourceNotFoundException("No account"));
        account.setBalance(account.getBalance() + deposit);
        bankRepository.save(account);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    //POST - TRANSFER MONEY

    @PostMapping("/transfer/{from_account_number}")
    public ResponseEntity<HttpStatus> transferAmount(@PathVariable long from_account_number, @RequestParam long to_account_number, @RequestParam int transfer_amount)
    {
        Account from_account = bankRepository.findById(from_account_number)
                .orElseThrow(()-> new ResourceNotFoundException("No account found"));
        Account to_account = bankRepository.findById(to_account_number)
                .orElseThrow(() -> new ResourceNotFoundException("No account found"));

        String type_from = from_account.getAccount_type();
        boolean t=true;
        if(type_from.equals("saving"))
        {
            SavingAccount s = new SavingAccount();
            t= s.withdraw(from_account, transfer_amount);
        }
        else if(type_from.equals("loan"))
        {
            LoanAccount l = new LoanAccount();
            t=l.withdraw(from_account, transfer_amount);
        }
        else if(type_from.equals("current"))
        {
            CurrentAccount c = new CurrentAccount();
            t=c.withdraw(from_account, transfer_amount);

        }
        else if(type_from.equals("credit"))
        {
            CreditCardAccount c = new CreditCardAccount();
            t=c.withdraw(from_account, transfer_amount);
        }

        if(!t)
            {
                throw(new InsufficientFunds("insufficient funds"));

            }
            else
            {
                to_account.setBalance(to_account.getBalance() + transfer_amount);
                bankRepository.save(from_account);
                bankRepository.save(to_account);
            }
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{account_number}")
    public ResponseEntity<HttpStatus> changeName(@PathVariable long account_number, @RequestParam String name)
    {
        Account account = bankRepository.findById(account_number)
                .orElseThrow(() -> new ResourceNotFoundException("No account found"));
        account.setName(name);
        bankRepository.save(account);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //GET - CHECK BALANCE
    @GetMapping("{account_number}/balance")
    public Integer getAccountByID (@PathVariable long account_number) {
        Account account = bankRepository.findById(account_number)
                .orElseThrow(() -> new ResourceNotFoundException("No account with:" + account_number));
        return account.getBalance();
    }

    //DELETE - CLOSE ACCOUNT
    @DeleteMapping("{account_number}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long account_number)
    {
        Account account = bankRepository.findById(account_number)
                .orElseThrow(() -> new ResourceNotFoundException("No account found"));
        bankRepository.delete(account);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
