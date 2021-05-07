package com.demo.tradebook.exception;

public class LowerVersionException extends Exception {
    public LowerVersionException(String message) {
        super(message);
    }
}
