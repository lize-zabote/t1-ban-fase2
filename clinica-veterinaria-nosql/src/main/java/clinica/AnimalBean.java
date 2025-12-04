package clinica;

import java.sql.Date;

public class AnimalBean {
    private int idAnimal;
    private String nome;
    private Date dataNascimento;
    private int idEspecie;
    private int idCliente;
    private String nomeCliente;
    private String nomeEspecie;

    public AnimalBean(int idAnimal, String nome, Date dataNascimento, int idEspecie, int idCliente) {
        this.idAnimal = idAnimal;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.idEspecie = idEspecie;
        this.idCliente = idCliente;
    }
    
    public AnimalBean(String nome, Date dataNascimento, int idEspecie, int idCliente) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.idEspecie = idEspecie;
        this.idCliente = idCliente;
    }

    // Getters e Setters
    public int getIdAnimal() { return idAnimal; }
    public void setIdAnimal(int idAnimal) { this.idAnimal = idAnimal; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
    public int getIdEspecie() { return idEspecie; }
    public void setIdEspecie(int idEspecie) { this.idEspecie = idEspecie; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
    public String getNomeEspecie() { return nomeEspecie; }
    public void setNomeEspecie(String nomeEspecie) { this.nomeEspecie = nomeEspecie; }
    
    @Override
    public String toString() {
        return "ID: " + idAnimal + " | Nome: " + nome + " | Data Nasc.: " + dataNascimento +
               " | Dono: " + nomeCliente + " | Espécie: " + nomeEspecie;
    }
    
    public String toShortString() {
        return "ID: " + idAnimal + " | Nome: " + nome;
    }
}
