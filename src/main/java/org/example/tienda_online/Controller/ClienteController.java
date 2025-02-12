package org.example.tienda_online.Controller;

import jakarta.validation.Valid;
import org.example.tienda_online.Dto.Cliente;
import org.example.tienda_online.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@CacheConfig(cacheNames = {"clientes"})
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    //Obtener usuario por ID
    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Optional<Cliente>> obtenerClientePorId(@PathVariable Integer id) {
        try{
            Thread.sleep(3000);
            Optional<Cliente> cliente = clienteService.obtenerClienteByID(id);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    // Crear usuario
    @PostMapping
    public ResponseEntity<String> guardarCliente(@RequestBody @Valid Cliente cliente) {
        Cliente clienteGuardar = clienteService.guardarCliente(cliente);
        if (clienteGuardar != null) {
            return ResponseEntity.ok("Cliente guardado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no guardado");
        }
    }

    //Actualizar usuario
    @PutMapping
    public ResponseEntity<String> actualizarCliente(@RequestBody @Valid Cliente nuevoCliente) {
        boolean actualizado = clienteService.actualizarCliente(nuevoCliente);
        if (actualizado) {
            return ResponseEntity.ok("Cliente actualizado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
    }

    //Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable int id) {
        boolean eliminado = clienteService.eliminarCliente(id);

        if (eliminado) {
            return ResponseEntity.ok("Cliente eliminado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> validarLogin(@RequestBody Cliente cliente){
        if(clienteService.validarLogin(cliente))
            return ResponseEntity.ok("Cliente logueado");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente no logueado");
    }

}
