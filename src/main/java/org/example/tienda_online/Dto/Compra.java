package org.example.tienda_online.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Lob
    @Column(name = "fecha_compra")
    @JsonIgnore
    private String fechaCompra;

    @NotNull
    @Lob
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_compra")
    @JsonIgnore
    private BigDecimal precioCompra;

    @Column(name = "devuelto")
    @JsonIgnore
    private String devuelto;

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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getDevuelto() {
        return devuelto;
    }

    public void setDevuelto(String devuelto) {
        this.devuelto = devuelto;
    }

    /* JSON a insertar en el postman
        POST
    {
        {
        "cliente": {
            "id": 1,
        },
        "producto": {
            "id": 10,
        },
        "cantidad": 17,
    }
        PUT
    {
        "id": 1,
        "cliente": {
            "id": 1,
        },
        "producto": {
            "id": 10,
        },
        "cantidad": 17,
    }
    */
}