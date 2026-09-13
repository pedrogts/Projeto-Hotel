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
        if(h != null){
            this.status = Status.RESERVADO;
            this.hospede = h;
        }
    }

    /**
     * Realiza o check-in de um hospede no apartamento, alterando seu status
     * para OCUPADO. Aceita como origem tanto um apartamento LIVRE (check-in
     * direto, REQ04) quanto um RESERVADO (check-in a partir de reserva, REQ03).
     *
     * @param h Dados do hospede
     * @throws IllegalArgumentException se h for nulo
     * @throws IllegalStateException se o apartamento ja estiver OCUPADO
     *
     * @pre O apartamento não pode estar com status OCUPADO
     * @post O apartamento tera status OCUPADO
     */
    public void checkin(Hospede h) {
        if (h == null){
            throw new IllegalArgumentException("Nao e possivel fazer checkin sem hospede"); //Problema vem de fora
        } else if (status == Status.OCUPADO){
            throw new IllegalStateException("Nao e possivel fazer checkin em apartamento ocupado"); //Problema de dentro
        }
        this.hospede = h;
        this.status = Status.OCUPADO;
        //throw new UnsupportedOperationException("Implementar: LIVRE/RESERVADO -> OCUPADO");
    }
    /**
     * Realiza o checkout do apartamento, liberando para uma nova ocupação e
     * removendo o vinculo com o hospede que estava hospedado (REQ05).
     *
     * @throws IllegalStateException se o apartamento não estiver OCUPADO
     *
     * @pre O apartamento deve estar com status OCUPADO
     * @post O apartamento ficara com o status LIVRE
     */
    public void checkout() {
        if (status != Status.OCUPADO){
            throw new IllegalStateException("Nao pode fazer checkout em apartamento livre");
        }
        this.hospede = null;
        this.status = Status.LIVRE;
        //throw new UnsupportedOperationException("Implementar: OCUPADO -> LIVRE");
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
