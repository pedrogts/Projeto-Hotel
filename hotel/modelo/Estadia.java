package hotel.modelo;

public class Estadia {
    private int idReserva;
    private Apartamento apto;
    private Hospede responsavel;
    private int dias;
    
    public Estadia(int idReserva, Apartamento apto, Hospede responsavel, int dias){
        this.idReserva = idReserva;
        this.apto = apto;
        this.responsavel = responsavel;
        this.dias = dias;
    }

    public int getIdReserva() { return idReserva; }
    public Apartamento getApto() { return apto; }
    public Hospede getResponsavel() { return responsavel; }

    public int getDias() { return dias; }

    public void alterarApartamento(Apartamento apto) {
        if(apto != null){
            this.apto = apto;
        } else {
            throw new IllegalArgumentException("Não é possível fazer alteração sem apartamento");
        }            
    }

    public void alterarResponsavel(Hospede responsavel) {
        if(responsavel != null){
            this.responsavel = responsavel;
        } else {
            throw new IllegalArgumentException("Não é possível fazer alteração sem resposável");
        }
                     
    }

    public void alterarQtdDias(int dias){
        throw new UnsupportedOperationException("Implementar alteração de quantidade de dias");
    } 

}
