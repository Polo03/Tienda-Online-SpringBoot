package org.example.tienda_online.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotNull(message = "El nombre del producto no puede ser nulo")
    @NotBlank(message = "El nombre del producto no puede estar en blanco")
    @Pattern(regexp = "^[^\\d]*$", message = "El nombre del producto no puede tener números")
    @Size(max = 100, message = "El nombre del producto no puede tener más de 100 caracteres.")
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    @Size(max = 200, message = "La descripción no puede tener más de 200 caracteres.")
    private String descripcion;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2) // Permite hasta 10 dígitos enteros y 2 decimales
    @NotNull(message = "El precio del producto no puede ser nulo")
    private BigDecimal precio;

    @Lob
    @Column(name = "tipo_producto")
    private String tipoProducto;

    @Column(name = "stock")
    @Min(1) // Asegura que el valor de stock sea mayor o igual a 0
    @Max(100) // Asegura que el valor de stock no sea mayor a 100
    @NotNull(message = "El stock del producto no puede ser nulo")
    private Integer stock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", tipoProducto='" + tipoProducto + '\'' +
                ", stock=" + stock +
                '}';
    }
}