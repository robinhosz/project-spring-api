package br.com.coursespring.fourcamp.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.coursespring.fourcamp.dto.ItemPedidoDTO;
import br.com.coursespring.fourcamp.dto.PedidoDTO;
import br.com.coursespring.fourcamp.exceptions.RegraNegocioException;
import br.com.coursespring.fourcamp.model.Cliente;
import br.com.coursespring.fourcamp.model.ItemPedido;
import br.com.coursespring.fourcamp.model.Pedido;
import br.com.coursespring.fourcamp.model.Produto;
import br.com.coursespring.fourcamp.repository.Clientes;
import br.com.coursespring.fourcamp.repository.ItemsPedido;
import br.com.coursespring.fourcamp.repository.Pedidos;
import br.com.coursespring.fourcamp.repository.Produtos;
import br.com.coursespring.fourcamp.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	Clientes clientesRepository;

	@Autowired
	Produtos produtosRepository;

	@Autowired
	Pedidos repository;

	@Autowired
	ItemsPedido itemsPedidoRepository;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clientesRepository.findById(idCliente)
				.orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);

		List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
		repository.save(pedido);
		itemsPedidoRepository.saveAll(itemsPedido);
		pedido.setItens(itemsPedido);
		return pedido;
	}

	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
		if (items.isEmpty()) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
		}

		return items.stream().map(dto -> {
			Integer idProduto = dto.getProduto();
			Produto produto = produtosRepository.findById(idProduto)
					.orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));

			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(dto.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			return itemPedido;
		}).collect(Collectors.toList());

	}

	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {

		return repository.findByIdFetchItens(id);
	}

}