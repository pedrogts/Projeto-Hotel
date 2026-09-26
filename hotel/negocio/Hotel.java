package hotel.negocio;

import hotel.modelo.*;
import java.util.ArrayList;

public class Hotel {
    public static final int NUM_ANDARES = 20;
    public static final int APTOS_POR_ANDAR = 14;
    public static final int SIMPLES_POR_ANDAR = 8;

    private Apartamento[][] matriz;
    private ArrayList<Servico> servicos;
    private ArrayList<Consumo> consumos;

    public Hotel() {
        this.matriz = new Apartamento[NUM_ANDARES][APTOS_POR_ANDAR];
        this.servicos = new ArrayList<>();
        this.consumos = new ArrayList<>();
        inicializar();
    }

    /**
     * Cria o hotel com todos os apartamentos livres, já diferenciados entre
     * Simples e Premium conforme a distribuição por andar.
     *
     * @pre Nenhuma
     * @post A matriz fica preenchida com todos os apartamentos em status LIVRE e
     *       sem hóspede; as listas de serviços e consumos começam vazias
     */
    private void inicializar() {
        for (int a = 0; a < NUM_ANDARES; a++) {
            for (int n = 0; n < APTOS_POR_ANDAR; n++) {
                if (n < SIMPLES_POR_ANDAR) {
                    matriz[a][n] = new ApartamentoSimples();
                } else {
                    matriz[a][n] = new ApartamentoPremium();
                }
            }
        }
    }

    private boolean aptoValido(int andar, int numero) {
        return andar >= 0 && andar < NUM_ANDARES && numero >= 0 && numero < APTOS_POR_ANDAR;
    }

    public boolean reservarApartamento(int andar, int numero, Hospede hospede) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        Apartamento apto = getApartamento(andar,numero);
        try{
            apto.reservar(hospede);
        } catch(IllegalStateException | IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean realizarCheckin(int andar, int numero, Hospede hospede) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        Apartamento apto = getApartamento(andar, numero);
        try {
            apto.checkin(hospede);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean realizarCheckout(int andar, int numero) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        Apartamento apto = getApartamento(andar, numero);
        try {
            apto.checkout();
        } catch (IllegalStateException | IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean cancelarReserva(int andar, int numero) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        throw new UnsupportedOperationException("Implementar cancelarReserva");
    }

    public void mostrarMapa() {
        throw new UnsupportedOperationException("Implementar mostrarMapa");
    }

    public void consultarApartamento(int andar, int numero) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        throw new UnsupportedOperationException("Implementar consultarApartamento");
    }

    public float calcularTaxaOcupacao() {
        return 0f;
        //throw new UnsupportedOperationException("Implementar calcularTaxaOcupacao");
    }

    public float calcularTaxaReservas() {
        throw new UnsupportedOperationException("Implementar calcularTaxaReservas");
    }

    public void cadastrarServico(String nome, float preco) {
        throw new UnsupportedOperationException("Implementar cadastrarServico");
    }

    public boolean registrarConsumo(int andar, int numero, int indiceServico, int quantidade) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        throw new UnsupportedOperationException("Implementar registrarConsumo");
    }

    public ArrayList<Consumo> getConsumosDoApartamento(int andar, int numero) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        throw new UnsupportedOperationException("Implementar getConsumosDoApartamento");
    }

    public Fatura emitirFatura(int andar, int numero, int dias) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        throw new UnsupportedOperationException("Implementar emitirFatura");
    }

    /**
     * Devolve o apartamento localizado nas coordenadas informadas.
     *
     * @param andar número do andar, de 0 a {@value #NUM_ANDARES} menos um
     * @param numero número do apartamento no andar, de 0 a {@value #APTOS_POR_ANDAR} menos um
     * @return o apartamento naquela posição
     * @throws IllegalArgumentException se as coordenadas estiverem fora dos limites
     *
     * @pre As coordenadas devem estar dentro dos limites do hotel
     * @post Nenhum objeto é alterado
     */
    public Apartamento getApartamento(int andar, int numero) {
        return matriz[andar][numero];
    }

    public ArrayList<Servico> getServicos() { return servicos; }
    public ArrayList<Consumo> getConsumos() { return consumos; }
}
