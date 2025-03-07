package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class LibroController {

    @Autowired
    private ResenaRepository resenasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Repositorio de usuarios

    @Autowired
    private LibroRepository libroRepository; // Repositorio de libros

    // Página principal que muestra la lista de libros
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("libros", libroRepository.findAll());
        return "listaLibros"; // Mostrar lista de libros
    }

    // Selecciona un libro y guarda su ID en la sesión
    @GetMapping("/seleccionar")
    public String seleccionarLibro(@RequestParam(name = "id") Long id, HttpSession session) {
        session.setAttribute("libroId", id); // Guarda el ID en la sesión
        return "redirect:/detalle"; // Redirige a detalle
    }

    // Página para crear un nuevo libro (formulario)
    @GetMapping("/nuevo")
    public String formularioLibro(Model model) {
        model.addAttribute("libro", new Libro());
        return "nuevo"; // Vista para añadir un nuevo libro
    }

    // Guarda el libro en la base de datos
    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute Libro libro, RedirectAttributes attributes) {
        libroRepository.save(libro);
        attributes.addFlashAttribute("mensaje", "¡Libro agregado exitosamente!");
        return "redirect:/"; // Redirigir a la página principal
    }

    // Muestra el detalle del libro usando el ID almacenado en la sesión
    @GetMapping("/detalle")
    public String detalleLibro(HttpSession session, Model model) {
        Long libroId = (Long) session.getAttribute("libroId");
        if (libroId != null) {
            Libro libro = libroRepository.findById(libroId)
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
            List<Resenas> resenas = ResenaRepository.findByLibroId(libroId);
            model.addAttribute("libro", libro);
            model.addAttribute("resenas", resenas);
            return "detalleLibro"; // Vista de detalle del libro
        }
        model.addAttribute("error", "No se ha seleccionado un libro.");
        return "error"; // En caso de error
    }

    // Filtrar libros por género
    @GetMapping("/filtrarLibros")
    public String filtrarLibros(@RequestParam(name = "genero", required = false) String genero, Model model) {
        List<Libro> librosFiltrados;
        if (genero == null || genero.trim().isEmpty()) {
            librosFiltrados = libroRepository.findAll();
        } else {
            String generoNormalizado = genero.trim().toLowerCase();
            librosFiltrados = libroRepository.findAll().stream()
                    .filter(libro -> libro.getGenero().toLowerCase().contains(generoNormalizado))
                    .toList();
        }
        model.addAttribute("libros", librosFiltrados);
        return "listaLibros"; // Mostrar los libros filtrados
    }

    // Página para añadir una nueva reseña
    @GetMapping("/nuevaResena")
    public String formularioResena(@RequestParam("libroId") Long libroId, Model model) {
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        model.addAttribute("libro", libro);
        model.addAttribute("resena", new Resenas()); // Crear una reseña vacía
        return "formularioResena"; // Vista para añadir una nueva reseña
    }

    // Guardar la nueva reseña
    @PostMapping("/guardarResena")
    public String guardarResena(@ModelAttribute Resenas resena, @RequestParam("libroId") Long libroId,
                                 RedirectAttributes attributes) {
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        Usuarios usuario = usuarioRepository.findById(1L) // Usar el usuario con ID 1 por ahora
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        resena.setLibro(libro);
        resena.setUsuario(usuario);
        resena.setFecha(LocalDateTime.now());

        resenasRepository.save(resena); // Guardar la reseña en la base de datos

        attributes.addAttribute("mensaje", "Reseña guardada exitosamente");
        return "redirect:/resenas?libroId=" + libroId; // Redirigir a la lista de reseñas del libro
    }

    // Página para mostrar todas las reseñas
    @GetMapping("/reseñas")
    public String verTodasLasResenas(Model model) {
        List<Resenas> resenas = resenasRepository.findAll(); // Obtener todas las reseñas
        model.addAttribute("resenas", resenas);
        return "todasResenas"; // Vista que muestra todas las reseñas
    }
}
