package br.com.coursespring.fourcamp.dto;

import java.math.BigDecimal;

import br.com.coursespring.fourcamp.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPedidoDTO {

	private Integer produto;
	private Integer quantidade;
}
