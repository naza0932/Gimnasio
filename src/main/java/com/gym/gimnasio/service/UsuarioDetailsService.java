package com.gym.gimnasio.service;

import com.gym.gimnasio.model.Usuario;
import com.gym.gimnasio.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    //Repositorio para buscar los usuarios
    private final UsuarioRepository repo;

    //Constructor para recibir el repositorio
    public UsuarioDetailsService(UsuarioRepository repo) {
        this.repo = repo;
    }

    //Buscar usuario por correo
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no encontrado: " + email));

        //Crear los datos para Spring Security para autenticar al usuario
        return User.withUsername(u.getEmail())
                .password(u.getPassword())
                .roles(u.getRol())
                .disabled(!u.isActivo())
                .build();
    }

    
}
