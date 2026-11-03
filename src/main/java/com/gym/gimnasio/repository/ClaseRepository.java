package com.gym.gimnasio.repository;

import com.gym.gimnasio.model.Clase;
import com.gym.gimnasio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;


//Repositorio para realizar operaciones con las clases
public interface ClaseRepository extends JpaRepository<Clase, Long>{

    //Buscar clases asignadas a un entrenador con su fecha determinada
    List<Clase> findByEntrenadorAndFecha(Usuario entrenador, LocalDate fecha);

    //Buscar la clase por fecha
    List<Clase> findByFecha(LocalDate fecha);

    
}
