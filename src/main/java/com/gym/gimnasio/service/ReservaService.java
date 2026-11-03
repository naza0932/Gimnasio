package com.gym.gimnasio.service;

import com.gym.gimnasio.model.Reserva;
import com.gym.gimnasio.model.Clase;
import com.gym.gimnasio.model.Usuario;
import com.gym.gimnasio.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository repo;

    public ReservaService(ReservaRepository repo){
        this.repo = repo;
    }

    //Consultar las reservas de una clase
    public List<Reserva> listarPorClase(Clase clase) {
        return repo.findByClase(clase);
    }

    //Registrar una reserva
    @Transactional 
    public Reserva crear(Usuario cliente, Clase clase) {

        if(repo.existsByClienteAndClase(cliente, clase)) {

            throw new IllegalArgumentException("Ya tiene una reserva para esta clase!");


        }

        List<Reserva> reservas = repo.findByClase(clase);

        if (reservas.size() >= clase.getCapacidad()) {
            throw new IllegalArgumentException("No hay cupos disponibles para esta clase, lo sentimos");
        }

        Reserva reserva = new Reserva(cliente, clase);

        return repo.save(reserva);

    }
    
}
