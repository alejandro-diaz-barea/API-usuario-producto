package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsuarioExistenteException extends RuntimeException {

    public UsuarioExistenteException() {
        super("Ya existe un usuario con el mismo nombre o características.");
    }
}