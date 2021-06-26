package gayashan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GayaCustomerDio {
	public int registerCustomer(GayaCustomer Customer) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO profiles" +
            "  (id, name, phone, email, username, password) VALUES " +
            " (?, ?, ?, ?, ?,?);";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "root", "root");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, Customer.getpName());
            preparedStatement.setString(3, Customer.getpPhone());
            preparedStatement.setString(4, Customer.getEmail());
            preparedStatement.setString(5, Customer.getUsername());
            preparedStatement.setString(6, Customer.getPassword());
            
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
