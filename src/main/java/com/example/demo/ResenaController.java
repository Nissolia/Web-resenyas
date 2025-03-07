package com.example.demo;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ResenaController {

    @Autowired
    private ResenaRepository resenasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroRepository libroRepository;

    // Método para mostrar el formulario de reseña
    @GetMapping("/nuevaResena")
    public String formularioResena(Model model) {
        // Obtener la lista de libros desde la base de datos
        model.addAttribute("libros", libroRepository.findAll());
        model.addAttribute("resena", new Resenas()); // Crear una reseña vacía
        return "formularioResena"; // Vista para añadir una nueva reseña
    }

    // Método para guardar la reseña
    @PostMapping("/guardarResena")
    public String guardarResena(@ModelAttribute Resenas resena, @RequestParam("libroId") Long libroId,
                                 RedirectAttributes attributes, Authentication authentication) {

        // Obtener el libro seleccionado por el ID
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        // Obtener el usuario autenticado (usando el nombre de usuario)
        String username = authentication.name(); // Obtener el nombre de usuario desde el contexto de autenticación
        Usuarios usuario = usuarioRepository.findByNombre(username) // Buscar por nombre de usuario
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Asociar la reseña con el libro y el usuario
        resena.setLibro(libro);
        resena.setUsuario(usuario);
        resena.setFecha(LocalDateTime.now());

        // Guardar la reseña en la base de datos
        resenasRepository.save(resena);

        attributes.addFlashAttribute("mensaje", "Reseña guardada exitosamente");
        return "redirect:/resenas"; // Redirige a la lista de reseñas
    }

   

    // Mostrar todas las reseñas de un libro
    @GetMapping("/resenas")
    public String verResenas(Model model) {
        List<Resenas> resenas = resenasRepository.findAll();
        model.addAttribute("resenas", resenas); // Agregar las reseñas al modelo
        return "listaResenas"; // Devolver la vista que muestra todas las reseñas
    }

}
