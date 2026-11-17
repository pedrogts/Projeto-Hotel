package hotel.modelo;

import java.util.List;

/**
 * Calcula a conta de uma hospedagem (REQ11): total das diárias mais o total dos
 * consumos da estadia. Os valores são calculados a partir da estadia no
 * momento da consulta.
 */
public class Fatura {
    private float totalDiarias; 
    private float totalConsumos; 
    private Estadia estadia;

    /**
     * Cria a fatura de uma estadia.
     *
     * @param estadia hospedagem cuja conta será calculada
     *
     * @pre A estadia deve existir no hotel
     * @post A fatura fica associada à estadia informada
     */
    public Fatura(Estadia estadia){
        this.estadia = estadia;
    }

    /**
     * Calcula o valor total da fatura: total das diárias mais total dos consumos.
     *
     * @return o valor total da hospedagem em reais
     *
     * @pre A fatura deve estar associada a uma estadia
     * @post Os totais de diárias e de consumos ficam atualizados na fatura
     */
    public float getTotalGeral() {
        totalDiarias = getTotalDiarias();
        totalConsumos = getTotalConsumos(); 
        return totalDiarias + totalConsumos; 
    }

    /**
     * Devolve a estadia faturada.
     * @return a estadia informada na criação
     * @pre A fatura deve estar inicializada
     * @post Nenhum objeto é alterado
     */
    public Estadia getEstadia() { return estadia; }

    /**
     * Calcula o total das diárias: dias da estadia vezes o preço da diária do
     * tipo de apartamento (Simples ou Premium).
     *
     * @return o total das diárias em reais
     *
     * @pre A fatura deve estar associada a uma estadia com apartamento
     * @post Nenhum objeto é alterado
     */
    public float getTotalDiarias() { 
        int dias = estadia.getDias();
        Apartamento apto = estadia.getApto();
        float precoDiaria = apto.getPrecoDiaria();
        return dias * precoDiaria;
    }

    /**
     * Calcula o total dos consumos registrados na estadia.
     *
     * @return a soma dos valores dos consumos, ou 0 se não houver consumos
     *
     * @pre A fatura deve estar associada a uma estadia
     * @post Nenhum objeto é alterado
     */
    public float getTotalConsumos() {
        float total = 0.0f;
        List<Consumo> Listaconsumos = estadia.getConsumos();

        if(Listaconsumos != null){
            for(Consumo c: Listaconsumos){
                total += c.getTotal();
            }
        }
        return total; 
    }

    /**
     * Devolve a representação textual da fatura, no formato "Fatura: R$ total".
     *
     * @return texto com o valor total da fatura
     *
     * @pre A fatura deve estar associada a uma estadia
     * @post Nenhum objeto é alterado
     */
    @Override
    public String toString() {
        return "Fatura: R$ " + getTotalGeral();
    }
}