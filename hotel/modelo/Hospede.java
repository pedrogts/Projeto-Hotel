package hotel.modelo;

public class Hospede {
    private final String cpf;
    private final String nome;
    private final String endereco;
    private final String celular;
    private final String email;

    public Hospede(String cpf, String nome, String endereco, String celular, String email) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("Campo obrigatório : CPF");
        } else if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Campo obrigatório : Nome");
        } else if (endereco == null || endereco.isBlank()) {
            throw new IllegalArgumentException("Campo obrigatório : Endereco");
        } else if (celular == null || celular.isBlank()) {
            throw new IllegalArgumentException("Campo obrigatório : Celular");
        } else if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Campo obrigatório : E-mail");
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Hospede outro)) {
            return false;
        }
        return this.cpf.equals(outro.getCpf());
    }

    @Override
    public int hashCode() {
        return cpf.hashCode();
    }
}
