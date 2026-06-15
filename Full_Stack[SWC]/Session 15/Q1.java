import java.sql.*;
import java.util.Scanner;

public class Q1 {

    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

                con.setAutoCommit(false);

                String sql = "INSERT INTO employee(id, name, salary) VALUES(?, ?, ?)";

                try (PreparedStatement ps = con.prepareStatement(sql)) {

                    int n = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < n; i++) {
                        int id = sc.nextInt();
                        sc.nextLine();
                        String name = sc.nextLine();
                        double salary = sc.nextDouble();
                        sc.nextLine();

                        ps.setInt(1, id);
                        ps.setString(2, name);
                        ps.setDouble(3, salary);

                        ps.addBatch();
                    }

                    try {
                        int[] results = ps.executeBatch();
                        con.commit();

                        int insertedCount = 0;

                        for (int result : results) {
                            if (result >= 0 || result == Statement.SUCCESS_NO_INFO) {
                                insertedCount++;
                            }
                        }

                        System.out.println("Batch executed successfully");
                        System.out.println("Records inserted: " + insertedCount);

                    } catch (BatchUpdateException e) {
                        con.rollback();

                        int successfulRecords = e.getUpdateCounts().length;

                        System.out.println("Batch execution failed");
                        System.out.println("Records inserted before failure: " + successfulRecords);
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}