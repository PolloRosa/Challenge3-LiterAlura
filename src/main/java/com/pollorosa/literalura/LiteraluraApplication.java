package com.pollorosa.literalura;

import com.pollorosa.literalura.principal.Principal;
import com.pollorosa.literalura.repository.AutorRepository;
import com.pollorosa.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository repository;

	@Autowired
	private AutorRepository repository2;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal aplicacion = new Principal(repository, repository2);
		aplicacion.iniciar();
	}
}
