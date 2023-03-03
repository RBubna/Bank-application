package com.example.bankBackend;

import com.example.bankBackend.model.Account;
import com.example.bankBackend.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BankBackendApplication.class, args);
	}

	@Autowired
	private BankRepository bankRepository;

	@Override
	public void run(String... args) throws Exception {

	}
}
