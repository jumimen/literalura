package com.example.literalura.repository;

import com.example.literalura.model.Autor;
import com.example.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("SELECT a FROM Libro a WHERE a.idioma = :idioma")
    List<Libro> findByIdioma(@Param("idioma")String idioma);
}
