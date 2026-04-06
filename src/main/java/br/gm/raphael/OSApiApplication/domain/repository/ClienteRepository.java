/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.gm.raphael.OSApiApplication.domain.repository;

import br.gm.raphael.OSApiApplication.domain.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 *
 * @author digma
 */

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    
    List<Cliente> findByNome(String nome);
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    
    //COMO O EMAIL "TEORICAMENTE" NÃO SE REPETE, NÃO USO List<Cliente>
    //OU SEJA, RESULTADO ÚNICO
    Cliente findByEmail(String email);
    
}
