package clinica;

public class EspecieBean {
    private int idEspecie;
    private String descricao;

    public EspecieBean(int idEspecie, String descricao) {
        this.idEspecie = idEspecie;
        this.descricao = descricao;
    }

    public EspecieBean(String descricao) {
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getIdEspecie() { return idEspecie; }
    public void setIdEspecie(int idEspecie) { this.idEspecie = idEspecie; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return "ID: " + idEspecie + " | Descrição: " + descricao;
    }
}
