package kata5p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//C:\Users\alexi\Documents\NetBeansProjects\Kata4
public class MailListReader {

    public static List<String> read(String mail) {

        List<String> lista = new ArrayList();

        try {
            BufferedReader leer = new BufferedReader(new FileReader(new File(mail)));
            while (true) {
                String line = leer.readLine();

                if (line == null) {
                    break;
                }else{
                    lista.add(line);
                }
            }
        } catch (FileNotFoundException exception) {
            System.out.println("No se ha encontrado el archivo indicado" + "\n"
                    + exception.getMessage());
        } catch (IOException exception) {
            System.out.println("No hay lineas que leer" + "\n"
                    + exception.getMessage());
        }

        return lista;
    }
}
