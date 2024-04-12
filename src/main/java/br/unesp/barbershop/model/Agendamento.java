package br.unesp.barbershop.model;

import java.util.ArrayList;
import java.util.Date;
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
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date data;

    @JsonIgnore
    @org.hibernate.annotations.ForeignKey(name = "usuario_id")
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "agendamento", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Servico> servicos = new ArrayList<Servico>();

    public Agendamento() {
    }

    public Agendamento(Long id, Date data, Usuario usuario, List<Servico> servicos) {
        this.id = id;
        this.data = data;
        this.usuario = usuario;
        this.servicos = servicos;
    }

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

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    
}
