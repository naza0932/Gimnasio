package com.gym.gimnasio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Controlador de login
@Controller
public class LoginController {

    //Mostrar el formulario de inicio de sesión
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //Redirigir a la página principal del gimnasio
    @GetMapping("/")
    public String inicio(){
        return "redirect:/inicio";
    }    
}
