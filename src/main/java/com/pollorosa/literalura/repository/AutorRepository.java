package com.pollorosa.literalura.repository;

import com.pollorosa.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento <= :anio AND :anio <= a.anioFallecimiento")
    List<Autor> listarVivosPorAnio(Integer anio);
}
