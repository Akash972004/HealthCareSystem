import java.sql.*;

public class DBConnection {
    public static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/hcms";
        String user = "root";
        String password = "Akash@2004";
        return DriverManager.getConnection(url, user, password);
    }
}