package com.gym.gimnasio.controller;

import com.gym.gimnasio.model.Clase;
import com.gym.gimnasio.service.ClaseService;
import com.gym.gimnasio.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller 
@RequestMapping("/clases")
public class GestionClaseController {

    private final ClaseService service;
    private final UsuarioService usuarioService;
    
    //Constructor para recibir los servicios
    public GestionClaseController(ClaseService service, UsuarioService usuarioService){
        this.service = service;
        this.usuarioService = usuarioService;
    }

    //Listar clases
    @GetMapping 
    public String listar(Model model){
        model.addAttribute("clases", service.listar());
        return "clases";
    }

    //Mostrar formulario para crear una clase
    @GetMapping("/nueva")
    public String nueva(Model model){
        model.addAttribute("clase", new Clase());
        model.addAttribute("entrenadores", usuarioService.listarEntrenadores());
        model.addAttribute("modo", "crear");
        return "formulario-clase";
    }

    //Guardar una clase
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("clase") Clase clase, BindingResult errores, @RequestParam(required = false) Long entrenadorId, Model model, RedirectAttributes flash){

        if (errores.hasErrors()|| entrenadorId == null) {
            model.addAttribute("entrenadores", usuarioService.listarEntrenadores());
            model.addAttribute("modo", "crear");
            return "formulario-clase";
        }

        try{
            clase.setEntrenador(usuarioService.buscarPorId(entrenadorId));
            service.crear(clase);

            flash.addFlashAttribute("mensaje", "Clase creada correctamente");
            return "redirect:/clases";

        }catch (IllegalArgumentException e){
            model.addAttribute("entrenadores", usuarioService.listarEntrenadores());
            model.addAttribute("modo", "crear");
            model.addAttribute("error", e.getMessage());
            return "formulario-clase";

        }
    }

    //Mostrar formulario para poder editar las clases
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model){
        Clase clase = service.buscarPorId(id);

        model.addAttribute("clase", clase);
        model.addAttribute("entrenadores", usuarioService.listarEntrenadores());
        model.addAttribute("modo", "editar");
        return "formulario-clase";
    }

    //Actualizar una clase
    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id, @Valid @ModelAttribute("clase") Clase clase, BindingResult errores, @RequestParam(required = false) Long entrenadorId, Model model, RedirectAttributes flash){

        if(errores.hasErrors() || entrenadorId == null ){
            clase.setId(id);
            model.addAttribute("entrenadores", usuarioService.listarEntrenadores());
            model.addAttribute("modo", "editar");
            return "formulario-clase";
        }

        try{
            clase.setEntrenador(usuarioService.buscarPorId(entrenadorId));
            service.actualizar(id,clase);

            flash.addFlashAttribute("mensaje", "Clase actualiza correctamente");
            return "redirect:/clases";

        }catch (IllegalArgumentException e){
            clase.setId(id);
            model.addAttribute("entrenadores", usuarioService.listarEntrenadores());
            model.addAttribute("modo", "editar");
            model.addAttribute("error", e.getMessage());
            return "formulario-clase";
        }
    }



}
