package org.example.tienda_online.Service;

import org.example.tienda_online.Dto.Producto;
import org.example.tienda_online.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    public List<Producto> obtenerTodosProductos() {
        return productoRepository.findAll();
    }

    // Obtener producto by ID
    public Optional<Producto> obtenerProductoByID(Integer id) {
        return productoRepository.findById(id);
    }

    //Guardar producto
    public Producto guardarProducto(Producto producto) {
        producto.setTipoProducto(dameTipoProducto(producto));
        Producto usuarioGuardar = productoRepository.save(producto);
        return usuarioGuardar;
    }

    //Actualizar producto
    public boolean actualizarProducto(Producto nuevoProducto) {
        Optional<Producto> usuarioExistente = productoRepository.findById(nuevoProducto.getId());
        if (usuarioExistente.isPresent()) {
            productoRepository.save(nuevoProducto);
            return true;
        }
        return false;
    }

    //Eliminar producto
    public boolean eliminarProducto(Integer id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public String dameTipoProducto(Producto producto) {
        if(producto.getPrecio().compareTo(new BigDecimal(10)) < 0)
            return "Oferta";
        else if(producto.getPrecio().compareTo(new BigDecimal(200)) > 0)
            return "Calidad";
        else
            return "Estandar";
    }

}
