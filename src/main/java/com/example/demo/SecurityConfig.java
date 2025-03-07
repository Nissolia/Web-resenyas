package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class SecurityConfig {

    @Autowired
    private UsuarioRepository usuariosRepository;  // Repositorio para interactuar con la tabla usuarios

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Consultar la base de datos por el nombre de usuario
            Usuarios usuario = usuariosRepository.findByNombre(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

            // Crear y devolver un objeto UserDetails con la información de la base de datos
            return User.builder()
                    .username(usuario.getNombre())
                    .password(usuario.getContrasena())  // La contraseña ya está encriptada en la base de datos
                    .authorities(usuario.getPerfil())   // Asumiendo que el perfil se usa como autoridad
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usamos BCrypt para encriptar las contraseñas
    }
}
