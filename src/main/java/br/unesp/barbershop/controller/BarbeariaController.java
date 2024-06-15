package br.unesp.barbershop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unesp.barbershop.dto.BarbeariaDTO;
import br.unesp.barbershop.dto.BarbeariaUpdateDTO;
import br.unesp.barbershop.model.Agendamento;
import br.unesp.barbershop.model.Barbearia;
import br.unesp.barbershop.model.Servico;
import br.unesp.barbershop.model.Usuario;
import br.unesp.barbershop.repository.AgendamentoRepository;
import br.unesp.barbershop.repository.BarbeariaRepository;
import br.unesp.barbershop.repository.ServicoRepository;
import br.unesp.barbershop.repository.UsuarioRepository;

@RestController
@RequestMapping("/barbearia")
public class BarbeariaController {
    @Autowired
    private BarbeariaRepository barbeariaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    // BUSCANDO TODOS as barbearias
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Barbearia>> listarBarbearias(){
        List<Barbearia> barbearias_list = (List<Barbearia>) barbeariaRepository.findAll();


        return new ResponseEntity<List<Barbearia>>(barbearias_list, HttpStatus.OK);
    }

    // Busca barbearia específica
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Barbearia> visualizarBarbearia(@PathVariable("id") Long id){
        Barbearia barbearia = barbeariaRepository.findById(id).isPresent()
        ?barbeariaRepository.findById(id).get():null;

        if (barbearia == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Barbearia>(barbearia, HttpStatus.OK);
    }
    
    // Criando Barbearia
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Barbearia> cadastrar(@RequestBody BarbeariaDTO barbeariadto){
        Usuario usuario_dono = usuarioRepository.findById(barbeariadto.getUsuario_id()).orElseGet(null);;

        if(usuario_dono == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Barbearia barbearia = new Barbearia();
        barbearia.setId(barbeariadto.getId());
        barbearia.setNomeBarbearia(barbeariadto.getNomeBarbearia());
        barbearia.setEndereco(barbeariadto.getEndereco());
        barbearia.setUsuario(usuario_dono);
        
        Barbearia nova_barbearia = barbeariaRepository.save(barbearia);
        
        return new ResponseEntity<>(nova_barbearia, HttpStatus.OK);
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Barbearia> atualizar(@RequestBody BarbeariaUpdateDTO barbeariadto){
    // Busca a Barbearia pelo ID
        Barbearia barbeariaExistente = barbeariaRepository.findById(barbeariadto.getId()).orElse(null);

        // Verifica se a Barbearia existe
        if (barbeariaExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Atualiza apenas os campos necessários
        barbeariaExistente.setNomeBarbearia(barbeariadto.getNomeBarbearia());
        barbeariaExistente.setEndereco(barbeariadto.getEndereco());
        // Não é necessário setar o usuário dono novamente se não mudou

        // Salva a Barbearia atualizada
        Barbearia barbeariaAtualizada = barbeariaRepository.save(barbeariaExistente);

        return new ResponseEntity<>(barbeariaAtualizada, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public String deletar(@PathVariable("id") Long id){
        Barbearia verifica_barbearia = barbeariaRepository.findById(id).isPresent() ? barbeariaRepository.findById(id).get():null;

        if(verifica_barbearia == null){
            return "Barbearia não encontrada";
        }

        barbeariaRepository.deleteById(id);

        return "Barbearia deletada";
    }

    // BUSCANDO todos os servicos de uma barbearia
    @GetMapping(value = "/{id}/servicos", produces = "application/json")
    public ResponseEntity<List<Servico>> listarServicosPorBarbearia(@PathVariable("id") Long id) {
        // Verifica se a barbearia existe
        if (!barbeariaRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Converte Iterable para Stream
        List<Servico> servicos = StreamSupport.stream(servicoRepository.findAll().spliterator(), false)
                .filter(servico -> servico.getBarbearia().getId().equals(id))
                .collect(Collectors.toList());

        return new ResponseEntity<>(servicos, HttpStatus.OK);
    }


}
