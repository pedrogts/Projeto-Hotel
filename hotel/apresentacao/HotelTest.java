package hotel.apresentacao;

import hotel.modelo.*;
import hotel.negocio.Hotel;
public class HotelTest {
    private static int passou = 0;
    private static int total = 0;

    public static void main(String[] args) {
        testarCheckinEmAptoLivre();
        testarCheckinArmazenaHospede();
        // Exemplo:
        // testarReservarAptoLivre();
        // testarReservarAptoOcupadoFalha();
        // ...
        testarMudarAptoLivreParaReservadoComHospede();
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
}
