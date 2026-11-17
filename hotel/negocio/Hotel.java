package hotel.negocio;

import hotel.modelo.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Fachada do sistema: gerencia os 280 apartamentos (20 andares com 14
 * apartamentos), o cardápio de serviços, os consumos e as estadias, e expõe
 * os casos de uso chamados pela camada de apresentação.
 */
public class Hotel implements Serializable{
    public static final int NUM_ANDARES = 20;
    public static final int APTOS_POR_ANDAR = 14;
    public static final int SIMPLES_POR_ANDAR = 8;
    private static final float TOTAL_APARTAMENTOS = NUM_ANDARES * APTOS_POR_ANDAR;

    private Apartamento[][] matriz;
    private ArrayList<Servico> servicos;
    private ArrayList<Consumo> consumos;
    private ArrayList<Estadia> estadias;
    private int idEstadia = 0;

    /**
     * Cria o hotel com todos os apartamentos livres, já diferenciados entre
     * Simples e Premium, e com as listas de serviços, consumos e estadias vazias.
     *
     * @pre Nenhuma
     * @post O hotel fica inicializado com {@value #NUM_ANDARES} andares de
     *       {@value #APTOS_POR_ANDAR} apartamentos, todos LIVRES
     */
    public Hotel() {
        this.matriz = new Apartamento[NUM_ANDARES][APTOS_POR_ANDAR];
        this.servicos = new ArrayList<>();
        this.consumos = new ArrayList<>();
        this.estadias = new ArrayList<>();
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

    private boolean precoValido(float preco) {
        return preco > 0;
    }

    private boolean quantidadeValida(int quantidade){
        return quantidade > 0;
    }

    private boolean servicoValido(int indiceServico) {
        return indiceServico >= 0 && indiceServico < servicos.size();
    }

    /**
     * Reserva um apartamento, mudando seu status de LIVRE para RESERVADO.
     *
     * @param andar Numero do andar (0 a 19)
     * @param numero Numero do apartamento no andar (0 a 13)
     * @param hospede Dados do hospede que fará a reserva
     * @param dias quantidade de diárias previstas para a estadia
     * @return true se a reserva foi bem-sucedida
     * @throws IllegalArgumentException se andar ou numero forem invalidos
     *
     * @pre O hotel deve estar inicializado
     * @post Se bem-sucedido, o apartamento tera status RESERVADO e o hospede sera armazenado
     */
    public boolean reservarApartamento(int andar, int numero, Hospede hospede, int dias) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        Apartamento apto = getApartamento(andar,numero);
        Estadia estadia = new Estadia(++idEstadia, apto, hospede, dias);
        estadias.add(estadia);
        apto.reservar(hospede);
        return true;
    }

