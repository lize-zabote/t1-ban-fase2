package clinica;

public class FuncionarioBean {
    private int idFuncionario;
    private String nome;
    private String sobrenome;
    private String rua;
    private String cep;
    private String bairro;
    private String especialidade;
    private String cargo;

    public FuncionarioBean(int idFuncionario, String nome, String sobrenome, String rua, String cep, String bairro, String especialidade, String cargo) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rua = rua;
        this.cep = cep;
        this.bairro = bairro;
        this.especialidade = especialidade;
        this.cargo = cargo;
    }
    
    // Construtor sem ID para inserção
    public FuncionarioBean(String nome, String sobrenome, String rua, String cep, String bairro, String especialidade, String cargo) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rua = rua;
        this.cep = cep;
        this.bairro = bairro;
        this.especialidade = especialidade;
        this.cargo = cargo;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    @Override
    public String toString(){
        return "ID: " + idFuncionario + " | Nome: " + nome + " " + sobrenome + " | Cargo: " + cargo + " | Especialidade: " + especialidade + " | Endereço: " + rua + ", " + bairro + " - CEP: " + cep;
    }
}

