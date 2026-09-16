package hotel.modelo;

public class Hospede {
    private String cpf;
    private String nome;
    private String endereco;
    private String celular;
    private String email;

    public Hospede(String cpf, String nome, String endereco, String celular, String email) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("Campo obrigatório : CPF");
        } else if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Campo obrigatório : Nome");
        }
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.celular = celular;
        this.email = email;
    }

    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getCelular() { return celular; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}
