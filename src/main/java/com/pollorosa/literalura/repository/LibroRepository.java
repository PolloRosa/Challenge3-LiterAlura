package com.pollorosa.literalura.repository;

import com.pollorosa.literalura.model.Idioma;
import com.pollorosa.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    public List<Libro> listarPorIdioma(Idioma idioma);
}
