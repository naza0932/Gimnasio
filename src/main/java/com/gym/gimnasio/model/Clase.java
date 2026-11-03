package com.gym.gimnasio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalTime;

//Indica que esta clase representa una tabla en la base de datos
@Entity 
@Table(name = "clases")
public class Clase {
    
    //Identificador único de cada clase
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Nombre de la clase del gimnasio
    @NotBlank(message = "El nombre de la clase es obligatoria!")
    private String nombre;

    //Actividad que se realizara
    @NotBlank(message = "El tipo de clase es obligatoria!")
    private String tipo;

    //Entrenador que se encarga de impartir clase y se relaciona con la tabla de usuarios
    @ManyToOne 
    @JoinColumn(name = "entrenador_id", nullable = false)
    private Usuario entrenador;

    //Fecha en la que la clase se va impartir
    @NotNull(message = "La fecha es obligatoria!")
    private LocalDate fecha;

    //Hora en la que inicia la clase
    @NotNull(message = "La hora de inicio es obligatoria!")
    private LocalTime horaInicio;

    //Hora en la que finaliza la clase se calculará automaticamente sumando una hora al inicio
    private LocalTime horaFin;
    
    //Cantidad máxima de personas que pueden estar
    @NotNull(message = "La capacidad es obligatoria!")
    @Min(value = 1, message = "La capacidad debe ser mayor que cero")
    private Integer capacidad;

    //Constructor vacío
    public Clase(){

    }

    //Getters y setters, para tener datos de clase y también poder modificar
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public Usuario getEntrenador(){
        return entrenador;
    }

    public void setEntrenador(Usuario entrenador){
        this.entrenador = entrenador;
    }

    public LocalDate getFecha(){
        return fecha;
    }

    public void setFecha(LocalDate fecha){
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio(){
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio){
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin(){
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin){
        this.horaFin = horaFin;
    }

    public Integer getCapacidad(){
        return capacidad;
    }

    public void setCapacidad(Integer capacidad){
        this.capacidad = capacidad;
    }

}
