package br.com.digitalhouse.oficina.resource;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.digitalhouse.oficina.model.VeiculoEntity;
import br.com.digitalhouse.oficina.service.VeiculoService;

@RestController
@RequestMapping("/veiculos") //localhost:8080/veiculos
public class VeiculoResource {
	
	private final VeiculoService veiculoService;

	@Autowired
	public VeiculoResource(VeiculoService veiculoService) {
		this.veiculoService = veiculoService;
	}
	
	
	@PostMapping
	public ResponseEntity<Void> create( @RequestBody VeiculoEntity veiculoEntity){
		
		veiculoEntity = this.veiculoService.create(veiculoEntity);
		
		URI uri = ServletUriComponentsBuilder
				 .fromCurrentRequest()
				 .path("/{id}")
				 .buildAndExpand(veiculoEntity.getIdVeiculo())
				 .toUri();
		
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable BigInteger id, @RequestBody VeiculoEntity veiculoEntity){
		veiculoEntity.setIdVeiculo(id);
		
		this.veiculoService.update(veiculoEntity);
		
		return ResponseEntity.noContent().build();
		
	}
	
	
	@GetMapping("/{id}")  // /veiculos/3
	public ResponseEntity<VeiculoEntity> findById(@PathVariable BigInteger id){
		
		VeiculoEntity veiculoEntity = this.veiculoService.findById(id);
		
		return ResponseEntity.ok(veiculoEntity);
	}
	
	@GetMapping // /veiculos
	public ResponseEntity<List<VeiculoEntity>> findAll(){
		
		List<VeiculoEntity> veiculoEntities = this.veiculoService.findAll();
		
		return ResponseEntity.ok(veiculoEntities);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable BigInteger id){
		this.veiculoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
