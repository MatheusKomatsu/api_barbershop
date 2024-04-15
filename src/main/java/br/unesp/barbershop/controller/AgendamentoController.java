package br.unesp.barbershop.controller;

import java.util.ArrayList;
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

import br.unesp.barbershop.dto.AgendamentoDTO;
import br.unesp.barbershop.model.Agendamento;
import br.unesp.barbershop.model.Barbearia;
import br.unesp.barbershop.model.Servico;
import br.unesp.barbershop.model.Usuario;
import br.unesp.barbershop.repository.AgendamentoRepository;
import br.unesp.barbershop.repository.BarbeariaRepository;
import br.unesp.barbershop.repository.ServicoRepository;
import br.unesp.barbershop.repository.UsuarioRepository;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    // Buscando todos os agendamentos de uma barbearia
    @GetMapping(value = "/barbearia/{id}", produces = "application/json")
    public ResponseEntity<List<Agendamento>> listarAgendamentosBarbearia(@PathVariable(name= "id") Long id){
        Barbearia barbearia = barbeariaRepository.findById(id).isPresent()? barbeariaRepository.findById(id).get():null;
        if (barbearia == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<List<Agendamento>>(barbearia.getAgendamentos(), HttpStatus.OK);
    }
    // Buscando agendamento único
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Agendamento> visualizarAgendamento(@PathVariable(name= "id") Long id ){
        Agendamento agendamento = agendamentoRepository.findById(id).isPresent()?agendamentoRepository.findById(id).get():null;
        if (agendamento == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Agendamento>(agendamento, HttpStatus.OK);
    }    
        // Busca todos os agendamentos de um usuário
    @GetMapping(value = "/usuario/{id}", produces = "application/json")
        public ResponseEntity<List<Agendamento>> listarAgendamentosUsuario(@PathVariable("id") Long id){
            Usuario usuario =  usuarioRepository.findById(id).isPresent()? usuarioRepository.findById(id).get():null;
    
            if (usuario == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    
            return new ResponseEntity<List<Agendamento>>(usuario.getAgendamentos(), HttpStatus.OK);
        }

    // Criando agendamentos
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Agendamento> cadastrar(@RequestBody AgendamentoDTO agendamentoDto){

        Barbearia barbearia_escolhida = barbeariaRepository.findById(agendamentoDto.getBarbearia_id()).isPresent()
        ?barbeariaRepository.findById(agendamentoDto.getBarbearia_id()).get():null;
        if(barbearia_escolhida == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Usuario usuario_atendido = usuarioRepository.findById(agendamentoDto.getUsuario_id()).isPresent()
        ?usuarioRepository.findById(agendamentoDto.getUsuario_id()).get():null;
        if(usuario_atendido == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        List<Servico> servicos = barbearia_escolhida.getServicos();
        List<Servico> servicos_agendamento = new ArrayList<>();
        List<Long> servicos_id = agendamentoDto.getServicos_id(); 
        
        for (Long servico_id : servicos_id){

            Servico servico_aux = servicoRepository.findById(servico_id).isPresent() 
            ? servicoRepository.findById(servico_id).get() : null;
            
            if(servico_aux == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if(!servicos.contains(servico_aux)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
            servicos_agendamento.add(servico_aux);
        }


        Agendamento agendamento = new Agendamento();
        agendamento.setId(agendamentoDto.getId());
        agendamento.setData(agendamentoDto.getData());
        agendamento.setUsuario(usuario_atendido);
        agendamento.setServicos(servicos_agendamento);
        agendamento.setBarbearia(barbearia_escolhida);
        
        Agendamento novo_agendamento = agendamentoRepository.save(agendamento);

        return new ResponseEntity<>(novo_agendamento, HttpStatus.OK);
    }

    // Editando agendamentos
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Agendamento> atualizar(@RequestBody AgendamentoDTO agendamentoDTO){

        Barbearia barbearia_escolhida = barbeariaRepository.findById(agendamentoDTO.getBarbearia_id()).isPresent()
        ?barbeariaRepository.findById(agendamentoDTO.getBarbearia_id()).get():null;
        if(barbearia_escolhida == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Usuario usuario_atendido = usuarioRepository.findById(agendamentoDTO.getUsuario_id()).isPresent()
        ?usuarioRepository.findById(agendamentoDTO.getUsuario_id()).get():null;
        if(usuario_atendido == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        List<Servico> servicos = barbearia_escolhida.getServicos();
        List<Servico> servicos_agendamento = new ArrayList<>();
        List<Long> servicos_id = agendamentoDTO.getServicos_id(); 
        
        for (Long servico_id : servicos_id){

            Servico servico_aux = servicoRepository.findById(servico_id).isPresent() 
            ? servicoRepository.findById(servico_id).get() : null;
            
            if(servico_aux == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if(!servicos.contains(servico_aux)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
            servicos_agendamento.add(servico_aux);
        }


        Agendamento agendamento = new Agendamento();
        agendamento.setId(agendamentoDTO.getId());
        agendamento.setData(agendamentoDTO.getData());
        agendamento.setUsuario(usuario_atendido);
        agendamento.setServicos(servicos_agendamento);
        agendamento.setBarbearia(barbearia_escolhida);
        
        Agendamento agendamento_atualizado = agendamentoRepository.save(agendamento);

        return new ResponseEntity<>(agendamento_atualizado, HttpStatus.OK);
    }


    // Deletando agendamentos
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public String deletar(@PathVariable("id") Long id){
        Agendamento verifica_agendamento = agendamentoRepository.findById(id).isPresent() 
        ?agendamentoRepository.findById(id).get():null;
        if(verifica_agendamento == null){
            return "Agendamento não encontrado";
        }

        agendamentoRepository.deleteById(id);

        return "Agendamento deletado";
    }

    
}
