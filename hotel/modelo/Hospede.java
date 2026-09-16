package hotel.modelo;

import java.io.Serializable;

/**
 * Representa um hóspede do hotel, com os dados de cadastro exigidos pelo REQ08.
 *
 * A classe é imutável: os cinco campos são definidos no construtor e não podem
 * ser alterados depois. A identidade de um hóspede é o seu CPF — é ele que
 * equals e hashCode utilizam.
 */
public class Hospede implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String cpf;
    private final String nome;
    private final String endereco;
    private final String celular;
    private final String email;

    /**
     * Cria um hóspede com todos os dados de cadastro preenchidos (REQ08).
     * O objeto é imutável: uma vez criado, nenhum campo pode ser alterado.
     *
     * @param cpf CPF do hóspede, que é sua identidade no sistema
     * @param nome nome completo do hóspede
     * @param endereco endereço residencial do hóspede
     * @param celular telefone celular para contato
     * @param email endereço de e-mail para contato
     * @throws IllegalArgumentException se qualquer um dos cinco campos for nulo,
     *         vazio ou composto apenas por espaços em branco
     *
     * @pre Os cinco campos devem conter texto não vazio
     * @post O hóspede fica criado com os cinco campos armazenados, e não poderá
     *       ser alterado depois
     */
    public Hospede(String cpf, String nome, String endereco, String celular, String email) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("Campo obrigatório : CPF");
        } else if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Campo obrigatório : Nome");
        } else if (endereco == null || endereco.isBlank()) {
            throw new IllegalArgumentException("Campo obrigatório : Endereco");
        } else if (celular == null || celular.isBlank()) {
            throw new IllegalArgumentException("Campo obrigatório : Celular");
        } else if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Campo obrigatório : E-mail");
        }
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.celular = celular;
        this.email = email;
    }

    /**
     * Devolve o CPF do hóspede, que é sua identidade no sistema.
     * @return o CPF informado na criação
     * @pre Nenhuma
     * @post Nenhum objeto é alterado
     */
    public String getCpf() { return cpf; }

    /**
     * Devolve o nome completo do hóspede.
     * @return o nome informado na criação
     * @pre Nenhuma
     * @post Nenhum objeto é alterado
     */
    public String getNome() { return nome; }

    /**
     * Devolve o endereço residencial do hóspede.
     * @return o endereço informado na criação
     * @pre Nenhuma
     * @post Nenhum objeto é alterado
     */
    public String getEndereco() { return endereco; }

    /**
     * Devolve o telefone celular de contato do hóspede.
     * @return o celular informado na criação
     * @pre Nenhuma
     * @post Nenhum objeto é alterado
     */
    public String getCelular() { return celular; }

    /**
     * Devolve o e-mail de contato do hóspede.
     * @return o e-mail informado na criação
     * @pre Nenhuma
     * @post Nenhum objeto é alterado
     */
    public String getEmail() { return email; }

    /**
     * Devolve a representação textual do hóspede, no formato "nome (CPF: xxx)",
     * usada na exibição de apartamentos ocupados ou reservados.
     *
     * @return texto com o nome e o CPF do hóspede
     *
     * @pre Nenhuma
     * @post Nenhum objeto é alterado
     */
    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }

    /**
     * Compara este hóspede com outro objeto. Dois hóspedes são considerados
     * iguais quando possuem o mesmo CPF, independentemente dos demais dados —
     * a mesma pessoa continua sendo a mesma após mudar de endereço ou telefone.
     *
     * @param o objeto a ser comparado com este hóspede
     * @return true se o objeto for um Hospede com o mesmo CPF; false caso
     *         contrário, inclusive quando o objeto for nulo ou de outro tipo
     *
     * @pre Nenhuma — o método aceita null e objetos de qualquer tipo
     * @post Nenhum objeto é alterado
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Hospede outro)) {
            return false;
        }
        return this.cpf.equals(outro.getCpf());
    }

    /**
     * Devolve o código de espalhamento do hóspede, derivado do CPF para manter
     * a coerência com equals: hóspedes considerados iguais produzem o mesmo
     * código, como exige o contrato de Object.
     *
     * @return o código de espalhamento calculado a partir do CPF
     *
     * @pre Nenhuma
     * @post Nenhum objeto é alterado; chamadas sucessivas devolvem sempre o
     *       mesmo valor enquanto o objeto existir
     */
    @Override
    public int hashCode() {
        return cpf.hashCode();
    }
}
