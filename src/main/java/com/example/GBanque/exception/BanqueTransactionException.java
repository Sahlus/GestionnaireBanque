package com.example.GBanque.exception;

public class BanqueTransactionException extends Exception {
    private static final long serialVersionUID = -3128681006635769411L;

    public BanqueTransactionException(String message) {
        super(message);
    }
}
