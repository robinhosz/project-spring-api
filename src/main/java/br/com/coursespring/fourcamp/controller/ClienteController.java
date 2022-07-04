package br.com.coursespring.fourcamp.controller;

import br.com.coursespring.fourcamp.model.Cliente;
import br.com.coursespring.fourcamp.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	Clientes clienteRepository;

	@GetMapping("{id}")
	public Cliente getClienteById(@PathVariable Integer id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente Não encontrado"));

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		clienteRepository.findById(id)
		.map(cliente -> {
			clienteRepository.delete(cliente); 
				return cliente;
		})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente Não encontrado"));

	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
		clienteRepository.findById(id).map(clienteExistente -> {
			cliente.setId(clienteExistente.getId());
			clienteRepository.save(cliente);
			return ResponseEntity.noContent().build();
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente Não encontrado"));
	}

	@GetMapping
	public List<Cliente> find(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		return clienteRepository.findAll(example);

	}
}
