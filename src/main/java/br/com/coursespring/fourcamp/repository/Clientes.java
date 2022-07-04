package br.com.coursespring.fourcamp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.coursespring.fourcamp.model.Cliente;


public interface Clientes extends JpaRepository<Cliente, Integer>{

	List<Cliente> findByNomeLike(String nome);
	
	List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);
	
	Cliente findOneByNome(String nome);
	
	boolean existsByNome(String nome);
	
	@Query("select c from Cliente c left join fetch c.pedidos where c.id = :id")
	Cliente findClienteFetchPedidos(@Param("id") Integer id);
	

}
