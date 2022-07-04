package br.com.coursespring.fourcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;


	@Column(name = "descricao")
	@NotEmpty(message = "Campo descrição é obrigatório")
	private String descricao;


	@Column(name = "preco_unitario")
	@NotNull(message = "Campo preço é obrigatório")
	private BigDecimal preco;
	

}


