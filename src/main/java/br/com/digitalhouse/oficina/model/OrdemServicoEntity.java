package br.com.digitalhouse.oficina.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDEM_SERIVCO")
public class OrdemServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_ORDEM_SR")
    private BigInteger idOrdemServico;

    //@Column(name = "ID_CLIENTE_SR")
    @OneToOne
    @JoinColumn(name = "ID_CLIENTE_SR")
    private ClienteEntity clienteEntity;

//    @Column(name = "ID_VEICULO_SR")
    @OneToOne
    @JoinColumn(name = "ID_VEICULO_SR")
    private VeiculoEntity veiculoEntity;

    @Column(name = "DS_ORDEM_SR")
    private String descricaoOrdemServico;

    @Column(name = "VL_ORDEM_SR")
    private BigInteger valorOrdemServico;
}


