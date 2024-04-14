package br.unesp.barbershop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.unesp.barbershop.model.Agendamento;

@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {
    
}
