package hotel.apresentacao;

import hotel.modelo.*;
import hotel.negocio.Hotel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HotelTest {
    private static int passou = 0;
    private static int total = 0;

    public static void main(String[] args) {
        testarCheckinEmAptoLivre();
        testarCheckinArmazenaHospede();
        testarFalhaCheckinEmAptoOcupado();
        testarFalhaCheckinComHospedeNulo();
        testarCheckinComReserva();
        testarCheckoutLiberaApartamento();
        testarCheckoutZeraHospede();
        testarFalhaCheckoutEmAptoLivre();
        testarFalhaCheckoutEmAptoReservado();
        testarMudarAptoLivreParaReservadoComHospede();
        testarMudarAptoLivreParaReservadoSemHospede();
        testarReservarAptoJaReservado();
        testarReservarAptoOcupado();
        testarCancelarReservaFeita();
        testarCancelarReservaEmAptoLivre();
        testarCancelarReservaEmAptoOcupado();
        testarConsultarApartamentoValido();
        testarConsultarApartamentoInvalido();

        //Testes Hospede
        testarFalhaHospedeComCPFNulo();
        testarFalhaHospedeComNomeNulo();
        testarFalhaHospedeComEnderecoNulo();
        testarFalhaHospedeComCelularNulo();
        testarFalhaHospedeComEmailNulo();
        testarHospedesComMesmoCpfSaoIguais();
        testeDevePermitirAlterarHospede();

        //Teste Apartamento Simples e Premium
        testarPrecoApartamentoSimplesEPremium();
        testarHotelCriaSimplesEPremiumPorAndar();
        testarHotelReservarApto();
        testarHotelRealizarCheckinApto();
        testarHotelRealizarCheckoutApto();
        testarHotelCancelarReserva();


        //Taxa de Ocupacao do Hotel
        testarTaxaOcupacaoComHotelVazio();
        testarTaxaOcupacaoComUmApartamentoOcupado();
        testarTaxaReservasComUmApartamentoReservado();

        //Visualização do mapa
        testarHotelMostrarMapaNaoLancaExcecao();


        //Testes de integração módulo A e B
        testarHotelInicializaComMisturaDeTipos();

        CadastrarServicoHotel();
        CadastrarServicoComPrecoNaoPositivoHotel();
        testarRegistrarConsumoHotel();
        testarRegistrarConsumoNaoPositivoHotel();
        testarFalhaRegistrarConsumoComServicoInexistente();

        //Teste de Fatura
         testarCalcularTotalDiariasFatura();
         testarCalcularConsumo();
         testarCalculoTotal();

         //Testes de Servico
        testarFalhaServicoComNomeNulo();
        testarFalhaServicoComNomeVazio();
        testarFalhaConsumoComServicoNulo();
        testarFalhaConsumoComApartamentoNulo();

        //Teste de Estadia
        testarAlterarAptoEstadia();
        testarAlterarAptoComAptoNulo();
        testarAlterarResponsavelEstadia();
        testarAlterarResponsavelComResponsavelNulo();
        testarAlterarDiasEstadia();
        testarAlterarDiasEstadiaComDiaMenorQueUm();
        testarCriarListadeConsumosEstadia();

        //Teste de Hotel
        testarConsumosDoApartamento();
        testarBuscarEstadiaExistente();
        testarBuscarEstadiaInexistente();
        testarEmitirFatura();
        
        System.out.println(passou + "/" + total + " testes passaram");
    }

    static void testarCheckinEmAptoLivre() {
        total++;

        try {
            Apartamento apto = new Apartamento();
            Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");

            apto.checkin(hosp);

            if (apto.estaOcupado()) {
                passou++;
            } else {
                System.out.println("FALHOU: testarCheckinEmAptoLivre");
            }
        } catch (Exception e) {
            System.out.println("FALHOU: testarCheckinEmAptoLivre (excecao inesperada: " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarCheckinArmazenaHospede() {
        total++;

        try {
            Apartamento apto = new Apartamento();
            Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");

            apto.checkin(hosp);

            if (apto.getHospede() != null && apto.getHospede().getCpf().equals("123")) {
                passou++;
            } else {
                System.out.println("FALHOU: testarCheckinArmazenaHospede");
            }
        } catch (Exception e) {
            System.out.println("FALHOU: testarCheckinArmazenaHospede (excecao inesperada: " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarFalhaCheckinEmAptoOcupado() {
        total++;

        try {
            Apartamento apto = new Apartamento();
            Hospede joao = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
            Hospede maria = new Hospede("321", "Maria", "Rua Y", "8888", "maria@x");

            apto.checkin(joao);
            apto.checkin(maria);

            System.out.println("FALHOU: testarFalhaCheckinEmAptoOcupado (nenhuma excecao foi lancada)");
        } catch (IllegalStateException e) {
            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarFalhaCheckinEmAptoOcupado (esperava IllegalStateException, veio " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarFalhaCheckinComHospedeNulo() {
        total++;

        try {
            Apartamento apto = new Apartamento();

            apto.checkin(null);

            System.out.println("FALHOU: testarFalhaCheckinComHospedeNulo (nenhuma excecao foi lancada)");
        } catch (IllegalArgumentException e) {
            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarFalhaCheckinComHospedeNulo (esperava IllegalArgumentException, veio " + e.getClass().getSimpleName() + ")");
        }
    }


    //REQ03
    static void testarCheckinComReserva() {
        total++;

        try {
            Apartamento apto = new Apartamento();
            Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");

            apto.reservar(hosp);
            apto.checkin(hosp);

            if (apto.estaOcupado()) {
                passou++;
            } else {
                System.out.println("FALHOU: testarCheckinComReserva");
            }
        } catch (Exception e) {
            System.out.println("FALHOU: testarCheckinComReserva (excecao inesperada: " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarCheckoutLiberaApartamento() {
        total++;

        try {
            Apartamento apto = new Apartamento();
            Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");

            apto.checkin(hosp);
            apto.checkout();

            if (apto.estaLivre()) {
                passou++;
            } else {
                System.out.println("FALHOU: testarCheckoutLiberaApartamento");
            }
        } catch (Exception e) {
            System.out.println("FALHOU: testarCheckoutLiberaApartamento (excecao inesperada: " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarCheckoutZeraHospede() {
        total++;

        try {
            Apartamento apto = new Apartamento();
            Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");

            apto.checkin(hosp);
            apto.checkout();

            if (apto.getHospede() == null) {
                passou++;
            } else {
                System.out.println("FALHOU: testarCheckoutZeraHospede");
            }
        } catch (Exception e) {
            System.out.println("FALHOU: testarCheckoutZeraHospede (excecao inesperada: " + e.getClass().getSimpleName() + ")");
        }
    }


    static void testarFalhaCheckoutEmAptoLivre() {
        total++;

        try {
            Apartamento apto = new Apartamento();

            apto.checkout();

            System.out.println("FALHOU: testarFalhaCheckoutEmAptoLivre (nenhuma excecao foi lancada)");
        } catch (IllegalStateException e) {
            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarFalhaCheckoutEmAptoLivre (esperava IllegalStateException, veio " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarFalhaCheckoutEmAptoReservado() {
        total++;

        try {
            Apartamento apto = new Apartamento();
            Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");

            apto.reservar(hosp);
            apto.checkout();

            System.out.println("FALHOU: testarFalhaCheckoutEmAptoReservado (nenhuma excecao foi lancada)");
        } catch (IllegalStateException e) {
            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarFalhaCheckoutEmAptoReservado (esperava IllegalStateException, veio " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarMudarAptoLivreParaReservadoComHospede() {
        total++;
        Apartamento apto = new Apartamento();
        Hospede h = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        apto.reservar(h);
        if(apto.estaReservado() && apto.getHospede() != null){
            passou++;
        } else{
            System.out.println("FALHOU: testarMudarAptoLivreParaReservadoComHospede");
        }
    }

    static void testarMudarAptoLivreParaReservadoSemHospede() {
        total++;
        Apartamento apto = new Apartamento();
        Hospede h = null;
        try {
            apto.reservar(h);
            System.out.println("FALHOU: testarMudarAptoLivreParaReservadoSemHospede");
        } catch (IllegalArgumentException e) {
            passou++;
        }
    }

    static void testarReservarAptoJaReservado() {
        total++;
        Apartamento apto = new Apartamento();
        Hospede h = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Hospede h2 = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        try {
            apto.reservar(h);
            apto.reservar(h2);
            System.out.println("FALHOU: testarReservarAptoJaReservado - Exceção não lançada");
        } catch (IllegalStateException e) {
            if (apto.getHospede() == h && apto.estaReservado()) {
                passou++;
            } else {
                System.out.println("FALHOU: testarReservarAptoJaReservado");
            }
        }
    }

    static void testarReservarAptoOcupado() {
        total++;
        Apartamento apto = new Apartamento();
        Hospede h = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Hospede h2 = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        try {
            apto.checkin(h);
            apto.reservar(h2);
            System.out.println("FALHOU: testarReservarAptoOcupado - Exceção não lançada");
        } catch (IllegalStateException e) {
            if (apto.getHospede() == h && apto.estaOcupado()) {
                passou++;
            } else {
                System.out.println("FALHOU: testarReservarAptoOcupado");
            }
        }
    }

    static void testarCancelarReservaFeita(){
        total++;
        Apartamento apto = new Apartamento();
        Hospede h = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        apto.reservar(h);
        apto.cancelarReserva();
        if(apto.estaLivre() && apto.getHospede() == null){
            passou++;
        } else{
            System.out.println("FALHOU: testarCancelarReservaFeita");
        }
    }

    static void testarCancelarReservaEmAptoLivre(){
        total++;
        Apartamento apto = new Apartamento();
        try{
            apto.cancelarReserva();
            System.out.println("FALHOU: testarCancelarReservaEmAptoLivre - Exceção não lançada");
        } catch (IllegalStateException e) {
            if(apto.estaLivre() && apto.getHospede() == null){
                passou++;
            } else{
                System.out.println("FALHOU: testarCancelarReservaEmAptoLivre");
            }
        }
    }

    static void testarCancelarReservaEmAptoOcupado(){
        total++;
        Apartamento apto = new Apartamento();
        Hospede h = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        apto.checkin(h);
        try{
            apto.cancelarReserva();
            System.out.println("FALHOU: testarCancelarReservaEmAptoOcupado - Exceção não lançada");
        } catch (IllegalStateException e) {
            if(apto.estaOcupado() && apto.getHospede() != null){
                passou++;
            } else{
                System.out.println("FALHOU: testarCancelarReservaEmAptoOcupado");
            }
        }
    }

    static void testarFalhaHospedeComCPFNulo(){
        total++;
        try {
            new Hospede( null, "Joao", "Rua X", "9999", "joao@x");
            System.out.println("FALHOU: testarFalhaHospedeComCPFNulo - Exceção não lançada");
        } catch (IllegalArgumentException e) {
            passou ++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarFalhaHospedeComCpfNulo (esperava IllegalArgumentException, veio " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarFalhaHospedeComNomeNulo() {
        total++;
        try {
            new Hospede("123", null, "Rua X", "9999", "joao@x");
            System.out.println("FALHOU: testarFalhaHospedeComNomeNulo - Exceção não lançada");
        } catch (IllegalArgumentException e) {
            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarFalhaHospedeComCpfNulo (esperava IllegalArgumentException, veio " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarFalhaHospedeComEnderecoNulo() {
        total++;
        try {
            new Hospede("123", "Joao", null, "9999", "joao@x");
            System.out.println("FALHOU: testarFalhaHospedeComNomeNulo - Exceção não lançada");
        } catch (IllegalArgumentException e) {
            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarFalhaHospedeComEnderecoNulo (esperava IllegalArgumentException, veio " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarFalhaHospedeComCelularNulo() {
        total++;
        try {
            new Hospede("123", "Joao", "Rua X", null, "joao@x");
            System.out.println("FALHOU: testarFalhaHospedeComCelularNulo (nenhuma excecao foi lancada)");
        } catch (IllegalArgumentException e) {
            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarFalhaHospedeComCelularNulo (esperava IllegalArgumentException, veio " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarFalhaHospedeComEmailNulo() {
        total++;
        try {
            new Hospede("123", "Joao", "Rua X", "9999", null);
            System.out.println("FALHOU: testarFalhaHospedeComEmailNulo (nenhuma excecao foi lancada)");
        } catch (IllegalArgumentException e) {
            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarFalhaHospedeComEmailNulo (esperava IllegalArgumentException, veio " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarHospedesComMesmoCpfSaoIguais() {
        total++;
        try {
            Hospede a = new Hospede("123", "Joao Silva", "Rua A", "9999", "joao@x");
            Hospede b = new Hospede("123", "Joao S.", "Rua Z", "0000", "outro@x");

            if (a.equals(b)) {
                passou++;
            } else {
                System.out.println("FALHOU: testarHospedesComMesmoCpfSaoIguais");
            }
        } catch (Exception e) {
            System.out.println("FALHOU: testarHospedesComMesmoCpfSaoIguais (excecao inesperada: " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testeDevePermitirAlterarHospede() {
        total++;

        Hospede h = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");

        h.setCpf("456");
        h.setNome("Maria");
        h.setEndereco("Rua Y");
        h.setCelular("8888");
        h.setEmail("maria@x");

        if (h.getCpf().equals("456")
                && h.getNome().equals("Maria")
                && h.getEndereco().equals("Rua Y")
                && h.getCelular().equals("8888")
                && h.getEmail().equals("maria@x")) {
            passou++;
        } else {
            System.out.println("FALHOU: testeDevePermitirAlterarHospede");
        }
    }

    static void testarConsultarApartamentoValido() {
        total++;
        try {
            Hotel hotel = new Hotel();

            System.out.println("--- Início da saída esperada no console ---");
            hotel.consultarApartamento(0, 0);
            System.out.println("--- Fim da saída esperada no console ---");

            passou++; // O teste passou
        } catch (Exception e) {
            System.out.println("FALHOU: testarConsultarApartamentoValido - Lançou exceção inesperada: " + e.getMessage());
        }
    }

    static void testarConsultarApartamentoInvalido() {
        total++;
        try {
            Hotel hotel = new Hotel();

            hotel.consultarApartamento(20, 0);

            System.out.println("FALHOU: testarConsultarApartamentoInvalido - Deveria ter lançado IllegalArgumentException.");
        } catch (IllegalArgumentException e) {

            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarConsultarApartamentoInvalido - Lançou o tipo errado de exceção: " + e.getClass().getSimpleName());
        }
    }

    static void testarPrecoApartamentoSimplesEPremium(){
        total++;
        Apartamento aptoSimples = new ApartamentoSimples();
        Apartamento aptoPremium = new ApartamentoPremium();

        float precoSimples = aptoSimples.getPrecoDiaria();
        float precoPremium = aptoPremium.getPrecoDiaria();

        if (precoSimples == 150.0f && precoPremium == 350.0f) {
            passou++;
        } else {
            System.out.println("FALHOU: testarPrecoApartamentoSimplesEPremium");
        }
    }

    static void testarHotelCriaSimplesEPremiumPorAndar() {
        total++;

        try {
            Hotel hotel = new Hotel();

            boolean ok = hotel.getApartamento(0, 0).getPrecoDiaria() == 150f
                    && hotel.getApartamento(0, 7).getPrecoDiaria() == 150f
                    && hotel.getApartamento(0, 8).getPrecoDiaria() == 350f
                    && hotel.getApartamento(0, 13).getPrecoDiaria() == 350f;

            if (ok) {
                passou++;
            } else {
                System.out.println("FALHOU: testarHotelCriaSimplesEPremiumPorAndar");
            }
        } catch (Exception e) {
            System.out.println("FALHOU: testarHotelCriaSimplesEPremiumPorAndar (excecao inesperada: " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarHotelReservarApto(){
        total++;
        Hotel hotel = new Hotel();
        ArrayList<Estadia> estadias;
        Hospede h = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        hotel.reservarApartamento(0,0,h, 1);
        Apartamento apto = hotel.getApartamento(0,0);
        estadias = hotel.getEstadias();
        if(apto.estaReservado() && apto.getHospede() != null && apto.getPrecoDiaria() == 150f && !estadias.isEmpty()){
            passou++;
        } else{
            System.out.println("FALHOU: testarHotelReservarAptoSimples");
        }
    }

    static void testarHotelRealizarCheckinApto(){
        total++;
        Hotel hotel = new Hotel();
        Hospede h = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        hotel.realizarCheckin(0,0, h, 2);
        Apartamento apto = hotel.getApartamento(0,0);
        ArrayList<Estadia> estadias = hotel.getEstadias();

        if (apto.estaOcupado() && apto.getHospede().equals(h) && estadias.size() == 1 && estadias.get(0).getDias() == 2) {
            passou++;
        } else {
            System.out.println("FALHOU: testarHotelRealizarCheckinApto");
        }
    }

    static void testarHotelRealizarCheckoutApto(){
        total++;
        Hotel hotel = new Hotel();
        Hospede h = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        hotel.realizarCheckin(0, 0, h, 1);
        hotel.realizarCheckout(0, 0);
        Apartamento apto = hotel.getApartamento(0, 0);

        if (apto.estaLivre() && apto.getHospede() == null) {
            passou++;
        } else {
            System.out.println("FALHOU: testarHotelRealizarCheckoutApto");
        }
    }

    static void testarHotelCancelarReserva(){
        total++;
        Hotel hotel = new Hotel();
        ArrayList<Estadia> estadias;
        Hospede h = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        boolean sucesso_cancelar;
        hotel.reservarApartamento(0,0,h, 1);
        sucesso_cancelar = hotel.cancelarReserva(0,0, 0);
        Apartamento apto = hotel.getApartamento(0,0);
        estadias = hotel.getEstadias();
        if(sucesso_cancelar && apto.estaLivre() && apto.getHospede() == null && estadias.isEmpty()){
            passou++;
        } else{
            System.out.println("FALHOU: testarHotelCancelarReserva");
        }
    }

    static void testarTaxaOcupacaoComHotelVazio() {
        total++;

        try {
            Hotel hotel = new Hotel();

            if (hotel.calcularTaxaOcupacao() == 0f) {
                passou++;
            } else {
                System.out.println("FALHOU: testarTaxaOcupacaoComHotelVazio");
            }
        } catch (Exception e) {
            System.out.println("FALHOU: testarTaxaOcupacaoComHotelVazio (excecao inesperada: " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarTaxaOcupacaoComUmApartamentoOcupado() {
        total++;

        try {
            Hotel hotel = new Hotel();
            hotel.realizarCheckin(0, 0, new Hospede("123", "Joao", "Rua X", "9999", "joao@x"),1);

            if (hotel.calcularTaxaOcupacao() == 1f / 280f) {
                passou++;
            } else {
                System.out.println("FALHOU: testarTaxaOcupacaoComUmApartamentoOcupado");
            }
        } catch (Exception e) {
            System.out.println("FALHOU: testarTaxaOcupacaoComUmApartamentoOcupado (excecao inesperada: " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarTaxaReservasComUmApartamentoReservado() {
        total++;

        try {
            Hotel hotel = new Hotel();
            hotel.reservarApartamento(0, 0, new Hospede("123", "Joao", "Rua X", "9999", "joao@x"), 1);

            if (hotel.calcularTaxaReservas() == 1f / 280f) {
                passou++;
            } else {
                System.out.println("FALHOU: testarTaxaReservasComUmApartamentoReservado");
            }
        } catch (Exception e) {
            System.out.println("FALHOU: testarTaxaReservasComUmApartamentoReservado (excecao inesperada: " + e.getClass().getSimpleName() + ")");
        }
    }

    static void testarHotelMostrarMapaNaoLancaExcecao(){
        total++;
        try {
            Hotel hotel = new Hotel();
            hotel.mostrarMapa();
            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarHotelMostrarMapaNaoLancaExcecao (" + e.getMessage() + ")");
        }
    }

    static void testarHotelInicializaComMisturaDeTipos() {
        total++;
        Hotel hotel = new Hotel();
        int simples = 0, premium = 0;
        for (int a = 0; a < Hotel.NUM_ANDARES; a++) {
            for (int n = 0; n < Hotel.APTOS_POR_ANDAR; n++) {
                Apartamento apto = hotel.getApartamento(a, n);
                if (apto instanceof ApartamentoSimples) simples++;
                else if (apto instanceof ApartamentoPremium) premium++;
            }
        }
        int esperadoSimples = 8 * Hotel.NUM_ANDARES;
        int esperadoPremium = 6 * Hotel.NUM_ANDARES;
        if (simples == esperadoSimples && premium == esperadoPremium) {
            passou++;
        } else {
            System.out.println("FALHOU: testarHotelInicializaComMisturaDeTipos (simples="
                    + simples + ", premium=" + premium + ")");
        }
    }

    static void CadastrarServicoHotel(){
        total++;
        Hotel hotel = new Hotel();
        List<Servico> servicos;
        String nome = "s1";
        float preco = 10;
        try{
            hotel.cadastrarServico(nome, preco);
            servicos = hotel.getServicos();
            if(servicos != null && servicos.size() == 1){
                passou++;
            } else{
                System.out.println("FALHOU: CadastrarServicoHotel");
            }
        } catch (Exception e){
            System.out.println("FALHOU: CadastrarServicoHotel");
        }
    }

    static void CadastrarServicoComPrecoNaoPositivoHotel(){
        total++;
        Hotel hotel = new Hotel();
        String nome = "s1";
        float preco = -1;
        try{
            hotel.cadastrarServico(nome, preco);
            System.out.println("FALHOU: CadastrarServicoComPrecoNegativoHotel");
        } catch (IllegalArgumentException e) {
            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: CadastrarServicoComPrecoNegativoHotel");
        }
    }

    static void testarRegistrarConsumoHotel(){
        total++;
        Hotel hotel = new Hotel();
        String nome_servico = "s1";
        float preco_servico = 10;
        int quantidade = 1;
        hotel.cadastrarServico(nome_servico, preco_servico);
        int numero = 0;
        int andar = 0;
        List<Consumo> consumos;
        boolean ok;

        try{
            ok = hotel.registrarConsumo(andar, numero, 0, quantidade);
            consumos = hotel.getConsumos();
            if(ok && consumos.size() == 1){
                passou++;
            } else{
                System.out.println("FALHOU: testarRegistrarConsumoHotel");
            }
        } catch(Exception e){
            System.out.println("FALHOU: testarRegistrarConsumoHotel");
        }
            
    }

    static void testarRegistrarConsumoNaoPositivoHotel(){
        total++;
        Hotel hotel = new Hotel();
        String nome_servico = "s1";
        float preco_servico = 10;
        int quantidade = -1;
        hotel.cadastrarServico(nome_servico, preco_servico);
        int numero = 0;
        int andar = 0;
        boolean ok;
        try{
            ok = hotel.registrarConsumo(andar, numero, 0, quantidade);
            System.out.println("FALHOU: testarRegistrarConsumoNaoPositivoHotel");
        } catch (IllegalArgumentException e){
            passou++;
        } catch (Exception e){
            System.out.println("FALHOU: testarRegistrarConsumoNaoPositivoHotel");
        }

    }

    static void testarCalcularTotalDiariasFatura() {
        total++;
        float totalDiarias;
        Apartamento apto = new ApartamentoSimples();
        Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Estadia estadia = new Estadia(1, apto, hosp, 1);
        Fatura fatura = new Fatura(estadia);
        try {
            totalDiarias = fatura.getTotalDiarias();
            if(totalDiarias == 150){
                passou++;
            }else{
                System.out.println("FALHOU: testarCalcularTotalDiariasFatura");    
            }
            
        } catch (Exception e) {
            System.out.println("FALHOU: testarCalcularTotalDiariasFatura");
        }
    }

    static void testarCalcularConsumo(){
        total++; 
        float totalConsumo;
        Apartamento apto = new ApartamentoSimples();
        Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Estadia estadia = new Estadia(1, apto, hosp, 1);
        List<Consumo> listaConsumo = estadia.getConsumos();

        Servico agua = new Servico("agua", 5.0f);
        Servico lanche = new Servico("Lanche", 25.0f);

        Consumo consumo1 = new Consumo(apto, agua, 2);
        Consumo consumo2 = new Consumo(apto, lanche, 1);
        
        listaConsumo.add(consumo1); 
        listaConsumo.add(consumo2); 

        Fatura fatura = new Fatura(estadia);
        
        try {
            totalConsumo = fatura.getTotalConsumos(); 
            if(totalConsumo == 35.0f){
                passou++;
            }else{
                System.out.println("FALHOU: testarCalcularConsumo");
            }
        } catch (Exception e) {
            System.out.println("FALHOU: testarCalcularConsumo");
        }
        
    }

    static void testarCalculoTotal(){
        total++; 
        Apartamento apto = new ApartamentoSimples();
        Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Estadia estadia = new Estadia(1, apto, hosp, 1);
        List<Consumo> listaConsumo = estadia.getConsumos();
        float totalGeral; 

        Servico agua = new Servico("agua", 5.0f);
        Servico lanche = new Servico("Lanche", 25.0f);

        Consumo consumo1 = new Consumo(apto, agua, 2);
        Consumo consumo2 = new Consumo(apto, lanche, 1);
        
        listaConsumo.add(consumo1); 
        listaConsumo.add(consumo2);
        
        Fatura fatura = new Fatura(estadia);

        try {
            totalGeral = fatura.getTotalGeral();
            if(totalGeral == 185.0f){
                passou++; 
            }else{
                System.out.println("FALHOU: testarCalculoTotal");
            }
            
        } catch (Exception e) {
            System.out.println("FALHOU: testarCalculoTotal");
        }
    }

    static void testarAlterarAptoEstadia(){
        total++;
        Apartamento apto = new Apartamento();
        Apartamento apto2 = new Apartamento();
        Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Estadia estadia = new Estadia(1, apto, hosp, 1);
        try{
            estadia.alterarApartamento(apto2);
            if(estadia.getApto().equals(apto2)){
                passou++;
            } else{
              System.out.println("FALHOU: testarAlterarAptoEstadia");  
            }
        } catch(Exception e){
            System.out.println("FALHOU: testarAlterarAptoEstadia");
        }
    }

    static void testarAlterarAptoComAptoNulo(){
        total++;
        Apartamento apto = new Apartamento();
        Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Estadia estadia = new Estadia(1, apto, hosp, 1);
        try{
          estadia.alterarApartamento(null);
          System.out.println("FALHOU: testarAlterarAptoComAptoNulo");
        } catch(IllegalArgumentException e){
            passou++;
        } catch(Exception e){
          System.out.println("FALHOU: testarAlterarAptoComAptoNulo - exceção IllegalArgumentException não lançada");
        }
    }

    static void testarAlterarResponsavelEstadia(){
        total++;
        Apartamento apto = new Apartamento();
        Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Hospede hosp2 = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Estadia estadia = new Estadia(1, apto, hosp, 1);
        try{
            estadia.alterarResponsavel(hosp2);
            if(estadia.getResponsavel().equals(hosp2)){
                passou++;
            } else{
              System.out.println("FALHOU: testarAlterarResponsavelEstadia");  
            }
        } catch(Exception e){
            System.out.println("FALHOU: testarAlterarResponsavelEstadia");
        }
    }

    static void testarAlterarResponsavelComResponsavelNulo(){
        total++;
        Apartamento apto = new Apartamento();
        Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Estadia estadia = new Estadia(1, apto, hosp, 1);
        try{
          estadia.alterarResponsavel(null);
          System.out.println("FALHOU: testarAlterarResponsavelComResponsavelNulo");
        } catch(IllegalArgumentException e){
            passou++;
        } catch(Exception e){
          System.out.println("FALHOU: testarAlterarResponsavelComResponsavelNulo - exceção IllegalArgumentException não lançada");
        }
    }

    static void testarAlterarDiasEstadia(){
        total++;
        Apartamento apto = new Apartamento();
        Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Estadia estadia = new Estadia(1, apto, hosp, 1);
        try{
            estadia.alterarQtdDias(2);
            if(estadia.getDias() == 2){
                passou++;
            } else{
              System.out.println("FALHOU: testarAlterarDiasEstadia");  
            }
        } catch(Exception e){
            System.out.println("FALHOU: testarAlterarDiasEstadia");
        }
    }

    static void testarAlterarDiasEstadiaComDiaMenorQueUm(){
        total++;
        Apartamento apto = new Apartamento();
        Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Estadia estadia = new Estadia(1, apto, hosp, 1);
        try{
            estadia.alterarQtdDias(0);
            System.out.println("FALHOU: testarAlterarDiasEstadiaComDiaMenorQueUm");  
        } catch(IllegalArgumentException e){
            passou++;
        } catch(Exception e){
            System.out.println("FALHOU: testarAlterarDiasEstadiaComDiaMenorQueUm");
        }
    }

    static void testarCriarListadeConsumosEstadia(){
        total++;
        Apartamento apto = new Apartamento();
        Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Estadia estadia = new Estadia(1, apto, hosp, 1);
        try{
            List<Consumo> consumos = estadia.getConsumos();
            if(consumos != null && consumos.isEmpty()){
                passou++;
            } else{
                System.out.println("FALHOU: testarCriarListadeConsumosEstadia");
            }
        } catch (Exception e){
            System.out.println("FALHOU: testarCriarListadeConsumosEstadia");
        }
    }

    static void testarBuscarEstadiaExistente(){
        total++;
        Hotel hotel = new Hotel();
        List<Estadia> estadias = hotel.getEstadias();
        Apartamento apto = new Apartamento();
        Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Estadia estadia = new Estadia(1, apto, hosp, 1);
        estadias.add(estadia);
        Estadia estadia_existente;
        
        try{
            estadia_existente = hotel.buscarEstadia(1);
            if(estadia_existente != null && estadia_existente.getIdReserva() == 1){
                passou++;
            } else{
                System.out.println("FALHOU: testarBuscarEstadiaExistente");
            }
        } catch (Exception e){
            System.out.println("FALHOU: testarBuscarEstadiaExistente");
        }
    }

    static void testarBuscarEstadiaInexistente(){
        total++;
        Hotel hotel = new Hotel();
        List<Estadia> estadias = hotel.getEstadias();
        Apartamento apto = new Apartamento();
        Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Estadia estadia = new Estadia(1, apto, hosp, 1);
        estadias.add(estadia);
        Estadia estadia_existente;
        
        try{
            estadia_existente = hotel.buscarEstadia(2);
            System.out.println("FALHOU: testarBuscarEstadiaInexistente");
        } catch(IllegalArgumentException e){
            passou++;
        } catch (Exception e){
            System.out.println("FALHOU: testarBuscarEstadiaInexistente");
        }
    }

    static void testarConsumosDoApartamento(){
        total++;
        Hotel hotel = new Hotel();
        List<Estadia> estadias = hotel.getEstadias();
        Apartamento apto = new Apartamento();
        Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Estadia estadia = new Estadia(1, apto, hosp, 1);
        estadias.add(estadia);

        List<Consumo> consumo_apartamento;

        try{
            consumo_apartamento = hotel.getConsumosDoApartamento(1);
            if(consumo_apartamento != null && consumo_apartamento.equals(estadia.getConsumos())){
                passou++;
            } else{
                System.out.println("FALHOU: testarConsumosDoApartamento");    
            }
        } catch (Exception e){
            System.out.println("FALHOU: testarConsumosDoApartamento");
        }

    }

    static void testarEmitirFatura(){
        total++;
        Hotel hotel = new Hotel();
        List<Estadia> estadias = hotel.getEstadias();
        Apartamento apto = new Apartamento();
        Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
        Estadia estadia = new Estadia(1, apto, hosp, 1);
        estadias.add(estadia);

        Fatura fatura;

        try{
            fatura = hotel.emitirFatura(1);
            if(fatura != null){
                passou++;
            } else{
                System.out.println("FALHOU: testarEmitirFatura");    
            }
        } catch (Exception e){
            System.out.println("FALHOU: testarEmitirFatura");
        }

    }

    static void testarFalhaServicoComNomeNulo() {
        total++;
        try {
            new Servico(null, 5.0f);
            System.out.println("FALHOU: testarFalhaServicoComNomeNulo");
        } catch (IllegalArgumentException e) {
            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarFalhaServicoComNomeNulo - exceção errada");
        }
    }

    static void testarFalhaServicoComNomeVazio() {
        total++;
        try {
            new Servico("   ", 5.0f);
            System.out.println("FALHOU: testarFalhaServicoComNomeVazio");
        } catch (IllegalArgumentException e) {
            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarFalhaServicoComNomeVazio - exceção errada");
        }
    }

    static void testarFalhaConsumoComServicoNulo() {
        total++;
        Apartamento apto = new ApartamentoSimples();
        try {
            new Consumo(apto, null, 2);
            System.out.println("FALHOU: testarFalhaConsumoComServicoNulo");
        } catch (IllegalArgumentException e) {
            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarFalhaConsumoComServicoNulo - exceção errada");
        }
    }

    static void testarFalhaConsumoComApartamentoNulo() {
        total ++;
        Servico agua = new Servico("agua", 5.0f);
        try {
            new Consumo (null, agua, 2);
            System.out.println("FALHOU: NOME");
        } catch (IllegalArgumentException e) {
            passou ++;
        } catch (Exception e) {
            System.out.println("FALHOU: NOME - excecao errada");
        }
    }

    static void testarFalhaRegistrarConsumoComServicoInexistente() {
        total++;
        Hotel hotel = new Hotel();
        try {
            hotel.registrarConsumo(0, 0, 0, 1);
            System.out.println("FALHOU: testarFalhaRegistrarConsumoComServicoInexistente");
        } catch (IllegalArgumentException e) {
            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarFalhaRegistrarConsumoComServicoInexistente - exceção errada");
        }
    }
}
