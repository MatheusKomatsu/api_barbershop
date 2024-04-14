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

import br.unesp.barbershop.repository.BarbeariaRepository;
import br.unesp.barbershop.repository.ServicoRepository;
import br.unesp.barbershop.dto.ServicoDTO;
import br.unesp.barbershop.model.Barbearia;
import br.unesp.barbershop.model.Servico;

@RestController
@RequestMapping("/servico")
public class ServicoController {
    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;
    // BUSCANDO TODOS OS servicos
    @GetMapping(value = "/barbearia/{id}", produces = "application/json")
    public ResponseEntity<List<Servico>> Servico(@PathVariable("id") Long id){
        Barbearia barbearia = barbeariaRepository.findById(id).get();

        if(barbearia == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        
        return new ResponseEntity<List<Servico>>(barbearia.getServicos(), HttpStatus.OK);
    }


    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Servico> cadastrar(@RequestBody ServicoDTO servicodto){
        Barbearia barbearia_servico = barbeariaRepository.findById(servicodto.getBarbearia_id()).get();

        if(barbearia_servico == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        Servico novo_servico = new Servico(servicodto.getId(), servicodto.getNome(), servicodto.getPreco(), 
                                            servicodto.getTempoServicoMinutos(), servicodto.getDescricao(), barbearia_servico, null);
        
        
        
        servicoRepository.save(novo_servico);

        return new ResponseEntity<>(novo_servico, HttpStatus.OK);
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Servico> atualizar(@RequestBody Servico servico){
        Servico servico_atualizado = servicoRepository.findById(servico.getId()).get();

        if(servico_atualizado == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        servico_atualizado = servicoRepository.save(servico);

        return new ResponseEntity<>(servico_atualizado, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public String deletar(@PathVariable("id") Long id){
        servicoRepository.deleteById(id);

        return "Serviço deletado";
    }
}

