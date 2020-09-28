package br.com.digitalhouse.oficina.service;

import br.com.digitalhouse.oficina.exception.ObjectNotFound;
import br.com.digitalhouse.oficina.exception.ObjectNull;
import br.com.digitalhouse.oficina.model.ClienteEntity;
import br.com.digitalhouse.oficina.model.OrdemServicoEntity;
import br.com.digitalhouse.oficina.model.VeiculoEntity;
import br.com.digitalhouse.oficina.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService {

    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    VeiculoService veiculoService;

    public OrdemServicoEntity create(OrdemServicoEntity ordemServicoEntity) throws ObjectNotFound, ObjectNull {

        ClienteEntity entidadeCliente = clienteService.findById(ordemServicoEntity.getClienteEntity().getIdCliente());

        VeiculoEntity entidadeVeiculos = veiculoService.findById(ordemServicoEntity.getVeiculoEntity().getIdVeiculo());

        if (entidadeCliente != null && entidadeVeiculos != null) {

            try {
                OrdemServicoEntity newServico = new OrdemServicoEntity().builder()
                        .clienteEntity(entidadeCliente)
                        .veiculoEntity(entidadeVeiculos)
                        .descricaoOrdemServico(ordemServicoEntity.getDescricaoOrdemServico())
                        .valorOrdemServico(ordemServicoEntity.getValorOrdemServico())
                        .build();

                ordemServicoRepository.save(newServico);

                return newServico;

            }catch (Exception e) {
                throw new ObjectNull("Objeto nulo encontrado");
            }

        }else {
            throw new ObjectNotFound("IdCliente ou IdVeiculo não encontrado");
        }
    }

    public void update(OrdemServicoEntity ordemServicoEntity) {

        ordemServicoRepository.save(ordemServicoEntity);
        //Fazer validação para o update
    }

    public OrdemServicoEntity findById(BigInteger idOrdemDeServico) {
        Optional
                .ofNullable(idOrdemDeServico)
                .orElseThrow( () -> new ObjectNull("O id não pode ser nulo"));

        return this.ordemServicoRepository.findById(idOrdemDeServico)
                .orElseThrow( () -> new ObjectNotFound("Não foi possivel encontrar um objeto com id " + idOrdemDeServico));
    }

    public List<OrdemServicoEntity> findAll() {
        return this.ordemServicoRepository.findAll();
    }

    public void deleteById(BigInteger idOrdemDeServico) {

        OrdemServicoEntity deleteOrdemServico = this.findById(idOrdemDeServico);

        ordemServicoRepository.deleteById(idOrdemDeServico);
    }
}
