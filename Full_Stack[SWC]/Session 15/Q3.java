import java.sql.*;
import java.util.Scanner;

public class Q3 {

    private static final String URL = "jdbc:mysql://localhost:3306/bankdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void transferFunds(Connection con, int fromAccountId, int toAccountId, double amount)
            throws SQLException {

        String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE account_id = ? AND balance >= ?";
        String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";

        try (
                PreparedStatement debitStmt = con.prepareStatement(debitQuery);
                PreparedStatement creditStmt = con.prepareStatement(creditQuery)
        ) {
            con.setAutoCommit(false);

            debitStmt.setDouble(1, amount);
            debitStmt.setInt(2, fromAccountId);
            debitStmt.setDouble(3, amount);

            int debitRows = debitStmt.executeUpdate();

            if (debitRows == 0) {
                throw new SQLException("Insufficient balance or invalid source account");
            }

            creditStmt.setDouble(1, amount);
            creditStmt.setInt(2, toAccountId);

            int creditRows = creditStmt.executeUpdate();

            if (creditRows == 0) {
                throw new SQLException("Invalid destination account");
            }

            con.commit();
            System.out.println("Fund transfer successful");

        } catch (SQLException e) {
            con.rollback();
            System.out.println("Transaction rolled back");
            System.out.println("Error: " + e.getMessage());
        } finally {
            con.setAutoCommit(true);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

                int fromAccountId = sc.nextInt();
                int toAccountId = sc.nextInt();
                double amount = sc.nextDouble();

                transferFunds(con, fromAccountId, toAccountId, amount);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}