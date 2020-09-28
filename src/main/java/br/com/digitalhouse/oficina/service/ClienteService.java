package br.com.digitalhouse.oficina.service;

import br.com.digitalhouse.oficina.exception.ObjectNotFound;
import br.com.digitalhouse.oficina.exception.ObjectNull;
import br.com.digitalhouse.oficina.model.ClienteEntity;
import br.com.digitalhouse.oficina.model.VeiculoEntity;
import br.com.digitalhouse.oficina.repository.ClienteRepository;
import br.com.digitalhouse.oficina.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    VeiculoRepository veiculoRepository;

    public ClienteEntity create(ClienteEntity clienteEntity) {
        //Set<VeiculoEntity> listaVeiculoEntities = new HashSet<VeiculoEntity>();

        boolean clienteId = Optional.ofNullable(clienteEntity.getIdCliente()).isPresent();

        if (clienteId) return null;

        boolean clienteNome = Optional.ofNullable(clienteEntity.getNomeCliente()).isPresent();
        boolean clienteVeiculos = clienteEntity.getVeiculos().size() != 0;

        if ( (clienteNome && clienteVeiculos) && !clienteId ) {

            ClienteEntity newClienteEntity = new ClienteEntity().builder()
                    .idCliente(clienteEntity.getIdCliente() == null ? null : clienteEntity.getIdCliente())
                    .nomeCliente(clienteEntity.getNomeCliente())
                    //.veiculos(listaVeiculoEntities)
                    .build();

            clienteRepository.save(clienteEntity);

            for (VeiculoEntity veiculoEntity : clienteEntity.getVeiculos()) {
                VeiculoEntity newVeiculoEntity = new VeiculoEntity().builder()
                        .marcaVeiculo(veiculoEntity.getMarcaVeiculo())
                        .corVeiculo(veiculoEntity.getCorVeiculo())
                        .placaVeiculo(veiculoEntity.getPlacaVeiculo())
                        .modeloVeiculo(veiculoEntity.getModeloVeiculo())
                        .cliente(clienteEntity)
                        .build();

                veiculoRepository.save(newVeiculoEntity);
            }

        }

        return clienteEntity;
    }

    public void update(ClienteEntity clienteEntity) {

        boolean ss = Optional.ofNullable(clienteEntity.getIdCliente()).isPresent();

        //Fazer validações para o update
        //Checar todos campos obrigatorios

    }

    public ClienteEntity findById(BigInteger idCliente) {
        Optional
                .ofNullable(idCliente)
                .orElseThrow( () -> new ObjectNull("O id não pode ser nulo"));

        return this.clienteRepository.findById(idCliente)
                .orElseThrow( () -> new ObjectNotFound("Não foi possivel encontrar um objeto com id " + idCliente));
    }

    public List<ClienteEntity> findAll() {
        return this.clienteRepository.findAll();
    }

    public void deleteById(BigInteger idCliente) {

        if (idCliente != null) {

            List<VeiculoEntity> listaDeVeiculos =  veiculoRepository.listaVeiculosPorIdCliente(idCliente);

            Optional<ClienteEntity> clienteEntity= clienteRepository.findById(idCliente);

            if (!listaDeVeiculos.isEmpty() || clienteEntity.isPresent()){
                for (VeiculoEntity veiculo : listaDeVeiculos) {
                    veiculoRepository.deleteById(veiculo.getIdVeiculo());
                }
                clienteRepository.deleteById(idCliente);
            }
        }else {
            //Exception
        }

    }
}
