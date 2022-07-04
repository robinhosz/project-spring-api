package br.com.coursespring.fourcamp.service;

import java.util.Optional;

import br.com.coursespring.fourcamp.dto.PedidoDTO;
import br.com.coursespring.fourcamp.model.Pedido;

public interface PedidoService {

	Pedido salvar(PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCompleto(Integer id);
}
