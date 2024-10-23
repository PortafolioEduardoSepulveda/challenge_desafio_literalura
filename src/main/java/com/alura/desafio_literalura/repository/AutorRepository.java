package com.alura.desafio_literalura.repository;

import com.alura.desafio_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    @Query("SELECT a FROM Autor a WHERE a.nombre ILIKE %:nombreAutor%")
    List<Autor> buscarPorNombre(String nombreAutor);

    @Query("SELECT a FROM Autor a WHERE CAST(a.fecha_nacimiento AS INTEGER) <= CAST(:fecha  AS INTEGER) and CAST(a.fecha_fallecimiento  AS INTEGER) > CAST(:fecha  AS INTEGER)")
    List<Autor> buscarPorUnDerminadoAnio(String fecha);

    @Query("SELECT a FROM Autor a WHERE  CAST(a.fecha_fallecimiento  AS INTEGER) >= CAST(:fecha  AS INTEGER)")
    List<Autor> buscarPorFechadeFallecimiento(String fecha);
}
