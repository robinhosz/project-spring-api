package br.com.coursespring.fourcamp.controller;

import br.com.coursespring.fourcamp.model.Produto;
import br.com.coursespring.fourcamp.repository.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	Produtos produtoRepository;

	@GetMapping
	public List<Produto> findAll(Produto produto) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(produto, matcher);
		return produtoRepository.findAll(example);
	}
	
	@GetMapping("{id}")
	public Produto findById(@PathVariable Integer id) {
		return produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto Não encontrado"));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto save(@RequestBody @Valid Produto produto) {
		return produtoRepository.save(produto);
		
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
	
		produtoRepository.findById(id).map(produto -> {
			produtoRepository.delete(produto);
			return Void.TYPE;
	})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto Não encontrado"));

}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@PathVariable Integer id, @RequestBody @Valid Produto produto) {
		produtoRepository.findById(id).map(clienteExistente -> {
			produto.setId(clienteExistente.getId());
			produtoRepository.save(produto);
			return produto;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente Não encontrado"));
	}
	
}
