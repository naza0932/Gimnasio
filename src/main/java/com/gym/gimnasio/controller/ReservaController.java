package com.gym.gimnasio.controller;

import com.gym.gimnasio.model.Clase;
import com.gym.gimnasio.model.Usuario;
import com.gym.gimnasio.service.ClaseService;
import com.gym.gimnasio.service.ReservaService;
import com.gym.gimnasio.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller 
@RequestMapping("/reservas")
public class ReservaController {
    
    private final ReservaService service;
    private final ClaseService claseService;
    private final UsuarioService usuarioService;

    public ReservaController(ReservaService service, ClaseService claseService, UsuarioService usuarioService) {
        this.service = service;
        this.claseService = claseService;
        this.usuarioService = usuarioService;
    }

    //Registrar una reserva
    @PostMapping("/guardar/{id}")
    public String guardar(@PathVariable Long id, Authentication authentication, RedirectAttributes flash) {

        try {
            Clase clase = claseService.buscarPorId(id);
            Usuario cliente = usuarioService.buscarPorEmail(authentication.getName());

            service.crear(cliente, clase);

            flash.addFlashAttribute("mensaje", "Reserva realizada de manera exitosa!");

        }catch (IllegalArgumentException e) {
            flash.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/clases";



    }
}
