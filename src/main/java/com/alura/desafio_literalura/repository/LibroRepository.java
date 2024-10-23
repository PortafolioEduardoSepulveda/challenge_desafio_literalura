package com.alura.desafio_literalura.repository;


import com.alura.desafio_literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma ")
    List<Libro> busquedaPorLenguaje(String idioma);
    @Query("SELECT l FROM Libro l WHERE UPPER(l.titulo) = UPPER(:nombreLibro) ")
    List<Libro> buscarLibroPorNombre(String nombreLibro);
    @Query("SELECT l FROM Libro l ORDER BY CAST(l.cantidad_descargas AS INTEGER) DESC LIMIT 10")
    List<Libro> busqueda10LibrosMasDescargados();
}
