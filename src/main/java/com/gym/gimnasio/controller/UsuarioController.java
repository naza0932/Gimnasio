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
    
}
