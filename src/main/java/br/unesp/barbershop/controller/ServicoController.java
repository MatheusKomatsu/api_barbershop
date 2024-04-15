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
    
    // Busca um serviço específico
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Servico> visualizarServico(@PathVariable("id") Long id){
        Servico servico = servicoRepository.findById(id).isPresent()? servicoRepository.findById(id).get(): null;

        if(servico == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Servico>(servico, HttpStatus.OK);
    }


    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Servico> cadastrar(@RequestBody ServicoDTO servicodto){
        Barbearia barbearia_servico = barbeariaRepository.findById(servicodto.getBarbearia_id()).isPresent()
        ? barbeariaRepository.findById(servicodto.getBarbearia_id()).get():null;

        if(barbearia_servico == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        Servico novo_servico = new Servico(servicodto.getId(), servicodto.getNome(), servicodto.getPreco(), 
                                            servicodto.getTempoServicoMinutos()
                                            , servicodto.getDescricao(), barbearia_servico, null);
        
        
        
        servicoRepository.save(novo_servico);

        return new ResponseEntity<>(novo_servico, HttpStatus.OK);
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Servico> atualizar(@RequestBody ServicoDTO servicoDTO){
        Barbearia barbearia_servico = barbeariaRepository.findById(servicoDTO.getBarbearia_id()).isPresent()
        ? barbeariaRepository.findById(servicoDTO.getBarbearia_id()).get():null;

        if(barbearia_servico == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        Servico servico_atualizado = new Servico(servicoDTO.getId(), servicoDTO.getNome(), servicoDTO.getPreco()
                                            ,servicoDTO.getTempoServicoMinutos()
                                            , servicoDTO.getDescricao(), barbearia_servico, null);
        
        
        
        servicoRepository.save(servico_atualizado);

        return new ResponseEntity<>(servico_atualizado, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public String deletar(@PathVariable("id") Long id){
        Servico verifica_servico = servicoRepository.findById(id).isPresent()? 
        servicoRepository.findById(id).get(): null;

        if(verifica_servico == null){
            return "Serviço não encontrado";
        }

        servicoRepository.deleteById(id);

        return "Serviço deletado";
    }
}

