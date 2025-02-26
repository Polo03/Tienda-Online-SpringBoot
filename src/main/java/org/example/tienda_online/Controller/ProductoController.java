package org.example.tienda_online.Controller;

import jakarta.validation.Valid;
import org.example.tienda_online.Dto.Producto;
import org.example.tienda_online.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CacheConfig(cacheNames = {"productos"})
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosProductos() {
        List<Producto> productos = productoService.obtenerTodosProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    //Obtener producto por ID
    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Optional<Producto>> obtenerProductoPorId(@PathVariable Integer id) {
        try{
            Thread.sleep(3000);
            Optional<Producto> producto = productoService.obtenerProductoByID(id);
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    // Crear producto
    @PostMapping
    public ResponseEntity<String> guardarProducto(@RequestBody @Valid Producto producto) {
        System.out.println(producto);
        if(!productoService.existeNombreProductoInsert(producto)) {
            Producto productoGuardar = productoService.guardarProducto(producto);
            if (productoGuardar != null) {
                return ResponseEntity.ok("Producto guardado con éxito");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no guardado");
            }
        }else
            return ResponseEntity.ok("El nombre del producto ya existe");
    }

    //Actualizar producto
    @PutMapping
    public ResponseEntity<String> actualizarProducto(@RequestBody @Valid Producto nuevoProducto) {
        if(!productoService.existeNombreProductoUpdate(nuevoProducto)) {
            boolean actualizado = productoService.actualizarProducto(nuevoProducto);
            if (actualizado) {
                return ResponseEntity.ok("Producto actualizado con éxito");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
            }
        }else
            return ResponseEntity.ok("El nombre del producto ya existe");
    }

    //Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable int id) {
        boolean eliminado = productoService.eliminarProducto(id);

        if (eliminado) {
            return ResponseEntity.ok("Producto eliminado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }
}
