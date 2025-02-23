package org.example.tienda_online.Service;

import org.example.tienda_online.Dto.Compra;
import org.example.tienda_online.Dto.Devolucion;
import org.example.tienda_online.Dto.Producto;
import org.example.tienda_online.Repository.DevolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevolucionService {

    @Autowired
    private DevolucionRepository devolucionRepository;

    @Autowired
    private CompraService compraService;

    @Autowired
    private ProductoService productoService;

    // Obtener todos los devoluciones
    public List<Devolucion> obtenerTodosDevoluciones() {
        return devolucionRepository.findAll();
    }

    // Obtener devolucion by ID
    public Optional<Devolucion> obtenerDevolucionByID(Integer id) {
        return devolucionRepository.findById(id);
    }

    //Guardar devolucion
    public Devolucion guardarDevolucion(int id) {
        Optional<Compra> c = compraService.obtenerCompraByID(id);
        Optional<Producto> p = productoService.obtenerProductoByID(c.get().getProducto().getId());
        Producto producto = p.get();
        producto.setStock(producto.getStock()+c.get().getCantidad());
        productoService.actualizarProducto(producto);
        if(c.get().getDevuelto().equals("No")){
            c.get().setDevuelto("Si");
            Devolucion d=new Devolucion();
            d.setCompra(c.get());
            Devolucion devolucionGuardar = devolucionRepository.save(d);
            System.out.println(devolucionGuardar);
            return devolucionGuardar;
        }
        return null;
    }

    //Actualizar devolucion
    public boolean actualizarDevolucion(Devolucion nuevoDevolucion) {
        Optional<Devolucion> devolucionExistente = devolucionRepository.findById(nuevoDevolucion.getId());
        if (devolucionExistente.isPresent()) {
            devolucionRepository.save(nuevoDevolucion);
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