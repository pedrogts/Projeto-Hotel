package hotel.modelo;

import java.util.List;

public class Fatura {
    private float totalDiarias; 
    private float totalConsumos; 
    private Estadia estadia;

    public Fatura(Estadia estadia){
        this.estadia = estadia;
    }

    public float getTotalGeral() {
        totalDiarias = getTotalDiarias();
        totalConsumos = getTotalConsumos(); 
        return totalDiarias + totalConsumos; 
    }

    public Estadia getEstadia() { return estadia; }

    public float getTotalDiarias() { 
        int dias = estadia.getDias();
        Apartamento apto = estadia.getApto();
        float precoDiaria = apto.getPrecoDiaria();
        return dias * precoDiaria;
    }

    public float getTotalConsumos() {
        float total = 0.0f;
        List<Consumo> Listaconsumos = estadia.getConsumos();

        if(Listaconsumos != null){
            for(Consumo c: Listaconsumos){
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