/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.gm.raphael.OSApiApplication.api.controller;

import br.gm.raphael.OSApiApplication.domain.model.Cliente;
import br.gm.raphael.OSApiApplication.domain.repository.ClienteRepository;
import br.gm.raphael.OSApiApplication.domain.service.ClienteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */
@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        //return clienteRepository.findAll();  
        //return clienteRepository.findByNome("KGe");

        //O tipo Optional indica que pode ou não estar nulo
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    
    
    
    //Por meio desse comando do @PostMapping eu consigo acrescentar itens na base de dados
    //--------------------POST
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }
    
    //Por meio desse comando do @PutMapping eu consigo editar itens já existentes na base de dados
    //--------------------PUT
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {

        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(id);
        cliente = clienteService.salvar(cliente); 
        return ResponseEntity.ok(cliente);
    }
    
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        
        if(!clienteRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
        

}
