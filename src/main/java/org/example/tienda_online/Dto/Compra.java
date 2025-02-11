package org.example.tienda_online.Dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "EL cliente no puede ser nulo.")
    private Cliente cliente;

    @Lob
    @Column(name = "productos", nullable = false)
    @NotNull(message = "No puedes comprar sin productos, es decir, no puede ser nulo")
    @NotBlank(message = "Los prodcutos no pueden estar en blanco")
    private String productos;

    @Lob
    @Column(name = "fecha_compra", nullable = false)
    private String fechaCompra;

    @Lob
    @Column(name = "stock", nullable = false)
    @NotNull(message = "No puedes comprar sin stock, es decir, no puede ser nulo")
    @NotBlank(message = "El stock no pueden estar en blanco")
    private String stock;

    @Column(name = "precio_compra", nullable = false)
    private Integer precioCompra;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Integer getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Integer precioCompra) {
        this.precioCompra = precioCompra;
    }

}