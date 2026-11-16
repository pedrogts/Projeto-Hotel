package hotel.servico;

import hotel.modelo.*;
import hotel.negocio.Hotel;
import java.util.*;

public class RelatorioSvc {
    private Hotel hotel;

    public RelatorioSvc(Hotel hotel) {
        this.hotel = hotel;
    }

    public int[][] calcularDiasOcupados() {
        int[][] dias = new int[Hotel.NUM_ANDARES][Hotel.APTOS_POR_ANDAR];
        for (Estadia estadia : hotel.getEstadias()) {
            for (int a = 0; a < Hotel.NUM_ANDARES; a++) {
                for (int n = 0; n < Hotel.APTOS_POR_ANDAR; n++) {
                    if (hotel.getApartamento(a, n) == estadia.getApto()) {
                        dias[a][n] += estadia.getDias();
                    }
                }
            }
        }
        return dias;
    }

    public void gerarRelatorioOcupacao() {
        int[][] dias = calcularDiasOcupados();
        System.out.println("=== RELATORIO DE OCUPACAO ===");
        for (int a = 0; a < Hotel.NUM_ANDARES; a++) {
            for (int n = 0; n < Hotel.APTOS_POR_ANDAR; n++) {
                if (dias[a][n] > 0) {
                    System.out.println("Andar " + a + ", Apto " + n + ": " + dias[a][n] + " dia(s)");
                }
            }
        }
    }

    public void gerarRankingServicos() {
        throw new UnsupportedOperationException("Implementar ranking de servicos");
    }

    public void gerarResumoFinanceiro() {
        throw new UnsupportedOperationException("Implementar resumo financeiro");
    }
}
