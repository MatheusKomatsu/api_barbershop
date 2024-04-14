package br.unesp.barbershop.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.UniqueConstraint;

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
    @org.hibernate.annotations.ForeignKey(name = "barbearia_id")
    @ManyToOne
    private Barbearia barbearia;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "servicos_agendamentos",
                uniqueConstraints = @UniqueConstraint (
                    columnNames = {"servico_id", "agendamento_id"},
                    name = "unique_user_servico"
                ),
                joinColumns = @JoinColumn(name = "servico_id",
                    referencedColumnName = "id",
                    table = "servico",
                    unique = false
                ),
                inverseJoinColumns = @JoinColumn (
                    name = "agendamento_id",
                    referencedColumnName = "id",
                    table = "agendamento",
                    unique = false
                    //updatable = false,
                )
    )
    private List<Agendamento> agendamentos;

    public Servico() {
    }

    public Servico(Long id, String nome, float preco, float tempoServicoMinutos, String descricao, Barbearia barbearia,
            List<Agendamento> agendamentos) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.tempoServicoMinutos = tempoServicoMinutos;
        this.descricao = descricao;
        this.barbearia = barbearia;
        this.agendamentos = agendamentos;
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
    
    public Barbearia getBarbearia() {
        return barbearia;
    }

    public void setBarbearia(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
}
