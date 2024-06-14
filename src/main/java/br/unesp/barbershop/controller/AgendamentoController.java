package br.unesp.barbershop.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestHeader;
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
import br.unesp.barbershop.security.TokenService;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private TokenService tokenService;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    
    // Buscando agendamento único
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Agendamento> visualizarAgendamento(@PathVariable(name= "id") Long id ){
        Agendamento agendamento = agendamentoRepository.findById(id).isPresent()?agendamentoRepository.findById(id).get():null;
        if (agendamento == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Agendamento>(agendamento, HttpStatus.OK);
    }   

    // Criando agendamentos
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Agendamento> cadastrar(@RequestBody AgendamentoDTO agendamentoDto, @RequestHeader("Authorization") String authorizationHeader){

        // vendo se barbearia realmente existe
        Barbearia barbearia_escolhida = barbeariaRepository.findById(agendamentoDto.getBarbearia_id()).isPresent()
        ?barbeariaRepository.findById(agendamentoDto.getBarbearia_id()).get():null;
        if(barbearia_escolhida == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Extraindo o token do cabeçalho
        String token = authorizationHeader.replace("Bearer ", "");
        
        Usuario usuario_atendido = new Usuario(); 
        try {
            String result = tokenService.validateToken(token);
            usuario_atendido =  usuarioRepository.findByLogin(result);
        } catch (com.auth0.jwt.exceptions.JWTDecodeException e) {
            System.out.println(e);
        }

        

        Long servico_id = agendamentoDto.getServico_id(); 
        
        Servico servico_aux = servicoRepository.findById(servico_id).isPresent() 
        ? servicoRepository.findById(servico_id).get() : null;
        
        Agendamento agendamento = new Agendamento();
        agendamento.setData(agendamentoDto.getData());
        agendamento.setUsuario(usuario_atendido);
        agendamento.setServico(servico_aux);
        agendamento.setBarbearia(barbearia_escolhida);


        System.out.println(agendamento);
        
        Agendamento novo_agendamento = agendamentoRepository.save(agendamento);

        return new ResponseEntity<Agendamento>(novo_agendamento, HttpStatus.OK);
    }

    // Buscando agendamento por barbearia
    @GetMapping(value = "/barbearia/{id}", produces = "application/json")
    public ResponseEntity<List<Agendamento>> visualizarAgendamentosPorBarbearia(@PathVariable(name= "id") Long id ){
        List<Agendamento> agendamentos = (List<Agendamento>) agendamentoRepository.findAll();
        List<Agendamento> agendamentosAtt = new ArrayList<>();
        for(Agendamento agendamento : agendamentos) {
            Integer barbeariaID = agendamento.getBarbearia().getId().intValue();
            Integer id_int = id.intValue();

            if(barbeariaID.equals(id_int)){
                agendamentosAtt.add(agendamento);
            }
        }

        return new ResponseEntity<List<Agendamento>>(agendamentos, HttpStatus.OK);
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
        Long servico_id = agendamentoDTO.getServico_id(); 

        Servico servico_aux = servicoRepository.findById(servico_id).isPresent() 
        ? servicoRepository.findById(servico_id).get() : null;
        
        if(servico_aux == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(!servicos.contains(servico_aux)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        Agendamento agendamento = new Agendamento();
        agendamento.setData(agendamentoDTO.getData());
        agendamento.setUsuario(usuario_atendido);
        agendamento.setServico(servico_aux);
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
