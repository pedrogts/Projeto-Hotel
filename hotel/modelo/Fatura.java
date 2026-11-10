package hotel.modelo;

import java.util.List;

public class Fatura {
    private Apartamento apartamento;
    private int dias;
    private List<Consumo> consumos;
    private float totalDiarias; 
    private float totalConsumos; 
    private Estadia estadia;

    public Fatura(Apartamento apto, int dias, List<Consumo> consumos) {
        this.apartamento = apto;
        this.dias = dias;
        this.consumos = consumos;
    }

    public Fatura(Estadia estadia){
        this.estadia = estadia;
    }

    public float getTotalGeral() {
        totalDiarias = getTotalDiarias();
        totalConsumos = getTotalConsumos(); 
        return totalDiarias + totalConsumos; 
    }

    public Apartamento getApartamento() { return apartamento; }
    public int getDias() { return dias; }
    public List<Consumo> getConsumos() { return consumos; }

    public float getTotalDiarias() { 
        if(estadia != null){
            int dias = estadia.getDias();
            Apartamento apto = estadia.getApto();
            float precoDiaria = apto.getPrecoDiaria();
            return dias * precoDiaria;
        }
         
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
