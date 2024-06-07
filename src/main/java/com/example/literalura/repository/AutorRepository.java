package com.example.literalura.repository;

import com.example.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Autor findByNombre(String nombre);
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :anio AND a.fechaFallecimiento >= :anio")
    List<Autor> findAllVivosEnAnio(@Param("anio")int anio);
}
