package hotel.servico;

import hotel.modelo.*;
import hotel.negocio.Hotel;
import java.util.*;

/**
 * Gera os relatórios gerenciais do hotel (REQ14): histórico de ocupação,
 * ranking de serviços mais consumidos e resumo financeiro. Apenas lê os dados
 * do hotel, sem alterá-los.
 */
public class RelatorioSvc {
    private Hotel hotel;

    /**
     * Cria o serviço de relatórios para um hotel.
     *
     * @param hotel hotel cujos dados serão usados nos relatórios
     *
     * @pre O hotel deve estar inicializado
     * @post O serviço fica associado ao hotel informado
     */
    public RelatorioSvc(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Calcula quantos dias cada apartamento ficou ocupado, somando os dias de
     * todas as estadias registradas no hotel.
     *
     * @return matriz [andar][número] com o total de dias de cada apartamento
     *
     * @pre O hotel deve estar inicializado
     * @post Nenhum objeto é alterado
     */
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

    /**
     * Exibe o histórico de ocupação (REQ14): total de dias de cada apartamento,
     * listando apenas os apartamentos que tiveram estadias.
     *
     * @pre O hotel deve estar inicializado
     * @post O relatório é exibido no terminal; nenhum objeto é alterado
     */
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

    /**
     * Exibe o ranking de serviços mais consumidos (REQ14), em ordem decrescente de
     * unidades consumidas. Se não houver consumos, informa que nenhum foi registrado.
     *
     * @pre O hotel deve estar inicializado
     * @post O ranking é exibido no terminal; nenhum objeto é alterado
     */
    public void gerarRankingServicos() {
        System.out.println("=== RANKING DE SERVICOS ===");

        Map<String, Integer> quantidades = new HashMap<>();
        for (Consumo c : hotel.getConsumos()) {
            quantidades.merge(c.getServico().getNome(), c.getQuantidade(), Integer::sum);
        }

        if (quantidades.isEmpty()) {
            System.out.println("Nenhum consumo registrado.");
            return;
        }

        List<Map.Entry<String, Integer>> ranking = new ArrayList<>(quantidades.entrySet());
        ranking.sort((x, y) -> y.getValue().compareTo(x.getValue()));

        int posicao = 1;
        for (Map.Entry<String, Integer> e : ranking) {
            System.out.println(posicao++ + ". " + e.getKey() + " - " + e.getValue() + " unidade(s)");
        }
    }

    /**
     * Exibe o resumo financeiro do hotel: total das diárias das estadias, total
     * dos consumos e faturamento total estimado.
     *
     * @pre O hotel deve estar inicializado
     * @post O resumo é exibido no terminal; nenhum objeto é alterado
     */
    public void gerarResumoFinanceiro() {
        float totalDiarias = 0f;
        for (Estadia estadia : hotel.getEstadias()) {
            totalDiarias += estadia.getDias() * estadia.getApto().getPrecoDiaria();
        }

        float totalConsumos = 0f;
        for (Consumo c : hotel.getConsumos()) {
            totalConsumos += c.getTotal();
        }

        System.out.println("=== RESUMO FINANCEIRO ===");
        System.out.printf("Total de diarias:  R$ %.2f%n", totalDiarias);
        System.out.printf("Total de consumos: R$ %.2f%n", totalConsumos);
        System.out.printf("Faturamento total: R$ %.2f%n", totalDiarias + totalConsumos);
    }
}
