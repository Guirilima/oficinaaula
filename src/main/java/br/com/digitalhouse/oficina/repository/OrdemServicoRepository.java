package br.com.digitalhouse.oficina.repository;

import br.com.digitalhouse.oficina.model.OrdemServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface OrdemServicoRepository extends JpaRepository<OrdemServicoEntity,BigInteger> {

}
