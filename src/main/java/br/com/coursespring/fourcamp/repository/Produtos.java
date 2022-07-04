package br.com.coursespring.fourcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coursespring.fourcamp.model.Produto;

public interface Produtos extends JpaRepository<Produto, Integer>{

}
