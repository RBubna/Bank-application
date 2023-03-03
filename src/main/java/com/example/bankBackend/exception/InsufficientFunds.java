package com.example.bankBackend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InsufficientFunds extends RuntimeException {
    public InsufficientFunds(String message) {super(message); }
}

