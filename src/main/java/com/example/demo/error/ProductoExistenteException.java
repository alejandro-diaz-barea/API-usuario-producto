package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductoExistenteException extends RuntimeException {

    public ProductoExistenteException() {
        super("Ya existe un producto con el mismo nombre o características.");
    }
}