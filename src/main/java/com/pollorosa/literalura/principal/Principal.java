package com.pollorosa.literalura.principal;

import com.pollorosa.literalura.config.Constantes;
import com.pollorosa.literalura.model.DatosAutor;
import com.pollorosa.literalura.model.DatosLibro;
import com.pollorosa.literalura.model.DatosResultado;
import com.pollorosa.literalura.service.ConsultaDatos;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    ConsultaDatos consultaDatos = new ConsultaDatos();
    Scanner teclado = new Scanner(System.in);

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
            String plantilla = """
                    ----- LIBRO -----
                    Título: %s
                    Autor: %s
                    Idioma: %s
                    Número de descargas: %d
                    -----------------
                    """;
            String autores = datos.autores().stream()
                    .map(DatosAutor::nombre)
                    .collect(Collectors.joining(" - "));
            String idiomas = datos.idiomas().stream()
                    .map(Enum::toString)
                    .collect(Collectors.joining(", "));
            System.out.printf(plantilla, datos.titulo(), autores, idiomas, datos.numeroDescargas());
        }
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
}

/*   PARA TESTEAR
Beowulf: An Anglo-Saxon Epic Poem

sin autores

Twenty years after

2 autores
 */