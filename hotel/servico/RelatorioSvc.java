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
        throw new UnsupportedOperationException("Implementar calcularDiasOcupados");
    }

    public void gerarRelatorioOcupacao() {
        throw new UnsupportedOperationException("Implementar relatorio de ocupacao");
    }

    public void gerarRankingServicos() {
        throw new UnsupportedOperationException("Implementar ranking de servicos");
    }

    public void gerarResumoFinanceiro() {
        throw new UnsupportedOperationException("Implementar resumo financeiro");
    }
}
