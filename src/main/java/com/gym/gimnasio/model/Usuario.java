package com.gym.gimnasio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Entity 
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    // El nombre del usuario no puede estar en blanco
    @NotBlank(message = "El nombre es obligatorio!")
    @Size(max = 100)
    private String nombre;

    //Cédula única del usuario
    @NotBlank (message = "La cédula es obligatoria!")
    @Pattern(regexp = "\\d{9}", message = "La cédula debe tener 9 dígitos")
    @Column(unique = true, nullable = false) 
    private String cedula;

    //Correo electrónico único para el inicio de sesión
    @NotBlank(message = "El correo electrónico es obligatorio!")
    @Email(message = "El correo electrónico no es válido")
    @Column(unique = true, nullable = false)
    private String email;

    //Teléfono del usuario de 8 dígitos
    @NotBlank(message = "El teléfono es obligatorio!")
    @Pattern(regexp = "\\d{8}", message = "El teléfono debe tener 8 dígitos")
    private String telefono;

    //Contraseña del usuario
    @NotBlank(message = "La contraseña es obligatoria!")
    private String password;

    //Rol del usuario, se dejo rol de ENTRENADOR o CLIENTE
    @Column(nullable = false)
    private String rol = "CLIENTE";

    //Indicador para saber de cuentas activas o inactivas
    @Column(nullable = false)
    private boolean activo = true;

    //Constructor vacío
    public Usuario() {
    }

    //Constructor de los datos del usuario
    public Usuario(String nombre, String cedula, String email, String telefono, String password, String rol) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.rol = rol;
    }

    //Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    
}
