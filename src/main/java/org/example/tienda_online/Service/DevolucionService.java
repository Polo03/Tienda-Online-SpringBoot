package org.example.tienda_online.Service;

import org.example.tienda_online.Dto.Devolucion;
import org.example.tienda_online.Repository.DevolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevolucionService {

    @Autowired
    private DevolucionRepository devolucionRepository;

    // Obtener todos los devoluciones
    public List<Devolucion> obtenerTodosDevoluciones() {
        return devolucionRepository.findAll();
    }

    // Obtener devolucion by ID
    public Optional<Devolucion> obtenerDevolucionByID(Integer id) {
        return devolucionRepository.findById(id);
    }

    //Guardar devolucion
    public Devolucion guardarDevolucion(Devolucion usuario) {
        Devolucion usuarioGuardar = devolucionRepository.save(usuario);
        return usuarioGuardar;
    }

    //Actualizar devolucion
    public boolean actualizarDevolucion(Devolucion nuevoUsuario) {
        Optional<Devolucion> devolucionExistente = devolucionRepository.findById(nuevoUsuario.getId());
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