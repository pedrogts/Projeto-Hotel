package hotel.modelo;

public class ApartamentoPremium extends Apartamento {
    private static final float PRECO_DIARIA = 350.0f;

    /**
     * Cria um apartamento Premium, delegando a inicialização de estado
     * (status LIVRE, sem hóspede) ao construtor da superclasse.
     *
     * @pre nenhuma
     * @post o apartamento é criado com status LIVRE
     */
    public ApartamentoPremium() {
        super();
    }

    /**
     * Retorna o preço da diária deste apartamento.
     *
     * @return o valor fixo de {@value #PRECO_DIARIA} para apartamentos Premium
     *
     * @pre nenhuma
     * @post nenhum estado é alterado (método de consulta)
     */
    @Override
    public float getPrecoDiaria() {
        return PRECO_DIARIA;
    }
}
