package br.com.coursespring.fourcamp.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder 
public class InformacaoItemPedidoDTO {
	
	private String descricaoProduto;
	private BigDecimal precoUnitario;
	private Integer quantidade;

}
