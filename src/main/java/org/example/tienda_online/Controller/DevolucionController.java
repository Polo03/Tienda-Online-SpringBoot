package org.example.tienda_online.Controller;

import jakarta.validation.Valid;
import org.example.tienda_online.Dto.Devolucion;
import org.example.tienda_online.Service.DevolucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/devoluciones")
@CacheConfig(cacheNames = {"devoluciones"})
public class DevolucionController {

    @Autowired
    private DevolucionService devolucionService;

    // Obtener todos las devoluciones
    @GetMapping
    public ResponseEntity<List<Devolucion>> obtenerTodosDevoluciones() {
        List<Devolucion> devoluciones = devolucionService.obtenerTodosDevoluciones();
        return new ResponseEntity<>(devoluciones, HttpStatus.OK);
    }

    //Obtener devolucion por ID
    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Optional<Devolucion>> obtenerDevolucionPorId(@PathVariable Integer id) {
        try{
            Thread.sleep(3000);
            Optional<Devolucion> devolucion = devolucionService.obtenerDevolucionByID(id);
            return new ResponseEntity<>(devolucion, HttpStatus.OK);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    // Crear devolucion
    @PostMapping("/{id}")
    public ResponseEntity<String> guardarDevolucion(@PathVariable @Valid int id) {
        Devolucion devolucionGuardar = devolucionService.guardarDevolucion(id);
        if (devolucionGuardar != null) {
            return ResponseEntity.ok("Devolucion guardada con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La compra con id "+id+" ya ha sido devuelto");
        }
    }

    //Actualizar devolucion
    @PutMapping
    public ResponseEntity<String> actualizarDevolucion(@RequestBody @Valid Devolucion nuevoDevolucion) {
        boolean actualizado = devolucionService.actualizarDevolucion(nuevoDevolucion);
        if (actualizado) {
            return ResponseEntity.ok("Devolucion actualizado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Devolucion no encontrado");
        }
    }

    //Eliminar una devolucion por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDevolucion(@PathVariable int id) {
        boolean eliminado = devolucionService.eliminarDevolucion(id);

        if (eliminado) {
            return ResponseEntity.ok("Devolucion eliminado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Devolucion no encontrado");
        }
    }

}
