package com.pollorosa.literalura.principal;

import com.pollorosa.literalura.config.Constantes;
import com.pollorosa.literalura.model.*;
import com.pollorosa.literalura.repository.AutorRepository;
import com.pollorosa.literalura.repository.LibroRepository;
import com.pollorosa.literalura.service.ConsultaDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private ConsultaDatos consultaDatos = new ConsultaDatos();
    private Scanner teclado = new Scanner(System.in);
    private LibroRepository repoLibro;
    private AutorRepository repoAutor;

    public Principal(LibroRepository repoLibro, AutorRepository repoAutor) {
        this.repoLibro = repoLibro;
        this.repoAutor = repoAutor;
    }

    public void iniciar() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    -----------------
                    Ingresar una opción:
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch(opcion) {
                case 1: 
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        DatosLibro datos = obtenerDatosLibro();
        if(datos != null) {
            Libro nuevoLibro = new Libro(datos);
            Autor nuevoAutor = nuevoLibro.getAutor();
            if(nuevoAutor == null) {
                System.out.println("No se puede registrar el libro porque no tiene autor.");
                return;
            }
            Autor registrado = validarAutorRegistrado(nuevoAutor);
            if(registrado != null) {
                nuevoAutor.setId(registrado.getId());
            }
            nuevoAutor.agregarLibro(nuevoLibro);
            try {
                repoAutor.save(nuevoAutor);
                imprimirLibro(nuevoLibro);
            } catch (DataIntegrityViolationException e) {
                System.out.println("No se puede registrar el mismo libro más de una vez.");
            }
        }
    }

    private Autor validarAutorRegistrado(Autor autor) {
        List<Autor> resultado = repoAutor.findByNombreIgnoreCase(autor.getNombre());
        // si hay resultado es porque el autor ya está registrado
        if(!resultado.isEmpty()) {
            // se devuelve el autor encontrado
            return resultado.get(0);
        }
        return null;
    }

    private DatosLibro obtenerDatosLibro() {
        System.out.println("Ingresar el nombre del libro en consulta:");
        String titulo = teclado.nextLine();
        DatosResultado datos = consultaDatos.cargarDatos(
                Constantes.URL_API + titulo.replace(" ", "%20"),
                DatosResultado.class);
        if(!datos.libros().isEmpty()) {
            return datos.libros().get(0);
        }
        System.out.println("Libro no encontrado");
        return null;
    }

    private void imprimirLibro(Libro libro) {
        String plantilla = """
                    ----- LIBRO -----
                    Título: %s
                    Autor: %s
                    Idioma: %s
                    Número de descargas: %d
                    -----------------
                    """;
        System.out.printf(plantilla, libro.getTitulo(), libro.getAutor()!=null ? libro.getAutor().getNombre() : "", libro.getIdioma(), libro.getNumeroDescargas());
        System.out.println();
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = repoLibro.findAll();
        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(this::imprimirLibro);
    }
}