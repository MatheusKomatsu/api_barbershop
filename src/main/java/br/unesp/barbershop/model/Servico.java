package br.unesp.barbershop.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.JoinColumn;

@Entity
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private float preco;
    private float tempoServicoMinutos; 
    private String descricao;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "servicos_barbearias",
                uniqueConstraints = @UniqueConstraint (
                    columnNames = {"servico_id", "barbearia_id"},
                    name = "unique_user_servico"
                ),
                joinColumns = @JoinColumn(name = "servico_id",
                    referencedColumnName = "id",
                    table = "servico",
                    unique = false
                ),
                inverseJoinColumns = @JoinColumn (
                    name = "barbearia_id",
                    referencedColumnName = "id",
                    table = "barbearia",
                    unique = false
                    //updatable = false,
                )
    )
    private List<Barbearia> barbearias = new ArrayList<Barbearia>();

    @JsonIgnore
    @org.hibernate.annotations.ForeignKey(name = "agendamento_id")
    @ManyToOne
    private Agendamento agendamento;

    public Servico() {
    }

    public Servico(Long id, String nome, float preco, float tempoServicoMinutos, String descricao,
            List<Barbearia> barbearias, Agendamento agendamento) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.tempoServicoMinutos = tempoServicoMinutos;
        this.descricao = descricao;
        this.barbearias = barbearias;
        this.agendamento = agendamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getTempoServicoMinutos() {
        return tempoServicoMinutos;
    }

    public void setTempoServicoMinutos(float tempoServicoMinutos) {
        this.tempoServicoMinutos = tempoServicoMinutos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public List<Barbearia> getBarbearias() {
        return barbearias;
    }

    public void setBarbearias(List<Barbearia> barbearias) {
        this.barbearias = barbearias;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    
}
