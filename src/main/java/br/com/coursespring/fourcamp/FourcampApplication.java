package br.com.coursespring.fourcamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.coursespring.fourcamp.model.Cliente;
import br.com.coursespring.fourcamp.repository.Clientes;

@SpringBootApplication
public class FourcampApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FourcampApplication.class, args);
	}

}
