package hotel.modelo;
import java.io.Serializable;

/**
 * Representa um item do cardápio do hotel, com nome e preço (REQ10).
 */
public class Servico implements Serializable{
    private String nome;
    private float preco;

    /**
     * Cria um serviço do cardápio com nome e preço.
     *
     * @param nome nome do serviço exibido no cardápio
     * @param preco preço unitário do serviço
     * @throws IllegalArgumentException se o nome for nulo, vazio ou composto apenas por espaços
     *
     * @pre O nome deve conter texto não vazio
     * @post O serviço fica criado com o nome e o preço informados
     */
    public Servico(String nome, float preco) {
        if (nome == null || nome.isBlank() ){
            throw new IllegalArgumentException("Campo obrigatório: nome");
        }
        this.nome = nome;
        this.preco = preco;
    }

    /**
     * Devolve o nome do serviço.
     * @return o nome informado na criação
     * @pre O serviço deve estar inicializado
     * @post Nenhum objeto é alterado
     */
    public String getNome() { return nome; }
    /**
     * Devolve o preço unitário do serviço.
     * @return o preço informado na criação
     * @pre O serviço deve estar inicializado
     * @post Nenhum objeto é alterado
     */
    public float getPreco() { return preco; }

    /**
     * Devolve a representação textual do serviço, no formato "nome (R$ preço)".
     *
     * @return texto com o nome e o preço do serviço
     *
     * @pre O serviço deve estar inicializado
     * @post Nenhum objeto é alterado
     */
    @Override
    public String toString() {
        return nome + " (R$ " + preco + ")";
    }
}
