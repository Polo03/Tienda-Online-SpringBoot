package org.example.tienda_online.Controller;

import jakarta.validation.Valid;
import org.example.tienda_online.Dto.Devoluciones;
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

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Devoluciones>> obtenerTodosDevoluciones() {
        List<Devoluciones> devoluciones = devolucionService.obtenerTodosDevoluciones();
        return new ResponseEntity<>(devoluciones, HttpStatus.OK);
    }

    //Obtener usuario por ID
    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Optional<Devoluciones>> obtenerDevolucionPorId(@PathVariable Integer id) {
        try{
            Thread.sleep(3000);
            Optional<Devoluciones> devolucion = devolucionService.obtenerDevolucionByID(id);
            return new ResponseEntity<>(devolucion, HttpStatus.OK);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    // Crear usuario
    @PostMapping
    public ResponseEntity<String> guardarDevolucion(@RequestBody @Valid Devoluciones devolucion) {
        Devoluciones devolucionGuardar = devolucionService.guardarDevolucion(devolucion);
        if (devolucionGuardar != null) {
            return ResponseEntity.ok("Devolucion guardado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Devolucion no guardado");
        }
    }

    //Actualizar usuario
    @PutMapping
    public ResponseEntity<String> actualizarDevolucion(@RequestBody @Valid Devoluciones nuevoDevolucion) {
        boolean actualizado = devolucionService.actualizarDevolucion(nuevoDevolucion);
        if (actualizado) {
            return ResponseEntity.ok("Devolucion actualizado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Devolucion no encontrado");
        }
    }

    //Eliminar un usuario por ID
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
