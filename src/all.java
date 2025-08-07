import java.sql.*;
import java.util.Scanner;

public class all {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // JDBC connection parameters
        String url = "jdbc:mysql://localhost:3306/bank"; // Make sure bank_db exists
        String user = "root"; // Replace with your MySQL username
        String password = "Vedant@20"; // Replace with your MySQL password

        try {
            // Step 1: Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            Connection conn = DriverManager.getConnection(url, user, password);

            // Step 3: Create table if not exists
            String createTable = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "Acc_no INT PRIMARY KEY, " +
                    "Acc_Name VARCHAR(50), " +
                    "City VARCHAR(20), " +
                    "Balance INT, " +
                    "Loan_taken INT" +
                    ")";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createTable);
            System.out.println("✅ Table ready!");

            // Step 4: Get input from user
            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();
            sc.nextLine(); // Clear \n

            System.out.print("Enter Account Name: ");
            String accName = sc.nextLine();

            System.out.print("Enter City: ");
            String city = sc.nextLine();

            System.out.print("Enter Balance: ");
            int balance = sc.nextInt();

            System.out.print("Enter Loan Taken: ");
            int loan = sc.nextInt();

            // Step 5: Insert input into the table
            String insertQuery = "INSERT INTO accounts (Acc_no, Acc_Name, City, Balance, Loan_taken) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, accNo);
            pstmt.setString(2, accName);
            pstmt.setString(3, city);
            pstmt.setInt(4, balance);
            pstmt.setInt(5, loan);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Account inserted successfully!");
            }

            // Step 6: Display all records from table
            System.out.println("\n📋 All Accounts in Database:");
            ResultSet rs = stmt.executeQuery("SELECT * FROM accounts");

            while (rs.next()) {
                System.out.println("Account No: " + rs.getInt("Acc_no") +
                        ", Name: " + rs.getString("Acc_Name") +
                        ", City: " + rs.getString("City") +
                        ", Balance: " + rs.getInt("Balance") +
                        ", Loan Taken: " + rs.getInt("Loan_taken"));
            }

            // Cleanup
            rs.close();
            pstmt.close();
            stmt.close();
            conn.close();
            sc.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
