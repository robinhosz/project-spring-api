package br.com.coursespring.fourcamp.dto;

import br.com.coursespring.fourcamp.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {

	@NotNull(message = "Informe o código do cliente")
	private Integer cliente;
	@NotNull(message = "Campo total do pedido é obrigatório")
	private BigDecimal total;

	@NotEmptyList(message = "Pedido não pode ser realizado sem itens.")
	private List<ItemPedidoDTO> items;
	
	
}
