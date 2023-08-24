package Controller;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_managerdb","root","password");
            System.out.println("Connected");
            return conn;
        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
            return null;
        }
    }

    public void disconnect(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("Disconnect error: " + e.getMessage());
        }
    }
}
