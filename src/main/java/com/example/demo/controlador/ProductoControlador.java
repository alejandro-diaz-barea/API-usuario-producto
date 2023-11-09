package com.example.demo.controlador;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.error.ProductoExistenteException;
import com.example.demo.error.ProductoNotFoundException;
import com.example.demo.modelo.Producto;
import com.example.demo.modelo.Usuario;
import com.example.demo.repos.ProductoRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/producto")
public class ProductoControlador {
    private final ProductoRepositorio productoRepositorio;

    public ProductoControlador(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    @GetMapping
    public List<ProductoDTO> getProductos(){
        List<ProductoDTO> resultado = new ArrayList<>();
        for (Producto producto: productoRepositorio.findAll()) resultado.add(new ProductoDTO(producto));
        return resultado;
    }

    @GetMapping("/{id}")
    public Producto getProducto(@PathVariable Long id){
        return productoRepositorio.findById(id).orElse(null);
    }

    /*¿Sería deseable tener  un producto repetido? Si es así, ¿como lo solucionarías?
     * No es deseable tener prodctos repetidos ya que despues darian conflicto de precios y confusiones de ID
     * para solucionarlo se debería poner la marca a la que pertenece el producto o algo que lo vuelva característico.*/
    @PostMapping("/")
    public ResponseEntity<Producto> createProducto(@Valid @RequestBody Producto producto){
        if (productoRepositorio.existsByName(producto.getName())) {
            throw new ProductoExistenteException();
        }

        Producto nuevoProducto = productoRepositorio.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @Valid @RequestBody Producto producto){
        return productoRepositorio.findById(id)
                .map(existingProducto -> {
                    existingProducto.setName(producto.getName());
                    existingProducto.setPrice(producto.getPrice());
                    return productoRepositorio.save(existingProducto);
                })
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id){
        return productoRepositorio.findById(id)
                .map(existingProducto -> {
                    productoRepositorio.delete(existingProducto);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    @PostMapping("/{id}/usuarios")
    public List<Usuario> getProductUsuarios(@PathVariable Long id){
        Producto producto = productoRepositorio.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        return producto.getUsuarios();
    }
}