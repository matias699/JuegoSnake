import java.sql.*;

public class Main {

    public static void main(String[] args) {
        String db_url = "jdbc:mysql://localhost:3306/snake";
        String user = "root";
        String paswd = "root";

        try {
            Connection con = DriverManager.getConnection(db_url, user, paswd);
        }catch (Exception e){
            System.out.println("La conexion ha fallado");
        }
    }
}