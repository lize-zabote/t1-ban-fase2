package clinica;

import java.math.BigDecimal;

public class ServicoBean {
    private int idServico;
    private BigDecimal valor;
    private String descricao;

    public ServicoBean(int idServico, BigDecimal valor, String descricao) {
        this.idServico = idServico;
        this.valor = valor;
        this.descricao = descricao;
    }

    public ServicoBean(BigDecimal valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getIdServico() { return idServico; }
    public void setIdServico(int idServico) { this.idServico = idServico; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return "ID: " + idServico + " | Descrição: " + descricao + " | Valor: R$" + valor;
    }
}
