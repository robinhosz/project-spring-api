package br.com.coursespring.fourcamp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.coursespring.fourcamp.model.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer>{

	@Query("select p from Pedido p left join fetch p.itens where p.id = :id")
	Optional<Pedido>findByIdFetchItens(@Param("id") Integer id);
	
}
