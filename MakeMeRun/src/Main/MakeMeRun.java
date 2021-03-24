package Main;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import java.sql.*;

public class MakeMeRun {

    private Connection conn;
    public Connection getConn() {
        return conn;
    }

    public MakeMeRun() {

            String username = "root";
            String pass = "root";
            String jdbcwb = "jdbc:mysql://127.0.0.1:3306/makemerun?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    //        String jdbcwb="jdbc:mysql://localhost:3306/?user=root";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(jdbcwb,username,pass);
                Controller.setConnection(this);
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Upozorenje");
                alert.setHeaderText("Neuspjesna konekcija sa bazom podataka");
                alert.setContentText("Konektujte se na bazu podataka i nastavite dalje");
                alert.showAndWait();
                Platform.exit();
                System.exit(0);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    public ResultSet executeStatement(String stmt) throws SQLException {
        Statement statement = null;
        statement = conn.createStatement();
        return statement.executeQuery(stmt);
}

    public boolean updateStatement(String stmt) throws SQLException {
            Statement statement = null;
            statement = conn.createStatement();
            statement.executeUpdate(stmt);
            return true;
    }
}
