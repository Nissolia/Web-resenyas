package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "libros") 
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titulo;
    private String autor;
    private String genero;
    private String sinopsis;

    // Constructor vacío
    public Libro() {
    }

    // Constructor con todos los campos
    public Libro(String titulo, String autor, String genero, String sinopsis) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.sinopsis = sinopsis;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    // Método toString para representar el objeto como cadena
    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", Género: " + genero + ", Sinopsis: " + sinopsis + '.';
    }
}
