import java.sql.*;

public class FetchData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bank"; // Replace with your DB name
        String user = "root"; // Your MySQL username
        String password = "Vedant@20"; // Your MySQL password

        try {
            // Step 1: Connect to MySQL
            Connection conn = DriverManager.getConnection(url, user, password);

            // Step 2: Create statement
            Statement stmt = conn.createStatement();

            // Step 3: Execute query
            String query = "SELECT * FROM account";  // Replace 'accounts' with your actual table name
            ResultSet rs = stmt.executeQuery(query);

            // Step 4: Loop through result set
            while (rs.next()) {
                int accNo = rs.getInt("Acc_no");
                String accName = rs.getString("Acc_Name");
                String city = rs.getString("City");
                int balance = rs.getInt("Balance");
                int loan = rs.getInt("Loan_taken");

                System.out.println("Account No: " + accNo +
                        ", Name: " + accName +
                        ", City: " + city +
                        ", Balance: " + balance +
                        ", Loan Taken: " + loan);
            }

            // Step 5: Clean up
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("❌ Error fetching data!");
            e.printStackTrace();
        }
    }
}
