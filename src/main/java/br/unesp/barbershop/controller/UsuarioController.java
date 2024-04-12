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

import br.unesp.barbershop.model.Usuario;
import br.unesp.barbershop.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    // BUSCANDO TODOS OS USUARIOS
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Usuario>> usuario(){
        List<Usuario> usuarios_list = (List<Usuario>) usuarioRepository.findAll();

        return new ResponseEntity<List<Usuario>>(usuarios_list, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
        Usuario novo_usuario = usuarioRepository.save(usuario);

        return new ResponseEntity<>(novo_usuario, HttpStatus.OK);
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario){
        Usuario usuario_atualizado = usuarioRepository.save(usuario);

        return new ResponseEntity<>(usuario_atualizado, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public String deletar(@PathVariable("id") Long id){
        usuarioRepository.deleteById(id);

        return "Usuario deletado";
    }

    // pegando todos os agendamentos de determinado usuario
    // @GetMapping(value = "/agendamentos", produces = "application/json")
    // public ResponseEntity<List<Agendamento>> agendamento(){
    //     List<Agendamento> agendamento_usuario = (List<Agendamento>)agendamentoRepository.findAll();

    //     return new ResponseEntity<List<Agendamento>>(agendamento_usuario, HttpStatus.OK);
    // }
}