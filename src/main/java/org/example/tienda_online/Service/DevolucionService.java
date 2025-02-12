package org.example.tienda_online.Service;

import org.example.tienda_online.Dto.Devoluciones;
import org.example.tienda_online.Repository.DevolucionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevolucionService {

    @Autowired
    private DevolucionesRepository devolucionRepository;

    // Obtener todos los devoluciones
    public List<Devoluciones> obtenerTodosDevoluciones() {
        return devolucionRepository.findAll();
    }

    // Obtener devolucion by ID
    public Optional<Devoluciones> obtenerDevolucionByID(Integer id) {
        return devolucionRepository.findById(id);
    }

    //Guardar devolucion
    public Devoluciones guardarDevolucion(Devoluciones usuario) {
        Devoluciones usuarioGuardar = devolucionRepository.save(usuario);
        return usuarioGuardar;
    }

    //Actualizar devolucion
    public boolean actualizarDevolucion(Devoluciones nuevoUsuario) {
        Optional<Devoluciones> devolucionExistente = devolucionRepository.findById(nuevoUsuario.getId());
        if (devolucionExistente.isPresent()) {
            devolucionRepository.save(nuevoUsuario);
            return true;
        }
        return false;
    }

    //Eliminar devolucion
    public boolean eliminarDevolucion(Integer id) {
        if (devolucionRepository.existsById(id)) {
            devolucionRepository.deleteById(id);
            return true;
        }
        return false;
    }

}