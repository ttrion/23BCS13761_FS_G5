import java.sql.*;
import java.util.Scanner;

public class Q3 {

    private static final String URL = "jdbc:mysql://localhost:3306/college";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void insertStudent(Connection con, int id, String name, int age) throws SQLException {
        String query = "INSERT INTO student(id, name, age) VALUES(?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);

            int rows = ps.executeUpdate();
            System.out.println(rows + " record inserted");
        }
    }

    public static void updateStudent(Connection con, int id, String name) throws SQLException {
        String query = "UPDATE student SET name = ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            System.out.println(rows + " record updated");
        }
    }

    public static void deleteStudent(Connection con, int id) throws SQLException {
        String query = "DELETE FROM student WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            System.out.println(rows + " record deleted");
        }
    }

    public static void fetchStudents(Connection con) throws SQLException {
        String query = "SELECT * FROM student";

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.printf("%-10s %-20s %-10s%n", "ID", "NAME", "AGE");

            while (rs.next()) {
                System.out.printf(
                        "%-10d %-20s %-10d%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age")
                );
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

                int id = sc.nextInt();
                sc.nextLine();
                String name = sc.nextLine();
                int age = sc.nextInt();

                insertStudent(con, id, name, age);

                sc.nextLine();
                String updatedName = sc.nextLine();
                updateStudent(con, id, updatedName);

                fetchStudents(con);

                int deleteId = sc.nextInt();
                deleteStudent(con, deleteId);

                fetchStudents(con);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver Not Found");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}