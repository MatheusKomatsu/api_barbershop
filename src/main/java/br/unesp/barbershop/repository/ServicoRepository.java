package br.unesp.barbershop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.unesp.barbershop.model.Servico;

@Repository
public interface ServicoRepository extends CrudRepository<Servico, Long>{
    
}
