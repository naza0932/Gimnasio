package com.gym.gimnasio.service;

import com.gym.gimnasio.model.Usuario;
import com.gym.gimnasio.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UsuarioService {

    //Repositorio para trabajar con usuarios
    private final UsuarioRepository repo;

    //Crifrar la contraseña
    private final PasswordEncoder passwordEncoder;

    //Constructor para recibir el repositorio y el cifrador
    public UsuarioService(UsuarioRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    //Listar usuarios
    public List<Usuario> listar(String q) {

        //Si no se escribe nada, solo se van a mostrar todos los usuarios
        if (q == null || q.isBlank()){
            return repo.findAll();
        }

        return repo.findByNombreIgnoreCaseContaining(q);
    }

    //Buscar usuario por ID
    public Usuario buscarPorId(Long id) {

        return repo.findById(id).
                 orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado" + id));
    }

    //Crear usuario
    @Transactional
    public Usuario crear(Usuario usuario){

        //Comprobrar si el correo ya esta registrado
        if (repo.existsByEmail(usuario.getEmail())){
            throw new IllegalArgumentException("El correo ya esta registrado!");
        }

        //Comprobar si la cédula ya esta registrada
        if (repo.existsByCedula(usuario.getCedula())){
            throw new IllegalArgumentException("La cédula ya esta registrada!");
        }

        //Cifrar la contraseña antes de guardarla
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return repo.save(usuario);

    }


    //Actualizar usuarios
    @Transactional
    public Usuario actualizar(Long id, Usuario datos){

        //Buscar el usuario que se quiere modificar
        Usuario existente = buscarPorId(id);

        //Comprobar si el correo ya esta registrado
        if (!existente.getEmail().equals(datos.getEmail())
                && repo.existsByEmail(datos.getEmail())){
            throw new IllegalArgumentException("El correo ya esta registrado!");
        }

        //Comprobar si la cédula ya esta registrada
        if (!existente.getCedula().equals(datos.getCedula())
                && repo.existsByCedula(datos.getCedula())){
            throw new IllegalArgumentException("La cédula ya esta registrada!");
        }

        //Actualizar los datos del usuario
        existente.setNombre(datos.getNombre());
        existente.setCedula(datos.getCedula());
        existente.setEmail(datos.getEmail());
        existente.setTelefono(datos.getTelefono());
        existente.setRol(datos.getRol());

        //Cambiar la contraseña si se proporciona una nueva
        if (datos.getPassword() != null && !datos.getPassword().isBlank()) {
            existente.setPassword(passwordEncoder.encode(datos.getPassword()));
        }

        return repo.save(existente);
    }

    //Desactivar usuario
    @Transactional
    public void desactivar(Long id) {
        //Buscar el usuario
        Usuario usuario = buscarPorId(id);
        //Cambiar el estado a inactivo
        usuario.setActivo(false);
        repo.save(usuario);
    }

    
}
