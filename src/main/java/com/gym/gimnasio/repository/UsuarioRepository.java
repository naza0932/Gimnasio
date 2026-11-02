package com.gym.gimnasio.repository;

import com.gym.gimnasio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

//Repositorio para las operaciones con los usuarios
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    //Buscar un usuario por correo electrónico
    Optional<Usuario> findByEmail(String email);

    //Comprobar si ya existe un correo electrónico igual
    boolean existsByEmail(String email);

    //Comprobar si ya existe una cédula igual
    boolean existsByCedula(String cedula);

    //Buscar el usuario por nombre sin importar mayusculas o minúsculas
    List<Usuario> findByNombreIgnoreCaseContaining(String nombre);

}
