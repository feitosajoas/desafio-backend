package com.example.contacts.exception;

import java.io.Serial;

public class ContactExistException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public ContactExistException(String message) {
        super(message);
    }


}
