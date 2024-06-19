package br.unesp.barbershop.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date data;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "barbearia_id", referencedColumnName = "id")
    private Barbearia barbearia;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "servico_id", referencedColumnName = "id")
    private Servico servico;

    public Agendamento() {
    }

    public Agendamento(Long id, Date data, Usuario usuario, Barbearia barbearia, Servico servico) {
        this.id = id;
        this.data = data;
        this.usuario = usuario;
        this.servico = servico;
        this.barbearia = barbearia;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Barbearia getBarbearia() {
        return barbearia;
    }

    public void setBarbearia(Barbearia barbearia) {
        this.barbearia = barbearia;
    }
}
