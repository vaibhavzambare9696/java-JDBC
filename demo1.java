import java.sql.*;

public class demo1 {
    public static void main(String[] args) {
        try {
            Connection con;
            Statement stmt;
            ResultSet rs;
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost/vaibhav", "postgres", "");

            if (con == null) {
                System.out.println("Connection fail");
            } else {
                System.out.println("Connection ok");
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM student");

                while (rs.next()) {
                    System.out.println("s_id: " + rs.getInt(1));
                    System.out.println("s_name: " + rs.getString(2));
                    System.out.println("s_gender: " + rs.getString(3));
                }

                con.close();
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
            ex.printStackTrace();
        } catch (SQLException ex1) {
            System.out.println("SQL Error");
            ex1.printStackTrace();
        } catch (Exception ex2) {
            System.out.println("Error");
            ex2.printStackTrace();
        }
    }
}
