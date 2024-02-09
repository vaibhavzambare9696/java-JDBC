import java.sql.*;

public class person {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/vaibhav";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            searchPersonsBornInYear(connection, 1986);

            searchFemalePersonsBornBetweenYears(connection, 2000, 2005);

            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void searchPersonsBornInYear(Connection connection, int year) throws SQLException {
        String sql = "SELECT * FROM person WHERE EXTRACT(YEAR FROM p_bdate) = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, year);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Persons born in the year " + year + ":");
        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("p_id") + ", Name: " + resultSet.getString("p_name") +
                    ", Gender: " + resultSet.getString("p_gender") + ", Date of Birth: " + resultSet.getDate("p_bdate"));
        }
        preparedStatement.close();
    }

    private static void searchFemalePersonsBornBetweenYears(Connection connection, int startYear, int endYear) throws SQLException {
        String sql = "SELECT * FROM person WHERE EXTRACT(YEAR FROM p_bdate) BETWEEN ? AND ? AND p_gender = 'Female'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, startYear);
        preparedStatement.setInt(2, endYear);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Female persons born between " + startYear + " and " + endYear + ":");
        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("p_id") + ", Name: " + resultSet.getString("p_name") +
                    ", Gender: " + resultSet.getString("p_gender") + ", Date of Birth: " + resultSet.getDate("p_bdate"));
        }
        preparedStatement.close();
    }
}

