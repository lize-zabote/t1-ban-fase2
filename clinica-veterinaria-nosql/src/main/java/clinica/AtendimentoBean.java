package clinica;

public class AtendimentoBean {
    private int idAgendamento;
    private int idFuncionario;
    private String nomeFuncionario;
    private String nomeAnimal;
    private String descServico;
    private java.sql.Date data;

    public AtendimentoBean(int idAgendamento, int idFuncionario) {
        this.idAgendamento = idAgendamento;
        this.idFuncionario = idFuncionario;
    }

    // Getters e Setters
    public int getIdAgendamento() { return idAgendamento; }
    public void setIdAgendamento(int idAgendamento) { this.idAgendamento = idAgendamento; }
    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }
    public String getNomeFuncionario() { return nomeFuncionario; }
    public void setNomeFuncionario(String nomeFuncionario) { this.nomeFuncionario = nomeFuncionario; }
    public String getNomeAnimal() { return nomeAnimal; }
    public void setNomeAnimal(String nomeAnimal) { this.nomeAnimal = nomeAnimal; }
    public String getDescServico() { return descServico; }
    public void setDescServico(String descServico) { this.descServico = descServico; }
    public java.sql.Date getData() { return data; }
    public void setData(java.sql.Date data) { this.data = data; }

    @Override
    public String toString() {
        return "Data: " + data + " | Funcionário: " + nomeFuncionario + " | Animal: " + nomeAnimal + " | Serviço: " + descServico;
    }
}
