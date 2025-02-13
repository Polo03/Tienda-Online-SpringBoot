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
        compra.setFechaCompra(LocalDate.now()+"");
        //compra.setPrecioCompra(damePrecioCompra(compra));
        Compra compraGuardar = compraRepository.save(compra);
        return compraGuardar;
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


}
