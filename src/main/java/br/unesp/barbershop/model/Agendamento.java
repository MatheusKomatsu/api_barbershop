package br.unesp.barbershop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;

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


    @JsonIgnore
    @org.hibernate.annotations.ForeignKey(name="barbearia_id")
    @ManyToOne
    private Barbearia barbearia;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "servicos_agendamentos",
                uniqueConstraints = @UniqueConstraint (
                    columnNames = {"servico_id", "agendamento_id"},
                    name = "unique_user_servico"
                ),
                joinColumns = @JoinColumn(name = "agendamento_id",
                    referencedColumnName = "id",
                    table = "agendamento",
                    unique = false
                ),
                inverseJoinColumns = @JoinColumn (
                    name = "servico_id",
                    referencedColumnName = "id",
                    table = "servico",
                    unique = false
                    //updatable = false,
                )
    )
    private List<Servico> servicos = new ArrayList<Servico>();


    public Agendamento() {
    }

    public Agendamento(Long id, Date data, Usuario usuario, Barbearia barbearia, List<Servico> servicos) {
        this.id = id;
        this.data = data;
        this.usuario = usuario;
        this.servicos = servicos;
        this.barbearia = barbearia;
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
    public Barbearia getBarbearia() {
        return barbearia;
    }

    public void setBarbearia(Barbearia barbearia) {
        this.barbearia = barbearia;
    }
    
}
