package hotel.modelo;

import java.util.List;

public class Fatura {
    private Apartamento apartamento;
    private int dias;
    private List<Consumo> consumos;
    private float totalDiarias; 
    private float totalConsumos; 

    public Fatura(Apartamento apto, int dias, List<Consumo> consumos) {
        this.apartamento = apto;
        this.dias = dias;
        this.consumos = consumos;
        this.totalDiarias = getTotalDiarias();
        this.totalConsumos = getTotalConsumos(); 

    }

    public float getTotalGeral() {
        return totalDiarias + totalConsumos; 
    }

    public Apartamento getApartamento() { return apartamento; }
    public int getDias() { return dias; }
    public List<Consumo> getConsumos() { return consumos; }

    public float getTotalDiarias() {
       return dias * apartamento.getPrecoDiaria();
    }

    public float getTotalConsumos() {
        float total = 0.0f;

        if(this.consumos != null){
            for(Consumo c: consumos){
                total += c.getTotal();
            }
        }
        return total; 
    }

    @Override
    public String toString() {
        return "Fatura: R$ " + getTotalGeral();
    }
}
