package br.com.digitalhouse.oficina.resource;

import br.com.digitalhouse.oficina.model.ClienteEntity;
import br.com.digitalhouse.oficina.model.OrdemServicoEntity;
import br.com.digitalhouse.oficina.service.ClienteService;
import br.com.digitalhouse.oficina.service.OrdemServicoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ordemServico")
public class OrdemServicoResource {

    @Autowired
    OrdemServicoService ordemServicoService;

    @ApiOperation(value = "API que é responsavel por criar uma ordem de serviço")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorno a ordem de serviço"),
            @ApiResponse(code = 400, message = "Erro na criação do serviço.")
    })
    @PostMapping //localhost:8080/ordemServico =: POST
    public ResponseEntity<Void> create(@RequestBody OrdemServicoEntity ordemServicoEntity,
                                       @Param("idCliente") BigInteger idCliente,
                                       @Param("idVeiculo") BigInteger idVeiculo){

        ordemServicoEntity = this.ordemServicoService.create(ordemServicoEntity);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ordemServicoEntity.getIdOrdemServico())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "API que editar uma ordem de Serviço por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso no update."),
            @ApiResponse(code = 400, message = "Erro no update do serviço.")
    })

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable BigInteger idOrdemDeServico, @RequestBody OrdemServicoEntity ordemServicoEntity){
        ordemServicoEntity.setIdOrdemServico(idOrdemDeServico);

        this.ordemServicoService.update(ordemServicoEntity);

        return ResponseEntity.noContent().build();

    }

    @ApiOperation(value = "API que recupera dados da ordem de serviço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno do serviço."),
            @ApiResponse(code = 400, message = "Erro na recuperacao do serviço")
    })
    @GetMapping("/{id}")  // /cliente/3
    public ResponseEntity<OrdemServicoEntity> findById(@PathVariable BigInteger idOrdemDeServico){

        OrdemServicoEntity ordemServicoEntity = this.ordemServicoService.findById(idOrdemDeServico);

        return ResponseEntity.ok(ordemServicoEntity);
    }

    @ApiOperation(value = "API que recupera todos os serviços")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno de todos os serviços"),
            @ApiResponse(code = 400, message = "Erro na recuperacao dos serviços")
    })
    @GetMapping
    public ResponseEntity<List<OrdemServicoEntity>> findAll(){

        List<OrdemServicoEntity> ordemDeServiços = this.ordemServicoService.findAll();

        return ResponseEntity.ok(ordemDeServiços);

    }

    @ApiOperation(value = "API que deleta um serviço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso na exclusão"),
            @ApiResponse(code = 400, message = "Erro na exclusão.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable BigInteger idOrdemDeServico){
        this.ordemServicoService.deleteById(idOrdemDeServico);
        return ResponseEntity.noContent().build();
    }
}
