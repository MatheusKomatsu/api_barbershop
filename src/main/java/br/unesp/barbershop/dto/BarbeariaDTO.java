package br.unesp.barbershop.dto;

import java.util.ArrayList;
import java.util.List;

public class BarbeariaDTO {
    private Long id;
    private String nomeBarbearia;
    private String endereco;
    private String imagem;
    private Long usuario_id;
    private List<Long> servicos_id = new ArrayList<Long>();
    private List<Long> agendamentos_id = new ArrayList<Long>();

    public BarbeariaDTO() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNomeBarbearia() {
        return nomeBarbearia;
    }
    public void setNomeBarbearia(String nomeBarbearia) {
        this.nomeBarbearia = nomeBarbearia;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public Long getUsuario_id() {
        return usuario_id;
    }
    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }
    
    public List<Long> getServicos_id() {
        return servicos_id;
    }

    public void setServicos_id(List<Long> servicos_id) {
        this.servicos_id = servicos_id;
    }

    public List<Long> getAgendamentos_id() {
        return agendamentos_id;
    }

    public void setAgendamentos_id(List<Long> agendamentos_id) {
        this.agendamentos_id = agendamentos_id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
