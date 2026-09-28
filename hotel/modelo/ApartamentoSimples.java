package hotel.modelo;

public class ApartamentoSimples extends Apartamento {
    private static final float PRECO_DIARIA = 150.0f;

    /**
     * Cria um apartamento Simples, delegando a inicialização de estado
     * (status LIVRE, sem hóspede) ao construtor da superclasse.
     *
     * @pre nenhuma
     * @post o apartamento é criado com status LIVRE
     */
    public ApartamentoSimples() {
        super();
    }


    /**
     * Retorna o preço da diária deste apartamento.
     *
     * @return o valor fixo de {@value #PRECO_DIARIA} para apartamentos Simples
     *
     * @pre nenhuma
     * @post nenhum estado é alterado (método de consulta)
     */
    @Override
    public float getPrecoDiaria() {
        return PRECO_DIARIA;
    }
}
