package com.libraryMicroservice.exception;

public class BookTitleNotFoundException extends RuntimeException{
    public BookTitleNotFoundException(String message) {
        super(message);
    }
}
