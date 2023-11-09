package com.example.demo.dto;


import com.example.demo.modelo.Producto;
import com.example.demo.modelo.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ProductoDTO {
    Long id;
    String name;
    BigDecimal price;
    List<Long> usuarios = new ArrayList<>();

    public ProductoDTO(Producto producto){
        id = producto.getId();
        name = producto.getName();
        price = producto.getPrice();
        for (Usuario usuario:
                producto.getUsuarios()){
            usuarios.add(producto.getId());
        }
    }
}