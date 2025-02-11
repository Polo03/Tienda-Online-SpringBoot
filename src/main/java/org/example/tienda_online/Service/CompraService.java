package org.example.tienda_online.Service;

import org.example.tienda_online.Dto.Compra;
import org.example.tienda_online.Dto.Producto;
import org.example.tienda_online.Repository.CompraRepository;
import org.example.tienda_online.Repository.ProductoRepository;
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
        compra.setPrecioCompra(damePrecioCompra(compra));
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

    public boolean comprobarStock(Compra compra) {
        String[] productos = compra.getProductos().split(" ");
        String[] stock = compra.getStock().split(" ");
        int cont=0;

        for (int i = 0; i < stock.length; i++) {
            Optional<Producto> producto = productoService.obtenerProductoByID(Integer.valueOf(productos[i]));
            if(producto.get().getStock()>=Integer.parseInt(stock[i]))
                cont++;
        }
        if(cont==productos.length)
            return true;
        return false;
    }

    public void quitarStock(Compra compra) {
        String[] productos = compra.getProductos().split(" ");
        String[] stock = compra.getStock().split(" ");
        for (int i = 0; i < stock.length; i++) {
            Optional<Producto> producto = productoService.obtenerProductoByID(Integer.valueOf(productos[i]));
            if(producto.get().getStock()>=Integer.parseInt(stock[i])){
                producto.get().setStock(producto.get().getStock()-Integer.parseInt(stock[i]));
                productoService.actualizarProducto(producto.get());
            }
        }
    }

    public int damePrecioCompra(Compra compra) {
        String[] productos = compra.getProductos().split(" ");
        String[] stock = compra.getStock().split(" ");
        int precioCompra=0;
        for (int i = 0; i < productos.length; i++) {
            Optional<Producto> producto = productoService.obtenerProductoByID(Integer.valueOf(productos[i]));
            BigDecimal precio = producto.get().getPrecio().multiply(BigDecimal.valueOf(Integer.parseInt(stock[i])));
            precioCompra+=precio.intValue();
        }
        return precioCompra;
    }

}
