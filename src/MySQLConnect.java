import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnect {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bank";
        String user = "root";
        String password = "Vedant@" +
                "20";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to MySQL!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed!");
            e.printStackTrace();
        }
    }
}
