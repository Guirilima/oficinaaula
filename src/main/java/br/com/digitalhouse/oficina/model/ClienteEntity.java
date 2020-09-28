package br.com.digitalhouse.oficina.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENTES")
public class ClienteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_CLIENTE_CL")
	private BigInteger idCliente;
	
	@Column(name = "NM_NOME_CL",length = 60, nullable = false)
	private String nomeCliente;

	@OneToMany(mappedBy = "cliente")
	private Set<VeiculoEntity> veiculos = new HashSet<VeiculoEntity>();
	
}

//	@OneToMany(mappedBy = "cliente")
//	@JoinColumn(name="ID_CLIENTE_VEI")
//	@Column(name = "ID_CLIENTE_VEI")
//	private BigInteger veiculos ;
//@OneToMany(mappedBy = "ordemServico")
//private Set<OrdemServicoEntity> ordemServicoEntities = new HashSet<OrdemServicoEntity>();