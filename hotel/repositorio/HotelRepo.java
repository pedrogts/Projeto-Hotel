package hotel.repositorio;

import hotel.negocio.Hotel;
import java.io.*;

public class HotelRepo {
    private static final String ARQUIVO = "hotel.dat";

    public static void salvar(Hotel hotel) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(hotel);}
    }

    public static Hotel carregar() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (Hotel) ois.readObject();}   
    }
}
