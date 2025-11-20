package clinica;

public class ClienteBean {
    private int idCliente;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cep;
    private String bairro;
    private String rua;

    public ClienteBean(int idCliente, String nome, String sobrenome, String telefone, String cep, String bairro, String rua) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
    }

    public ClienteBean(String nome, String sobrenome, String telefone, String cep, String bairro, String rua) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
    }

    // Getters e Setters
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    @Override
    public String toString() {
        return "ID: " + idCliente + " | Nome: " + nome + " " + sobrenome + " | Telefone: " + telefone + " | Endereço: " + rua + ", " + bairro + " - CEP: " + cep;
    }
}

