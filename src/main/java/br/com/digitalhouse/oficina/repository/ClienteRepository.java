package br.com.digitalhouse.oficina.repository;

import br.com.digitalhouse.oficina.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, BigInteger> {

}
