import java.sql.*;
import java.util.Scanner;

public class DistrictManager {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/vaibhav";
    static final String USER = "postgres";
    static final String PASS = "";

    static Connection conn = null;
    static Statement stmt = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Insert");
                System.out.println("2. Modify");
                System.out.println("3. Delete");
                System.out.println("4. Search");
                System.out.println("5. View All");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        insertDistrict();
                        break;
                    case 2:
                        modifyDistrict();
                        break;
                    case 3:
                        deleteDistrict();
                        break;
                    case 4:
                        searchDistrict();
                        break;
                    case 5:
                        viewAllDistricts();
                        break;
                    case 6:
                        System.out.println("Exiting program.");
                        conn.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void insertDistrict() throws SQLException {
        System.out.print("Enter district name: ");
        String name = scanner.nextLine();
        System.out.print("Enter district area: ");
        String area = scanner.nextLine();
        System.out.print("Enter district population: ");
        String population = scanner.nextLine();

        String sql = "INSERT INTO District (Name, Area, Population) VALUES ('" + name + "', '" + area + "', '" + population + "')";
        int rowsAffected = stmt.executeUpdate(sql);
        if (rowsAffected > 0)
            System.out.println("District inserted successfully.");
        else
            System.out.println("Failed to insert district.");
    }

    public static void modifyDistrict() throws SQLException {
        System.out.print("Enter district name to modify: ");
        String name = scanner.nextLine();
        System.out.print("Enter new area: ");
        String newArea = scanner.nextLine();
        System.out.print("Enter new population: ");
        String newPopulation = scanner.nextLine();

        String sql = "UPDATE District SET Area='" + newArea + "', Population='" + newPopulation + "' WHERE Name='" + name + "'";
        int rowsAffected = stmt.executeUpdate(sql);
        if (rowsAffected > 0)
            System.out.println("District modified successfully.");
        else
            System.out.println("District not found.");
    }

    public static void deleteDistrict() throws SQLException {
        System.out.print("Enter district name to delete: ");
        String name = scanner.nextLine();

        String sql = "DELETE FROM District WHERE Name='" + name + "'";
        int rowsAffected = stmt.executeUpdate(sql);
        if (rowsAffected > 0)
            System.out.println("District deleted successfully.");
        else
            System.out.println("District not found.");
    }

    public static void searchDistrict() throws SQLException {
        System.out.print("Enter district name to search: ");
        String name = scanner.nextLine();

        String sql = "SELECT * FROM District WHERE Name='" + name + "'";
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            System.out.println("District found:");
            System.out.println("Name: " + rs.getString("Name"));
            System.out.println("Area: " + rs.getString("Area"));
            System.out.println("Population: " + rs.getString("Population"));
        } else {
            System.out.println("District not found.");
        }
        rs.close();
    }

    public static void viewAllDistricts() throws SQLException {
        String sql = "SELECT * FROM District";
        ResultSet rs = stmt.executeQuery(sql);
        if (!rs.next()) {
            System.out.println("No districts found.");
        } else {
            System.out.println("All districts:");
            do {
                System.out.println("Name: " + rs.getString("Name"));
                System.out.println("Area: " + rs.getString("Area"));
                System.out.println("Population: " + rs.getString("Population"));
            } while (rs.next());
        }
        rs.close();
    }
}

