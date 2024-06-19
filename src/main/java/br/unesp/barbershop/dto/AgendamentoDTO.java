package br.unesp.barbershop.dto;
import java.util.Date;

public class AgendamentoDTO {

    private Date data;

    private Long barbearia_id;

    private Long usuario_id;

    private Long servico_id;

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

    public Long getServico_id() {
        return servico_id;
    }

    public void setServico_id(Long servico_id) {
        this.servico_id = servico_id;
    }

    public Long getBarbearia_id() {
        return barbearia_id;
    }

    public void setBarbearia_id(Long barbearia_id) {
        this.barbearia_id = barbearia_id;
    }

}
