package br.unesp.barbershop.dto;

import br.unesp.barbershop.model.UserRole;

public record RegisterDTO(String nome, String login, String password, UserRole role) {
    
}
