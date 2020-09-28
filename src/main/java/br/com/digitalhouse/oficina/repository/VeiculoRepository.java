package br.com.digitalhouse.oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.digitalhouse.oficina.model.VeiculoEntity;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoEntity, BigInteger> {

    @Query("from VeiculoEntity v WHERE v.cliente.idCliente = :idCliente")
    public List<VeiculoEntity> listaVeiculosPorIdCliente(@Param("idCliente") BigInteger idCliente);

}
