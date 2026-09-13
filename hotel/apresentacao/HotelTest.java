package hotel.apresentacao;

import hotel.modelo.*;
import hotel.negocio.Hotel;
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
        // Exemplo:
        // testarReservarAptoLivre();
        // testarReservarAptoOcupadoFalha();
        // ...
        testarMudarAptoLivreParaReservadoComHospede();
        testarMudarAptoLivreParaReservadoSemHospede();
        testarReservarAptoJaReservado();
        testarReservarAptoOcupado();
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
}
