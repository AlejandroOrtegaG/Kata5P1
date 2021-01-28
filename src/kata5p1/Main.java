package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static kata5p1.MailListReader.read;

public class Main {

    public static void main(String[] args) {
        SelectApp app = new SelectApp();
        app.selectAll();
        createNewTable();

        Main idt = new Main();
        // Se insertar 3 nuevas líneas
        idt.insert("abc@ulpgc.es");
        idt.insert("xyz@ull.es");
        idt.insert("def123@gmail.com");

    }

    public static void createNewTable() {

        //Cadena con la direccion del archivo email.txt
        String email = "C:\\Users\\alexi\\Documents\\NetBeansProjects\\Kata5P1\\email.txt";
        List<String> emails = new ArrayList<>(read(email));

        // Cadena de conexión SQLite
        String url = "jdbc:sqlite:mail.db";
        // Instrucción SQL para crear nueva tabla
        String sql = "CREATE TABLE IF NOT EXISTS direcc_email (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " direccion text NOT NULL);";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // Se crea la nueva tabla
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para insertar datos en la tabla direcc_email
    public void insert(String email) {
        String sql = "INSERT INTO direcc_email(direccion) VALUES(?)";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Connection connect() {
        // Cadena de conexión SQLite
        String url = "jdbc:sqlite:mail.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
