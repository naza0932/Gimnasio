package com.gym.gimnasio.model;

import jakarta.persistence.*;

@Entity 
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Cliente realiza su reserva
    @ManyToOne 
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    //Clase en la que realiza la reserva
    @ManyToOne 
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    public Reserva(){

    }

    public Reserva(Usuario cliente, Clase clase){
        this.cliente = cliente;
        this.clase = clase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase =  clase;
    }


    
}
