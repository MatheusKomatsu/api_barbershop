package br.unesp.barbershop.model;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Barbearia{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nomeBarbearia;
    private String endereco;

    @JsonIgnore
    @org.hibernate.annotations.ForeignKey(name = "usuario_id")
    @ManyToOne
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "barbearia", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Servico> servicos = new ArrayList<Servico>();

    @JsonIgnore
    @OneToMany(mappedBy="barbearia", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos = new ArrayList<Agendamento>();


    public Barbearia() {
    }

    public Barbearia(Long id, String nomeBarbearia, String endereco, Usuario usuario, List<Servico> servicos, List<Agendamento> agendamentos) {
        this.id = id;
        this.nomeBarbearia = nomeBarbearia;
        this.endereco = endereco;
        this.usuario = usuario;
        this.servicos = servicos;
        this.agendamentos = agendamentos;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }
   public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }    

}
