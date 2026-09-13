package hotel.modelo;

public class Apartamento {
    private Status status;
    private Hospede hospede;

    public Apartamento() {
        this.status = Status.LIVRE;
        this.hospede = null;
    }

    public Status getStatus() { return status; }
    public Hospede getHospede() { return hospede; }

    /**
     * Reserva o apartamento, alterando seu status de LIVRE para RESERVADO
     * e associando o hóspede à reserva.
     *
     * @param h Dados do hóspede que fará a reserva
     *
     * @pre O apartamento deve possuir número e andar válidos
     * @post Se bem-sucedido, o apartamento terá o status RESERVADO e o hospede será armazenado
     */
    public void reservar(Hospede h) {
        this.status = Status.RESERVADO;
        this.hospede = h;
    }

    public void checkin(Hospede h) {
        this.hospede = h;
        this.status = Status.OCUPADO;
        //throw new UnsupportedOperationException("Implementar: LIVRE/RESERVADO -> OCUPADO");
    }

    public void checkout() {
        throw new UnsupportedOperationException("Implementar: OCUPADO -> LIVRE");
    }

    public void cancelarReserva() {
        throw new UnsupportedOperationException("Implementar: RESERVADO -> LIVRE");
    }

    public boolean estaLivre() { return status == Status.LIVRE; }
    public boolean estaReservado() { return status == Status.RESERVADO; }
    public boolean estaOcupado() { return status == Status.OCUPADO; }

    public float getPrecoDiaria() { return 0f; }

    public char getSymbol() {
        switch (status) {
            case LIVRE: return '.';
            case RESERVADO: return 'R';
            case OCUPADO: return 'O';
            default: return '?';
        }
    }
}
