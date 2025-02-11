package org.example.tienda_online.Controller;

import jakarta.validation.Valid;
import org.example.tienda_online.Dto.Compra;
import org.example.tienda_online.Dto.Producto;
import org.example.tienda_online.Service.CompraService;
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
@RequestMapping("/api/compras")
@CacheConfig(cacheNames = {"compras"})
public class CompraController {

    @Autowired
    private CompraService compraService;

    // Obtener todos los compras
    @GetMapping
    public ResponseEntity<List<Compra>> obtenerTodasCompras() {
        List<Compra> compras = compraService.obtenerTodasCompras();
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

    //Obtener compra por ID
    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Optional<Compra>> obtenerCompraPorId(@PathVariable Integer id) {
        try{
            Thread.sleep(3000);
            Optional<Compra> compra = compraService.obtenerCompraByID(id);
            return new ResponseEntity<>(compra, HttpStatus.OK);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    // Crear compra
    @PostMapping
    public ResponseEntity<String> guardarProducto(@RequestBody @Valid Compra compra) {
        if(compraService.comprobarStock(compra)) {
            compraService.quitarStock(compra);
            Compra compraGuardar = compraService.guardarCompra(compra);
            if (compraGuardar != null)
                return ResponseEntity.ok("Compra guardado con éxito");
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compra no guardado");
        }else
            return ResponseEntity.ok("Hay algun problema con el stock de algun producto");
    }

    //Actualizar compra
    @PutMapping
    public ResponseEntity<String> actualizarCompra(@RequestBody @Valid Compra nuevoCompra) {
        boolean actualizado = compraService.actualizarCompra(nuevoCompra);
        if (actualizado) {
            return ResponseEntity.ok("Compra actualizado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compra no encontrado");
        }
    }

    //Eliminar un compra por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCompra(@PathVariable int id) {
        boolean eliminado = compraService.eliminarCompra(id);

        if (eliminado) {
            return ResponseEntity.ok("Compra eliminado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compra no encontrado");
        }
    }

}
