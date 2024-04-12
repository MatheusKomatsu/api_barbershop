package br.unesp.barbershop.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import br.unesp.barbershop.model.Agendamento;
import br.unesp.barbershop.repository.AgendamentoRepository;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    // BUSCANDO TODOS OS AgendamentoS
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Agendamento>> Agendamento(){
        List<Agendamento> agendamentos_list = (List<Agendamento>) agendamentoRepository.findAll();

        return new ResponseEntity<List<Agendamento>>(agendamentos_list, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Agendamento> cadastrar(@RequestBody Agendamento Agendamento){
        Agendamento novo_agendamento = agendamentoRepository.save(Agendamento);

        return new ResponseEntity<>(novo_agendamento, HttpStatus.OK);
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Agendamento> atualizar(@RequestBody Agendamento Agendamento){
        Agendamento agendamento_atualizado = agendamentoRepository.save(Agendamento);

        return new ResponseEntity<>(agendamento_atualizado, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public String deletar(@PathVariable("id") Long id){
        agendamentoRepository.deleteById(id);

        return "Agendamento deletado";
    }
}
