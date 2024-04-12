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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "servicos_barbearias",
                uniqueConstraints = @UniqueConstraint (
                    columnNames = {"servico_id", "barbearia_id"},
                    name = "unique_user_servico"
                ),
                joinColumns = @JoinColumn(name = "barbearia_id",
                    referencedColumnName = "id",
                    table = "barbearia",
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Getter e Setter para usuarios
    public List<Servico> getUsuarios() {
        return servicos;
    }

    public void setUsuarios(List<Servico> usuarios) {
        this.servicos = usuarios;
    }

    public Barbearia() {
    }

    public Barbearia(Long id, String nomeBarbearia, String endereco, Usuario usuario, List<Servico> servicos) {
        this.id = id;
        this.nomeBarbearia = nomeBarbearia;
        this.endereco = endereco;
        this.usuario = usuario;
        this.servicos = servicos;
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

}
