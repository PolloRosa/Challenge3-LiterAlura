package com.pollorosa.literalura.model;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private Long numeroDescargas;

    public Libro() {}

    public Libro(DatosLibro datos) {
        this.titulo = datos.titulo();
        this.autor = !datos.autores().isEmpty() ? new Autor(datos.autores().get(0)) : null;
        this.idioma = !datos.idiomas().isEmpty() ? Idioma.fromString(datos.idiomas().get(0)) : null;
        this.numeroDescargas = datos.numeroDescargas().longValue();
    }

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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Long getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Long numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
