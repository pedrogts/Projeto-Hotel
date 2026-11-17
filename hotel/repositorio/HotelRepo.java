package hotel.repositorio;

import hotel.negocio.Hotel;
import java.io.*;

/**
 * Persistência do estado do hotel em arquivo local (REQ13), por serialização
 * Java no arquivo {@value #ARQUIVO}.
 */
public class HotelRepo {
    private static final String ARQUIVO = "hotel.dat";

    /**
     * Salva o estado completo do hotel no arquivo {@value #ARQUIVO}, substituindo
     * o conteúdo anterior.
     *
     * @param hotel hotel a ser salvo
     * @throws IOException se não for possível gravar o arquivo ou serializar o hotel
     *
     * @pre O hotel e os objetos que ele contém devem ser serializáveis
     * @post O arquivo {@value #ARQUIVO} contém o estado do hotel no momento da chamada
     */
    public static void salvar(Hotel hotel) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(hotel);}
    }

    /**
     * Carrega o estado do hotel salvo no arquivo {@value #ARQUIVO}.
     *
     * @return o hotel recuperado do arquivo
     * @throws IOException se o arquivo não existir ou não puder ser lido
     * @throws ClassNotFoundException se o conteúdo do arquivo não corresponder às
     *         classes do sistema
     *
     * @pre O arquivo {@value #ARQUIVO} deve ter sido gerado por salvar
     * @post Nenhum objeto existente é alterado; um novo hotel é devolvido
     */
    public static Hotel carregar() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (Hotel) ois.readObject();}   
    }
}
