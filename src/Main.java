import java.sql.*;

public class Main {

    private static final String db_url = "jdbc:mysql://localhost:3306/snake";
    private static final String user = "root";
    private static final String paswd = "root";

    public static void insertarUsuario(String nombre) {
        String insertQy = "insert into usuarios (nombre) values (?)";

        try {
            Connection con = DriverManager.getConnection(db_url, user, paswd);
            PreparedStatement ps = con.prepareStatement(insertQy);

            ps.setString(1, nombre);
            ps.executeUpdate();

            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error en la conexión con la base de datos");
        }
    }

    public static void insertarPuntuacion(int puntos, int idUsuario) {
        String insertQy = "insert into puntuaciones (puntuacion, fecha, id_usuario) values (?, ?, ?)";

        try {
            Connection con = DriverManager.getConnection(db_url, user, paswd);
            PreparedStatement ps = con.prepareStatement(insertQy);

            ps.setInt(1, puntos);
            ps.setString(2, "2026-06-09");
            ps.setInt(3, idUsuario);

            ps.executeUpdate();

            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error en la conexión con la base de datos");
        }
    }
}