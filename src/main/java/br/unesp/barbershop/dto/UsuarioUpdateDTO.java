package br.unesp.barbershop.dto;

public class UsuarioUpdateDTO {
    private Long id;
    private String nome;
    private String senha;
    private boolean trocouSenha;

    public UsuarioUpdateDTO() {
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isTrocouSenha() {
        return trocouSenha;
    }

    public void setTrocouSenha(boolean trocouSenha) {
        this.trocouSenha = trocouSenha;
    }

    
}
