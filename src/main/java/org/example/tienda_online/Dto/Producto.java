package org.example.tienda_online.Dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

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
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "El precio del producto no puede tener letras")
    @NotNull(message = "El precio del producto no puede ser nulo")
    private Float precio;

    @Column(name = "stock")
    @Pattern(regexp = "^\\d{1,3}$", message = "El stock del producto no puede tener letras")
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

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

}