package hotel.apresentacao;

import hotel.modelo.*;
import hotel.negocio.Hotel;

public class HotelTest {
    private static int passou = 0;
    private static int total = 0;

    public static void main(String[] args) {
        testarStatusTemTresValores();
        testarCheckinEmAptoLivre();
        testarCheckinComReserva();
        testarFalhaCheckinEmAptoOcupado();
        testarCheckinComHospedeNulo();
        testarCheckinArmazenaHospede();
        testarCheckinSobrescreveReserva();
        testarCheckinMostraSimboloO();
        // Exemplo:
        // testarReservarAptoLivre();
        // testarReservarAptoOcupadoFalha();
        // ...

        System.out.println(passou + "/" + total + " testes passaram");
    }

    static void testarStatusTemTresValores() {
        total++;
        if (Status.values().length == 3) {
            passou++;
        } else {
            System.out.println("FALHOU: testarStatusTemTresValores");
        }
    }

    // Exemplo de metodo de teste:
    // static void testarReservarAptoLivre() {
    //     total++;
    //     Hotel hotel = new Hotel();
    //     Hospede h = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
    //     if (hotel.reservarApartamento(0, 0, h) && hotel.getApartamento(0, 0).estaReservado()) {
    //         passou++;
    //     } else {
    //         System.out.println("FALHOU: testarReservarAptoLivre");
    //     }
    // }

    //Teste 1
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

    //Teste 2
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

    //Teste 3
    static void testarCheckinArmazenaHospede(){
        total++;

        try{
            Apartamento apto = new Apartamento();
            Hospede hosp = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");

            apto.checkin(hosp);

            if (apto.getHospede() != null && apto.getHospede().getCpf().equals("123")){
                passou++;
            } else {
                System.out.println("FALHOU: testarCheckinArmazenaHospede");
                }
            } catch (Exception e) {
                System.out.println("FALHOU: testarCheckinArmazenaHospede (excecao inesperada: " + e.getClass().getSimpleName() + ")");
            }
    }

    //Teste 4
    static void testarCheckinSobrescreveReserva(){
        total++;

        try{
            Apartamento apto = new Apartamento();
            Hospede joao = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
            Hospede maria = new Hospede("321", "Maria", "Rua Y", "8888", "maria@x");

            apto.reservar(joao);
            apto.checkin(maria);

            if (apto.getHospede() != null && apto.getHospede().getCpf().equals("321")){
                passou ++;
            } else {
                System.out.println("FALHOU: testarCheckinSobrescreveReserva");
            }
        } catch (Exception e) {
            System.out.println("FALHOU: testarCheckinSobrescreveReserva (excecao inesperada: " + e.getClass().getSimpleName() + ")");
        }
    }

    //Teste 5
    static void testarCheckinMostraSimboloO(){
        total++;

        try{
            Apartamento apto = new Apartamento();
            Hospede joao = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");

            apto.checkin(joao);

            if (apto.getSymbol() == 'O'){
                passou++;
            } else {
                System.out.println("FALHOU: testarCheckinMostraSimboloO");
            }
        } catch (Exception e) {
            System.out.println("FALHOU: testarCheckinMostraSimboloO (excecao inesperada: " + e.getClass().getSimpleName() + ")");
        }
    }

    //Teste 6
    static void testarFalhaCheckinEmAptoOcupado(){
        total ++;

        try{
            Apartamento apto = new Apartamento();
            Hospede joao = new Hospede("123", "Joao", "Rua X", "9999", "joao@x");
            Hospede maria = new Hospede("321", "Maria", "Rua Y", "8888", "maria@x");

            apto.checkin(joao);
            apto.checkin(maria);

            System.out.println("FALHOU: testarCheckinEmAptoOcupado (nenhuma excecao foi lancada)");

        } catch (IllegalStateException e) {
            passou ++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarCheckinEmAptoOcupado (esperava IllegalStateException, veio " + e.getClass().getSimpleName() + ")");
        }
    }

    //Teste 7
    static void testarCheckinComHospedeNulo(){
        total++;

        try {
            Apartamento apto = new Apartamento();

            apto.checkin(null);

            System.out.println("FALHOU: testarCheckinComHospedeNulo (nenhuma excecao foi lancada)");
        } catch (IllegalArgumentException e) {
            passou++;
        } catch (Exception e) {
            System.out.println("FALHOU: testarCheckinComHospedeNulo (esperava IllegalArgumentException, veio " + e.getClass().getSimpleName() + ")");
        }
    }

}



