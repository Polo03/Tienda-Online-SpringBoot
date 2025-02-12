package org.example.tienda_online.Dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar en blanco")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo puede contener letras y espacios")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres.")
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    @NotNull(message = "El apellido no puede ser nulo")
    @NotBlank(message = "El apellido no puede estar en blanco")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido solo puede contener letras y espacios")
    @Size(max = 50, message = "El apellido no puede tener más de 50 caracteres.")
    private String apellido;

    @Column(name = "nickname", nullable = false, length = 50, unique = true)
    @NotNull(message = "El nickname no puede ser nulo")
    @NotBlank(message = "El nickname no puede estar en blanco")
    @Size(max = 50, message = "El nickname no puede tener más de 50 caracteres.")
    private String nickname;

    @Column(name = "password", nullable = false)
    @NotNull(message = "La contraseña no puede ser nula")
    @NotBlank(message = "La contraseña no puede estar en blanco")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
            message = "La contraseña debe tener al menos una mayúscula, una minúscula y un número")
    private String password;

    @Column(name = "telefono", length = 9)
    @Pattern(regexp = "^[69]\\d{8}$", message = "El teléfono debe tener 9 dígitos y empezar por 6 o 9")
    private String telefono;

    @Column(name = "domicilio", length = 100)
    @Size(max = 100, message = "El domicilio no puede tener más de 100 caracteres.")
    private String domicilio;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
}
