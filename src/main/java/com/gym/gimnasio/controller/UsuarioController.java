package com.gym.gimnasio.controller;

import com.gym.gimnasio.model.Usuario;
import com.gym.gimnasio.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller 
@RequestMapping("/registro")
public class UsuarioController {

    //Realizar las operaciones con los usuarios
    private final UsuarioService service;

    //Constructor para recibir el servicio
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    //Mostrar formulario de registro
    @GetMapping
    public String mostrarFormulario(Model model) {

        //Crear usuario vacío para llenar el formulario
        model.addAttribute("usuario", new Usuario());

        //Mostrar la página de registro
        return "registro";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult errores, Model model, RedirectAttributes flahs) {

        //Comprobar errores en los datos ingresados
        if (errores.hasErrors()) {
            return "registro";
        }

        //Darle el rol de CLIENTE al usuario que se registro
        usuario.setRol("CLIENTE");
        try{

            //Guardar el usuario mediante el servicio
            service.crear(usuario);
            //Mostrar mensaje de registro exito
            flahs.addFlashAttribute("mensaje", "Usuario registrado correctamente");

            //Redirigir al login
            return "redirect:/login";

        }catch (IllegalArgumentException e) {

            //Mostrar error si el correo o la cédula ya existen
            model.addAttribute("error", e.getMessage());
            return "registro";
            
        }
       
    }

    
}
