package br.unesp.barbershop.dto;

public class ServicoDTO {
    private Long id;
    private String nome;
    private float preco;
    private float tempoServicoMinutos; 
    private String imagem;
    private String descricao;
    private Long barbearia_id;

    public ServicoDTO() {
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
    public Long getBarbearia_id() {
        return barbearia_id;
    }
    public void setBarbearia_id(Long barbearia_id) {
        this.barbearia_id = barbearia_id;
    }
    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
}
