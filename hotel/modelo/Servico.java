package hotel.modelo;
import java.io.Serializable;

public class Servico implements Serializable{
    private String nome;
    private float preco;

    public Servico(String nome, float preco) {
        if (nome == null || nome.isBlank() ){
            throw new IllegalArgumentException("Campo obrigatório: nome");
        }
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() { return nome; }
    public float getPreco() { return preco; }

    @Override
    public String toString() {
        return nome + " (R$ " + preco + ")";
    }
}
