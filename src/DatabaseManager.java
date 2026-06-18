import java.sql.*;

public class DatabaseManager {

    private static final String url = "jdbc:postgresql://localhost:5432/train_reservation";
    private static final String user = "postgres";
    private static final String password = "argha";


    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }
}
