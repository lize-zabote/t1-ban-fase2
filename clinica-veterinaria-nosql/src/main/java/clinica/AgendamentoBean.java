package clinica;

import java.sql.Date;
import java.sql.Time;

public class AgendamentoBean {
    private int idAgendamento;
    private Date data;
    private Time hora;
    private int idServico;
    private int idAnimal;
    private String nomeAnimal;
    private String descServico;

    public AgendamentoBean(int idAgendamento, Date data, Time hora, int idServico, int idAnimal) {
        this.idAgendamento = idAgendamento;
        this.data = data;
        this.hora = hora;
        this.idServico = idServico;
        this.idAnimal = idAnimal;
    }

    public AgendamentoBean(Date data, Time hora, int idServico, int idAnimal) {
        this.data = data;
        this.hora = hora;
        this.idServico = idServico;
        this.idAnimal = idAnimal;
    }

    // Getters e Setters
    public int getIdAgendamento() { return idAgendamento; }
    public void setIdAgendamento(int idAgendamento) { this.idAgendamento = idAgendamento; }
    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }
    public Time getHora() { return hora; }
    public void setHora(Time hora) { this.hora = hora; }
    public int getIdServico() { return idServico; }
    public void setIdServico(int idServico) { this.idServico = idServico; }
    public int getIdAnimal() { return idAnimal; }
    public void setIdAnimal(int idAnimal) { this.idAnimal = idAnimal; }
    public String getNomeAnimal() { return nomeAnimal; }
    public void setNomeAnimal(String nomeAnimal) { this.nomeAnimal = nomeAnimal; }
    public String getDescServico() { return descServico; }
    public void setDescServico(String descServico) { this.descServico = descServico; }

    @Override
    public String toString() {
        return "ID: " + idAgendamento + " | Data: " + data + " | Hora: " + hora +
               " | Animal: " + nomeAnimal + " | Serviço: " + descServico;
    }
}
