package com.gym.gimnasio.controller;

import com.gym.gimnasio.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import com.gym.gimnasio.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class GestionUsuarioController {

    //Servicio para poder realizar operaciones con los usuarios
    private final UsuarioService service;

    //Constructor para recibir el servicio
    public GestionUsuarioController(UsuarioService service) {
        this.service = service;
    }

    //Mostrar la lista de usuarios registrados
    @GetMapping
    public String listar(@RequestParam(required = false) String q, Model model) {

        //Obtener los usuarios según la busqueda
        model.addAttribute("usuarios", service.listar(q));

        //Mantener el texto escrito en el buscador
        model.addAttribute("q", q);

        //Mostrar la página con la lista de usuarios
        return "usuarios";
    }

    //Mostrar formulario de edición de usuarios
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model){

        //Buscar el usuario que se quiere editar
        Usuario u = service.buscarPorId(id);

        //Dejar la contraseña vacia así no se muestra la cifrada
        u.setPassword("");

        //Enviar el usuario al formulario
        model.addAttribute("usuario", u);

        //Mostrar el formulario de edición
        return "editar-usuario";
    }  
    
    //Guardar cambios del usuario
    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id, @Valid @ModelAttribute("usuario") Usuario usuario, BindingResult errores, Model model, RedirectAttributes flash){

        //Comprobar si hay errores en los datos
        if (errores.hasErrors()){
            return "editar-usuario";

        }

        try {
        //Actualizar el usuario
        service.actualizar(id, usuario);

        //Mostrar mensaje de confirmación
        flash.addFlashAttribute("mensaje", "Usuario actualizado correctamente");

        return "redirect:/usuarios";

    } catch (IllegalArgumentException e) {
        model.addAttribute("error", e.getMessage());
        return "editar-usuario";
    }
            
    }
    
    
}
