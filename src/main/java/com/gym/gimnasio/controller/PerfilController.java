package com.gym.gimnasio.controller;

import com.gym.gimnasio.model.Usuario;
import com.gym.gimnasio.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PerfilController {

    private final UsuarioService service;

    //Constructor para recibir el servicio de usuarios
    public PerfilController(UsuarioService service) {
        this.service = service;
    }

    //Mostrar perfil del cliente
    @GetMapping("/mi-perfil")
    public String mostrarPerfil(Authentication authentication, Model model) {

        //Buscar al cliente que inicio sesión
        Usuario usuario = service.buscarPorEmail(authentication.getName());

        //Dejar la contraseña vacía
        usuario.setPassword("");

        model.addAttribute("usuario", usuario);

        return "mi-perfil";
    }    

    //Actualizar perfil del cliente
    @PostMapping("/mi-perfil/actualizar")
    public String actualizarPerfil(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult errores, Authentication authentication, Model model, RedirectAttributes flash) {

        //Buscar al cliente que inicio sesión
        Usuario existente = service.buscarPorEmail(authentication.getName());

        //Si hay errores de validación se muestra el formulario de nuevo
        if(errores.hasErrors()) {
            usuario.setEmail(existente.getEmail());
            model.addAttribute("usuario", usuario);
            return "mi-perfil";
        }

        try {
            service.actualizarPerfil(existente.getId(), usuario);

            flash.addFlashAttribute("mensaje", "Perfil actualizado correctamente");

            return "redirect:/mi-perfil";
        }catch (IllegalArgumentException e) {
            usuario.setEmail(existente.getEmail());
            model.addAttribute("usuario", usuario);
            model.addAttribute("error", e.getMessage());
            return "mi-perfil";
        }
    }
}
