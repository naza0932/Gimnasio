package com.gym.gimnasio.service;

import com.gym.gimnasio.model.Clase;
import com.gym.gimnasio.repository.ClaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ClaseService {

    private final ClaseRepository repo;

    public ClaseService(ClaseRepository repo){
        this.repo = repo;
    }

    //Listar todas las clases
    public List<Clase> listar(){
        return repo.findAll();
    }

    //Buscar una clase por ID
    public Clase buscarPorId(Long id){
        return repo.findById(id)
                 .orElseThrow(() -> new IllegalArgumentException("Clase no encontrada:" +id));
    }

    //Crear una clase
    @Transactional 
    public Clase crear(Clase clase){

        //Comprobar que el entrenador esta activo y su rol correcto
        if (clase.getEntrenador() == null || !clase.getEntrenador().isActivo() || !"ENTRENADOR".equals(clase.getEntrenador().getRol())) {

            throw new IllegalArgumentException("Debe seleccionar un entrenador activo!");

        }

        //Calcular la hora de finalización sumando una hora
        clase.setHoraFin(clase.getHoraInicio().plusHours(1));

        //Buscar las clases que tiene el entrenador en esa fecha
        List<Clase> clases = repo.findByEntrenadorAndFecha(clase.getEntrenador(), clase.getFecha());

        //Comprobar que no existe horarios superpuestos
        for(Clase existente : clases){
            if (clase.getHoraInicio().isBefore(existente.getHoraFin()) && clase.getHoraFin().isAfter(existente.getHoraInicio())){
                throw new IllegalArgumentException("El entrenador ya tiene una clase en ese horario");
            }
        }

        return repo.save(clase);

    }

    //Actualiar una clase
    @Transactional 
    public Clase actualizar(Long id, Clase datos){

        //Buscar la clase que se quiere modificar
        Clase existente = buscarPorId(id);

        //Comprobar que el entrenador este activo y sea rol entrenador
        if (datos.getEntrenador() == null || !datos.getEntrenador().isActivo() || !"ENTRENADOR".equals(datos.getEntrenador().getRol())){

            throw new IllegalArgumentException("Debe seleccionar un entrenador activo");
        }

        //Actualizar los datos de la clase
        existente.setNombre(datos.getNombre());
        existente.setTipo(datos.getTipo());
        existente.setEntrenador(datos.getEntrenador());
        existente.setFecha(datos.getFecha());
        existente.setHoraInicio(datos.getHoraInicio());
        existente.setCapacidad(datos.getCapacidad());

        //Calcular nuevamente la hora de finalización
        existente.setHoraFin(existente.getHoraInicio().plusHours(1));

        //Buscar la clase del entrenador en esa fecha
        List<Clase> clases = repo.findByEntrenadorAndFecha(existente.getEntrenador(), existente.getFecha());

        //Comprobar que no existe horario superpuestos
        for (Clase otraClase : clases){

            //Evitar comparar la clase consigo misma
            if (existente.getId().equals(otraClase.getId())){
                continue;
            }

            if (existente.getHoraInicio().isBefore(otraClase.getHoraFin()) && existente.getHoraFin().isAfter(otraClase.getHoraInicio())){

                throw new IllegalArgumentException("El entrenador ya tiene una clase en ese horario");
            }
        }

        return repo.save(existente);
    }

    
}
