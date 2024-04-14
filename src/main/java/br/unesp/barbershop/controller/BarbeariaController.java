package br.unesp.barbershop.controller;

import java.util.List;
import java.util.Optional;

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

import br.unesp.barbershop.dto.BarbeariaDTO;
import br.unesp.barbershop.model.Barbearia;
import br.unesp.barbershop.model.Usuario;
import br.unesp.barbershop.repository.BarbeariaRepository;
import br.unesp.barbershop.repository.UsuarioRepository;

@RestController
@RequestMapping("/barbearia")
public class BarbeariaController {
    @Autowired
    private BarbeariaRepository barbeariaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // BUSCANDO TODOS as barbearias
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Barbearia>> Barbearia(){
        List<Barbearia> barbearias_list = (List<Barbearia>) barbeariaRepository.findAll();

        return new ResponseEntity<List<Barbearia>>(barbearias_list, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Barbearia> cadastrar(@RequestBody BarbeariaDTO barbeariadto){
        Usuario usuario_dono = usuarioRepository.findById(barbeariadto.getUsuario_id()).get();

        if(usuario_dono == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public ResponseEntity<Barbearia> atualizar(@RequestBody Barbearia barbearia){
        Barbearia barbearia_atualizada = barbeariaRepository.findById(barbearia.getId()).get();

        if(barbearia_atualizada == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        barbearia_atualizada = barbeariaRepository.save(barbearia);

        return new ResponseEntity<>(barbearia_atualizada, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public String deletar(@PathVariable("id") Long id){
        barbeariaRepository.deleteById(id);

        return "Barbearia deletada";
    }
}
