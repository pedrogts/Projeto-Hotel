package hotel.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma hospedagem: o período de um hóspede responsável em um
 * apartamento, com número próprio, quantidade de dias e os consumos daquela
 * hospedagem. É a partir dela que a fatura é calculada.
 */

public class Estadia implements Serializable {
    private int idReserva;
    private Apartamento apto;
    private Hospede responsavel;
    private int dias;
    private List<Consumo> consumos;
    
    /**
     * Cria uma estadia com lista de consumos vazia.
     *
     * @param idReserva número que identifica a estadia no hotel
     * @param apto apartamento da hospedagem
     * @param responsavel hóspede responsável pela hospedagem
     * @param dias quantidade de diárias da hospedagem
     *
     * @pre O número da estadia deve ser único no hotel
     * @post A estadia fica criada com os dados informados e sem consumos
     */
    public Estadia(int idReserva, Apartamento apto, Hospede responsavel, int dias){
        this.idReserva = idReserva;
        this.apto = apto;
        this.responsavel = responsavel;
        this.dias = dias;
        consumos = new ArrayList<>();
    }

    /**
     * Devolve o número que identifica a estadia.
     * @return o número informado na criação
     * @pre A estadia deve estar inicializada
     * @post Nenhum objeto é alterado
     */
    public int getIdReserva() { return idReserva; }
    /**
     * Devolve o apartamento da hospedagem.
     * @return o apartamento atual da estadia
     * @pre A estadia deve estar inicializada
     * @post Nenhum objeto é alterado
     */
    public Apartamento getApto() { return apto; }
    /**
     * Devolve o hóspede responsável pela hospedagem.
     * @return o hóspede responsável atual
     * @pre A estadia deve estar inicializada
     * @post Nenhum objeto é alterado
     */
    public Hospede getResponsavel() { return responsavel; }

    /**
     * Devolve a quantidade de diárias da hospedagem.
     * @return a quantidade de dias atual
     * @pre A estadia deve estar inicializada
     * @post Nenhum objeto é alterado
     */
    public int getDias() { return dias; }
    /**
     * Devolve a lista de consumos da hospedagem.
     * @return a lista de consumos, vazia se nada foi consumido
     * @pre A estadia deve estar inicializada
     * @post Nenhum objeto é alterado
     */
    public List<Consumo> getConsumos() { return consumos; }

    /**
     * Transfere a hospedagem para outro apartamento.
     *
     * @param apto novo apartamento da estadia
     * @throws IllegalArgumentException se o apartamento for nulo
     *
     * @pre O apartamento informado deve existir
     * @post A estadia passa a estar associada ao novo apartamento
     */
    public void alterarApartamento(Apartamento apto) {
        if(apto != null){
            this.apto = apto;
        } else {
            throw new IllegalArgumentException("Não é possível fazer alteração sem apartamento");
        }            
    }

    /**
     * Troca o hóspede responsável pela hospedagem.
     *
     * @param responsavel novo hóspede responsável
     * @throws IllegalArgumentException se o responsável for nulo
     *
     * @pre O hóspede informado deve existir
     * @post A estadia passa a ter o novo hóspede como responsável
     */
    public void alterarResponsavel(Hospede responsavel) {
        if(responsavel != null){
            this.responsavel = responsavel;
        } else {
            throw new IllegalArgumentException("Não é possível fazer alteração sem resposável");
        }
                     
    }

    /**
     * Altera a quantidade de diárias da hospedagem.
     *
     * @param dias nova quantidade de dias
     * @throws IllegalArgumentException se a quantidade for menor que 1
     *
     * @pre A quantidade de dias deve ser maior que zero
     * @post A estadia passa a ter a nova quantidade de dias
     */
    public void alterarQtdDias(int dias){
        if(dias > 0){
            this.dias = dias;
        } else{
            throw new IllegalArgumentException("Não é permitido definir uma estadia com menos de 1 dia");
        }
        
    } 

}
