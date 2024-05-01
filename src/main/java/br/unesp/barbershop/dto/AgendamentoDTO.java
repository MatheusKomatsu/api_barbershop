package br.unesp.barbershop.dto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgendamentoDTO {
    private Long id;

    private Date data;

    private Long barbearia_id;

    private Long usuario_id;

    private List<Long> servicos_id = new ArrayList<Long>();

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

    public Long getBarbearia_id() {
        return barbearia_id;
    }

    public void setBarbearia_id(Long barbearia_id) {
        this.barbearia_id = barbearia_id;
    }

}
