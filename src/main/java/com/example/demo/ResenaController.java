package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ResenaController {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private ResenaRepository resenaRepository;

    // Mostrar formulario para añadir una reseña
    @GetMapping("/nuevaResena")
    public String mostrarFormularioResena(Model model) {
        // Obtener todos los libros de la base de datos
        List<Libro> libros = libroRepository.findAll();
        model.addAttribute("libros", libros); // Pasar los libros al modelo
        return "nuevaResena"; // Nombre de la plantilla de la vista
    }

    // Guardar la reseña
    @PostMapping("/guardarResena")
    public String guardarResena(@RequestParam("libro_id") Long libroId, 
                                 @RequestParam("estrellas") int estrellas, 
                                 @RequestParam("resena") String reseña) {
        // Lógica para guardar la reseña
        Libro libro = libroRepository.findById(libroId).orElse(null);
        if (libro != null) {
            Resenas nuevaResena = new Resenas();
            nuevaResena.setLibro(libro); // Asociamos la reseña con el libro
            nuevaResena.setEstrellas(estrellas);
            nuevaResena.setResena(reseña);
            nuevaResena.setFecha(LocalDateTime.now()); // Fecha actual usando LocalDateTime

            resenaRepository.save(nuevaResena); // Guardar la reseña en la base de datos
        }
        return "redirect:/"; // Redirigir a la página principal después de guardar
    }

    // Mostrar las reseñas de un libro específico
    @GetMapping("/resenas")
    public String verResenas(@RequestParam("libroId") Long libroId, Model model) {
        // Obtener las reseñas de un libro específico
        List<Resenas> resenas = ResenaRepository.findByLibroId(libroId);
        model.addAttribute("resenas", resenas);
        model.addAttribute("libroId", libroId);
        return "resenas"; // Vista que muestra todas las reseñas para el libro
    }
}
