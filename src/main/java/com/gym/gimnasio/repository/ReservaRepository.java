package com.gym.gimnasio.repository;

import com.gym.gimnasio.model.Reserva;
import com.gym.gimnasio.model.Clase;
import com.gym.gimnasio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    //Consultar las reservas de una clase
    List<Reserva> findByClase(Clase clase);

    //Comprobar si un cliente ya reservó una clase
    boolean existsByClienteAndClase(Usuario cliente, Clase clase);
    
    
}