    /**
     * Realiza o check-in de um hospede em um apartamento, mudando seu status
     * para OCUPADO.
     *
     * @param andar Numero do andar (0 a 19)
     * @param numero Numero do apartamento no andar (0 a 13)
     * @param hospede Dados do hospede que fara o check-in
     * @param dias quantidade de diárias da estadia
     * @return true se o check-in foi bem-sucedido
     * @throws IllegalArgumentException se andar ou numero forem invalidos
     *
     * @pre O hotel deve estar inicializado
     * @post Se bem-sucedido, o apartamento tera status OCUPADO e o hospede sera armazenado
     */
    public boolean realizarCheckin(int andar, int numero, Hospede hospede, int dias) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        Apartamento apto = getApartamento(andar, numero);
        apto.checkin(hospede);
        Estadia estadia = new Estadia(++idEstadia, apto, hospede, dias);
        estadias.add(estadia);
        return true;
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
        Apartamento apto = getApartamento(andar, numero);
        apto.checkout();
        return true;
    }

    /**
     * Cancela a reserva de um apartamento, liberando para uma nova reserva
     * ou ocupacao e removendo o vinculo com o hospede associado.
     *
     * @param andar Numero do andar (0 a 19)
     * @param numero Numero do apartamento no andar (0 a 13)
     * @param id número da estadia (reserva) a ser cancelada
     * @return true se o cancelamento foi bem-sucedido
     * @throws IllegalArgumentException se andar ou numero forem invalidos
     *
     * @pre O hotel deve estar inicializado
     * @post Se bem-sucedido, o apartamento tera status LIVRE e nenhum hospede associado
     */
    public boolean cancelarReserva(int andar, int numero, int id) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }
        Apartamento apto = getApartamento(andar,numero);
        apto.cancelarReserva();
        estadias.remove(id);
        return true;
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

    /**
     * Cadastra um novo serviço no cardápio do hotel (REQ10).
     *
     * @param nome nome do serviço
     * @param preco preço unitário do serviço
     * @throws IllegalArgumentException se o preço não for maior que zero ou se o
     *         nome for nulo ou vazio
     *
     * @pre O hotel deve estar inicializado
     * @post Se bem-sucedido, o serviço é adicionado ao final do cardápio
     */
    public void cadastrarServico(String nome, float preco) {
        if(!precoValido(preco)){
            throw new IllegalArgumentException("Preço de serviõ inválido");
        }

        Servico servico = new Servico(nome, preco);
        this.servicos.add(servico);
    }

    /**
     * Registra o consumo de um serviço do cardápio por um apartamento (REQ10).
     *
     * @param andar Numero do andar (0 a 19)
     * @param numero Numero do apartamento no andar (0 a 13)
     * @param indiceServico posição do serviço no cardápio, a partir de 0
     * @param quantidade quantidade de unidades consumidas
     * @return true se o consumo foi registrado
     * @throws IllegalArgumentException se andar ou numero forem invalidos, se o
     *         serviço não existir no cardápio, se a quantidade não for positiva ou
     *         se o apartamento não estiver OCUPADO
     *
     * @pre O hotel deve estar inicializado, o serviço deve estar cadastrado e o
     *      apartamento deve estar OCUPADO
     * @post Se bem-sucedido, o consumo é adicionado à lista de consumos do hotel
     */
    public boolean registrarConsumo(int andar, int numero, int indiceServico, int quantidade) {
        if (!aptoValido(andar, numero)) {
            throw new IllegalArgumentException("Andar ou numero invalido");
        }

        if (!servicoValido(indiceServico)) {
            throw new IllegalArgumentException("Servico invalido");
        }

        if(!quantidadeValida(quantidade)){
            throw new IllegalArgumentException("Quantidade de consumo invalida");
        }

        if(!matriz[andar][numero].estaOcupado()){
            throw new IllegalArgumentException("Apartamento sem ocupação");
        }

        Servico servico = servicos.get(indiceServico);
        Consumo consumo = new Consumo(matriz[andar][numero], servico, quantidade);
        consumos.add(consumo);

        return true;
    }

    /**
     * Devolve os consumos de uma estadia.
     *
     * @param id número da estadia
     * @return a lista de consumos da estadia
     * @throws IllegalArgumentException se não existir estadia com o número informado
     *
     * @pre A estadia deve existir no hotel
     * @post Nenhum objeto é alterado
     */
    public List<Consumo> getConsumosDoApartamento(int id) {
        List<Consumo> listaConsumos;
        Estadia estadia = buscarEstadia(id);
        listaConsumos = estadia.getConsumos();

        return listaConsumos;
    }

    /**
     * Emite a fatura de uma estadia, com o total das diárias e dos consumos (REQ11).
     *
     * @param id número da estadia
     * @return a fatura da estadia
     * @throws IllegalArgumentException se não existir estadia com o número informado
     *
     * @pre A estadia deve existir no hotel
     * @post Nenhum objeto é alterado; a fatura é calculada a partir da estadia
     */
    public Fatura emitirFatura(int id) {
        Fatura fatura;
        Estadia estadia = buscarEstadia(id);
        fatura = new Fatura(estadia);
        return fatura;
    }

    /**
     * Localiza uma estadia pelo seu número.
     *
     * @param id número da estadia
     * @return a estadia com o número informado
     * @throws IllegalArgumentException se não existir estadia com o número informado
     *
     * @pre O hotel deve estar inicializado
     * @post Nenhum objeto é alterado
     */
    public Estadia buscarEstadia(int id){
        Estadia estadia = null;
        int indice = 0;
        while(estadia == null && indice < this.estadias.size()){
            if(id == estadias.get(indice).getIdReserva()){
                estadia = estadias.get(indice);
            }
            indice++;
        }

        if(estadia == null){
            throw new IllegalArgumentException("Estadia inexistente");
        }

        return estadia;
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

    /**
     * Devolve o cardápio de serviços do hotel.
     * @return a lista de serviços cadastrados, na ordem de cadastro
     * @pre O hotel deve estar inicializado
     * @post Nenhum objeto é alterado
     */
    public ArrayList<Servico> getServicos() { return servicos; }
    /**
     * Devolve todos os consumos registrados no hotel.
     * @return a lista de consumos, na ordem de registro
     * @pre O hotel deve estar inicializado
     * @post Nenhum objeto é alterado
     */
    public ArrayList<Consumo> getConsumos() { return consumos; }
    /**
     * Devolve todas as estadias registradas no hotel.
     * @return a lista de estadias, na ordem de criação
     * @pre O hotel deve estar inicializado
     * @post Nenhum objeto é alterado
     */
    public ArrayList<Estadia> getEstadias() { return estadias; }
}
