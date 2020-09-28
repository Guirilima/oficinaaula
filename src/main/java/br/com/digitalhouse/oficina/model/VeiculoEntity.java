package br.com.digitalhouse.oficina.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VEICULOS")
public class VeiculoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_VEICULO_VEI")
	private BigInteger idVeiculo;
	
	@Column(name = "VEI_PLACA_VEI",length = 7, nullable = false)
	private String placaVeiculo;
	
	@Column(name = "VEI_COR_VEI",length = 30, nullable = false)
	private String corVeiculo;

	@Column(name = "VEI_MODELO_VEI",length = 30, nullable = false)
	private String modeloVeiculo;

	@Column(name = "VEI_MARCA_VEI",length = 30, nullable = false)
	private String marcaVeiculo;

	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE_VEI")
	private ClienteEntity cliente;


}

//	@Column(name = "ID_CLIENTE_VEI", nullable = false)
//	private BigInteger idClienteVeiculo;
