package br.com.digitalhouse.oficina.resource;

import br.com.digitalhouse.oficina.model.ClienteEntity;
import br.com.digitalhouse.oficina.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

    @Autowired
    ClienteService clienteService;

    @ApiOperation(value = "API que é responsavel por criar um novo Usuário juntamente com seus Veiculos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorno de dados do cliente."),
            @ApiResponse(code = 400, message = "Erro na criação do cliente.")
    })
    @PostMapping //localhost:8080/cliente =: POST
    public ResponseEntity<Void> create(@RequestBody ClienteEntity clienteEntity){

        clienteEntity = this.clienteService.create(clienteEntity);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clienteEntity.getIdCliente())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "API que editar um cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso no update."),
            @ApiResponse(code = 400, message = "Erro no update do cliente.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable BigInteger id, @RequestBody ClienteEntity clienteEntity){
        clienteEntity.setIdCliente(id);

        this.clienteService.update(clienteEntity);

        return ResponseEntity.noContent().build();

    }

    @ApiOperation(value = "API que recupera dados do cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno de dados do cliente."),
            @ApiResponse(code = 400, message = "Erro na recuperacao do cliente")
    })
    @GetMapping("/{id}")  // /cliente/3
    public ResponseEntity<ClienteEntity> findById(@PathVariable BigInteger id){

        ClienteEntity clienteEntity = this.clienteService.findById(id);

        return ResponseEntity.ok(clienteEntity);
    }

    @ApiOperation(value = "API que recupera todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno de todos os clientes"),
            @ApiResponse(code = 400, message = "Erro na recuperacao dos clientes")
    })
    @GetMapping // /cliente
    public ResponseEntity<List<ClienteEntity>> findAll(){

        List<ClienteEntity> clientes = this.clienteService.findAll();

        return ResponseEntity.ok(clientes);

    }

    @ApiOperation(value = "API que deleta um cliente e seus veiculos pelo IdCliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso na exclusão"),
            @ApiResponse(code = 400, message = "Erro na exclusão.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable BigInteger id){
        this.clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
