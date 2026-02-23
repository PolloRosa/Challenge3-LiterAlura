package com.pollorosa.literalura.principal;

import com.pollorosa.literalura.config.Constantes;
import com.pollorosa.literalura.model.DatosResultado;
import com.pollorosa.literalura.service.ConsultaDatos;

import java.util.Scanner;

public class Principal {
    ConsultaDatos consultaDatos = new ConsultaDatos();
    Scanner teclado = new Scanner(System.in);

    public void iniciar() {
        String url = Constantes.URL_API;

        System.out.println("Ingresar el titulo del libro:");
        String titulo = teclado.nextLine();

        System.out.println("Descargando la información...");
        url += titulo.replace(" ", "%20");
        DatosResultado datos = consultaDatos.cargarDatos(url, DatosResultado.class);
        System.out.println(datos);
    }
}
