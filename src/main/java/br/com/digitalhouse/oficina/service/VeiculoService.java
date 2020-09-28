package br.com.digitalhouse.oficina.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digitalhouse.oficina.model.VeiculoEntity;
import br.com.digitalhouse.oficina.repository.VeiculoRepository;

@Service
public class VeiculoService {
	
	private final VeiculoRepository veiculoRepository;
	
	@Autowired
	public VeiculoService(VeiculoRepository veiculoRepository) {
		this.veiculoRepository = veiculoRepository;
	}
	
	
	public VeiculoEntity create(VeiculoEntity veiculoEntity) {
		veiculoEntity.setIdVeiculo(null);
		return this.veiculoRepository.save(veiculoEntity);
	}
	
	public VeiculoEntity update(VeiculoEntity novo) {
	
		VeiculoEntity antigo = this.findById(novo.getIdVeiculo());
		
		antigo.setCorVeiculo(novo.getCorVeiculo());
		antigo.setMarcaVeiculo(novo.getMarcaVeiculo());
		antigo.setModeloVeiculo(novo.getModeloVeiculo());
		antigo.setPlacaVeiculo(novo.getPlacaVeiculo());
		
		return this.veiculoRepository.save(antigo);
		
	}
	 
	
	public VeiculoEntity findById(BigInteger id) {
		Optional
			.ofNullable(id)
			.orElseThrow( () -> new RuntimeException("O id não pode ser nulo"));  // todo: criar exceção personalizada para argumento ilegal
		
		return this.veiculoRepository.findById(id)
				.orElseThrow( () -> new RuntimeException("Não foi possivel encontrar um objeto com id " + id)); // todo: mudar pra object not found exception
	}
	
	public List<VeiculoEntity> findAll(){
		return this.veiculoRepository.findAll();
	}
	
	public void deleteById(BigInteger id) {
		this.findById(id);
		
		this.veiculoRepository.deleteById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
