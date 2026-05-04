package br.gm.raphael.OSApiApplication.api.controller;

import br.gm.raphael.OSApiApplication.domain.model.OrdemServico;
import br.gm.raphael.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.gm.raphael.OSApiApplication.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */
@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }
    
    //PESQUISA GERAL
    @GetMapping
    public List<OrdemServico> buscar(){
        List<OrdemServico> ordemServico = ordemServicoRepository.findAll();
        
        return ordemServico;
    }

    //PESQUISA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscar(@PathVariable Long id) {

        Optional<OrdemServico> ordemServico = ordemServicoService.encontrarPorId(id);

        if (ordemServico.isPresent()) {
            return ResponseEntity.ok(ordemServico.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<OrdemServico> atualizar(@Valid @PathVariable Long id, @RequestBody OrdemServico ordemServico){
        
        if(!ordemServicoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        
        ordemServico.setId(id);
        ordemServico = ordemServicoService.atualizaOs(ordemServico);
        return ResponseEntity.ok(ordemServico);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        if(!ordemServicoRepository.existsById(id)){
            return ResponseEntity.notFound().build();        
        } else{
            ordemServicoService.excluir(id);
        return ResponseEntity.noContent().build();        
        }
    
    
    
    }
    
}
