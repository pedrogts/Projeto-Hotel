package hotel.modelo;

/**
 * Estados possíveis de um apartamento do hotel.
 *
 * LIVRE: disponível para reserva ou check-in direto; RESERVADO: aguardando o
 * check-in do hóspede; OCUPADO: com hóspede hospedado. As transições entre os
 * estados são controladas pela classe Apartamento.
 */
public enum Status {
    LIVRE,
    RESERVADO,
    OCUPADO;
}
