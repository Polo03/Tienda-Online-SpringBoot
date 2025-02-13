package org.example.tienda_online.Service;

import org.example.tienda_online.Dto.Compra;
import org.example.tienda_online.Dto.Producto;
import org.example.tienda_online.Repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProductoService productoService;

    // Obtener todos los compras
    public List<Compra> obtenerTodasCompras() {
        return compraRepository.findAll();
    }

    // Obtener compra by ID
    public Optional<Compra> obtenerCompraByID(Integer id) {
        return compraRepository.findById(id);
    }

    //Guardar compra
    public Compra guardarCompra(Compra compra) {
        if(comprobarStock(compra)) {
            quitarStock(compra);
            compra.setFechaCompra(LocalDate.now()+"");
            compra.setPrecioCompra(damePrecioCompra(compra));
            return compraRepository.save(compra);
        }
        return null;
    }

    //Actualizar compra
    public boolean actualizarCompra(Compra nuevaCompra) {
        Optional<Compra> compraExistente = compraRepository.findById(nuevaCompra.getId());
        if (compraExistente.isPresent()) {
            compraRepository.save(nuevaCompra);
            return true;
        }
        return false;
    }

    //Eliminar compra
    public boolean eliminarCompra(Integer id) {
        if (compraRepository.existsById(id)) {
            compraRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean comprobarStock(Compra compra) {
        Optional<Producto> p = productoService.obtenerProductoByID(compra.getProducto().getId());
        return compra.getCantidad() <= p.get().getStock();
    }

    public void quitarStock(Compra compra) {
        Optional<Producto> p = productoService.obtenerProductoByID(compra.getProducto().getId());
        Producto pGuardar = p.get();
        pGuardar.setStock(pGuardar.getStock() - compra.getCantidad());
        productoService.actualizarProducto(pGuardar);
    }

    public BigDecimal damePrecioCompra(Compra compra){
        Optional<Producto> p = productoService.obtenerProductoByID(compra.getProducto().getId());
        return p.get().getPrecio().multiply(BigDecimal.valueOf(compra.getCantidad()));
    }



}
