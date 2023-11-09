package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductoNotFoundException extends RuntimeException {
    private  static final long serialVersionUID = 43876691117560211L;

    public ProductoNotFoundException(Long id){
        super("No se ha encontrado el usrario con id:"+id);
    }
}