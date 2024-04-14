package br.unesp.barbershop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.unesp.barbershop.model.Barbearia;

@Repository
public interface BarbeariaRepository extends CrudRepository<Barbearia, Long>{
    
}
