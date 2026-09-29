package hotel.negocio;

import hotel.modelo.*;
import java.util.ArrayList;

public class Hotel {
    public static final int NUM_ANDARES = 20;
    public static final int APTOS_POR_ANDAR = 14;
    public static final int SIMPLES_POR_ANDAR = 8;
    private static final float TOTAL_APARTAMENTOS = NUM_ANDARES * APTOS_POR_ANDAR;

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

    /**
     * Reserva um apartamento, mudando seu status de LIVRE para RESERVADO.
     *
     * @param andar Numero do andar (0 a 19)
     * @param numero Numero do apartamento no andar (0 a 13)
     * @param hospede Dados do hospede que fará a reserva
     * @return true se a reserva foi bem-sucedida
     * @throws IllegalArgumentException se andar ou numero forem invalidos
     *
     * @pre O hotel deve estar inicializado
     * @post Se bem-sucedido, o apartamento tera status RESERVADO e o hospede sera armazenado
     */
    public boolean reservarApartamento(int andar, int numero, Hospede hospede) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }

        if(matriz[andar][numero].estaLivre()){
            matriz[andar][numero].reservar(hospede);
            return true;
        }

        return false;
    }

    /**
     * Realiza o check-in de um hospede em um apartamento, mudando seu status
     * para OCUPADO.
     *
     * @param andar Numero do andar (0 a 19)
     * @param numero Numero do apartamento no andar (0 a 13)
     * @param hospede Dados do hospede que fara o check-in
     * @return true se o check-in foi bem-sucedido
     * @throws IllegalArgumentException se andar ou numero forem invalidos
     *
     * @pre O hotel deve estar inicializado
     * @post Se bem-sucedido, o apartamento tera status OCUPADO e o hospede sera armazenado
     */
    public boolean realizarCheckin(int andar, int numero, Hospede hospede) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }

        if(matriz[andar][numero].estaLivre() || matriz[andar][numero].estaReservado()){
            matriz[andar][numero].checkin(hospede);
            return true;
        }

        return false;
    }

    /**
     * Realiza o check-out de um apartamento, liberando para uma nova reserva
     * ou ocupacao e removendo o vinculo com o hospede.
     *
     * @param andar Numero do andar (0 a 19)
     * @param numero Numero do apartamento no andar (0 a 13)
     * @return true se o check-out foi bem-sucedido
     * @throws IllegalArgumentException se andar ou numero forem invalidos
     *
     * @pre O hotel deve estar inicializado
     * @post Se bem-sucedido, o apartamento tera status LIVRE e nenhum hospede associado
     */
    public boolean realizarCheckout(int andar, int numero) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }

        if(matriz[andar][numero].estaOcupado()){
            matriz[andar][numero].checkout();
            return true;
        }

        return false;
    }

    /**
     * Cancela a reserva de um apartamento, liberando para uma nova reserva
     * ou ocupacao e removendo o vinculo com o hospede associado.
     *
     * @param andar Numero do andar (0 a 19)
     * @param numero Numero do apartamento no andar (0 a 13)
     * @return true se o cancelamento foi bem-sucedido
     * @throws IllegalArgumentException se andar ou numero forem invalidos
     *
     * @pre O hotel deve estar inicializado
     * @post Se bem-sucedido, o apartamento tera status LIVRE e nenhum hospede associado
     */
    public boolean cancelarReserva(int andar, int numero) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }

        if(matriz[andar][numero].estaReservado()){
            matriz[andar][numero].cancelarReserva();
            return true;
        }

        return false;
    }

    /**
     * Exibe o mapa de ocupacao do hotel, mostrando cada apartamento
     * e seus respectivos status.
     *
     * @pre O hotel deve estar inicializado
     * @post exibição da situação de ocupacao do hotel
     */
    public void mostrarMapa() {
        char c;
        for (int a = 0; a < NUM_ANDARES; a++) {
            System.out.print("Andar " + (a) + ": ");
            for (int n = 0; n < APTOS_POR_ANDAR; n++) {
                c = matriz[a][n].getSymbol();
                System.out.print(c + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Consulta a situação de um apartamento específico e exibe os dados do hóspede (se houver).
     * @param andar Número do andar (0 a 19)
     * @param numero Número do apartamento no andar (0 a 13)
     * @throws IllegalArgumentException se o andar ou o número forem inválidos
     * @pre O hotel deve estar inicializado.
     * @post Exibe o status do apartamento e os dados do hóspede (se ocupado ou reservado) no terminal.
     */
    public void consultarApartamento(int andar, int numero) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou número de apartamento inválido.");
        }

        Apartamento apto = getApartamento(andar, numero);
        System.out.println("Apartamento [" + andar + "][" + numero + "] - Status: " + apto.getStatus());

        if (!apto.estaLivre()) {
            System.out.println("Dados do Hóspede: " + apto.getHospede().toString());
        }
    }

    /**
     * Calcula a proporção de apartamentos do hotel que estão ocupados (REQ07).
     *
     * @return valor entre 0.0 e 1.0, onde 1.0 significa o hotel totalmente ocupado
     *
     * @pre Nenhuma
     * @post Nenhum objeto é alterado
     */
    public float calcularTaxaOcupacao() {
        int ocupados = 0;
        for (int a = 0; a < NUM_ANDARES; a++) {
            for (int n = 0; n < APTOS_POR_ANDAR; n++) {
                if (matriz[a][n].estaOcupado()) {
                    ocupados++;
                }
            }
        }
        return ocupados / TOTAL_APARTAMENTOS;
    }

    /**
     * Calcula a proporção de apartamentos do hotel que estão reservados (REQ07).
     *
     * @return valor entre 0.0 e 1.0, onde 1.0 significa todos os apartamentos reservados
     *
     * @pre Nenhuma
     * @post Nenhum objeto é alterado
     */
    public float calcularTaxaReservas() {
        int reservados = 0;
        for (int a = 0; a < NUM_ANDARES; a++) {
            for (int n = 0; n < APTOS_POR_ANDAR; n++) {
                if (matriz[a][n].estaReservado()) {
                    reservados++;
                }
            }
        }
        return reservados / TOTAL_APARTAMENTOS;
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
