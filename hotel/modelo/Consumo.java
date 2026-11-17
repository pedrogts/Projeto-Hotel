package hotel.modelo;

/**
 * Registro do consumo de um serviço do cardápio por um apartamento (REQ10).
 * Associa um Servico a um Apartamento, com a quantidade consumida (composição).
 */
public class Consumo {
    private Apartamento apartamento;
    private Servico servico;
    private int quantidade;

    /**
     * Cria o registro de consumo de um serviço por um apartamento.
     *
     * @param apto apartamento que realizou o consumo
     * @param servico serviço do cardápio consumido
     * @param quantidade quantidade de unidades consumidas
     * @throws IllegalArgumentException se o serviço ou o apartamento forem nulos
     *
     * @pre O serviço e o apartamento devem existir
     * @post O consumo fica criado, associando o serviço ao apartamento
     */
    public Consumo(Apartamento apto, Servico servico, int quantidade) {
        if (servico == null)  {
            throw new IllegalArgumentException("Campo obrigatório: serviço");
        } else if (apto == null) {
            throw new IllegalArgumentException("Campo obrigatório: apartamento");
        }
        this.apartamento = apto;
        this.servico = servico;
        this.quantidade = quantidade;
    }

    /**
     * Devolve o apartamento que realizou o consumo.
     * @return o apartamento informado na criação
     * @pre O consumo deve estar inicializado
     * @post Nenhum objeto é alterado
     */
    public Apartamento getApartamento() { return apartamento; }
    /**
     * Devolve o serviço consumido.
     * @return o serviço informado na criação
     * @pre O consumo deve estar inicializado
     * @post Nenhum objeto é alterado
     */
    public Servico getServico() { return servico; }
    /**
     * Devolve a quantidade de unidades consumidas.
     * @return a quantidade informada na criação
     * @pre O consumo deve estar inicializado
     * @post Nenhum objeto é alterado
     */
    public int getQuantidade() { return quantidade; }

    /**
     * Calcula o valor total do consumo: preço unitário do serviço vezes a quantidade.
     *
     * @return o valor total do consumo em reais
     *
     * @pre O consumo deve estar inicializado
     * @post Nenhum objeto é alterado
     */
    public float getTotal() {
        return servico.getPreco() * quantidade;
    }

    /**
     * Devolve a representação textual do consumo, no formato
     * "serviço xquantidade = R$ total".
     *
     * @return texto com o serviço, a quantidade e o valor total
     *
     * @pre O consumo deve estar inicializado
     * @post Nenhum objeto é alterado
     */
    @Override
    public String toString() {
        return servico.getNome() + " x" + quantidade + " = R$ " + getTotal();
    }
}
