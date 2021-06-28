package gayashan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.catalina.User;

public class Gayashan_Dio {

	private String jdbcURL = "jdbc:mysql://localhost:3306/rebuild";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO usersmanager" + "  (name, email, phone, address, description) VALUES "
			+ " (?, ?, ?, ? ,?);";

	private static final String SELECT_USER_BY_ID = "select id,name,email,phone,address,description from usersmanager where id =?";
	private static final String SELECT_ALL_USERS = "select * from usersmanager";
	private static final String DELETE_USERS_SQL = "delete from usersmanager where id = ?;";
	private static final String UPDATE_USERS_SQL = "update usersmanager set name = ?,email= ?,phone= ?,address= ?, description =? where id = ?;";

	public Gayashan_Dio() {
	}
	
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	
	public void insertUser(Gayasahan_User gayasahan_User) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			
			preparedStatement.setString(1, gayasahan_User.getName());
			preparedStatement.setString(2, gayasahan_User.getEmail());
			preparedStatement.setInt(3, gayasahan_User.getPhone());
			preparedStatement.setString(4, gayasahan_User.getAddress());
			preparedStatement.setString(5, gayasahan_User.getDescription());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public boolean updateUser(Gayasahan_User gayasahan_User) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, gayasahan_User.getName());
			statement.setString(2, gayasahan_User.getEmail());
			statement.setInt(3, gayasahan_User.getPhone());
			statement.setString(4, gayasahan_User.getAddress());
			statement.setString(5, gayasahan_User.getDescription());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	
	public Gayasahan_User selectUser(int id) {
		Gayasahan_User gayasahan_User = null;
		
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				int phone = rs.getInt("phone");
				String address = rs.getString("address");
				String description = rs.getString("description");
				gayasahan_User = new Gayasahan_User(id, name, email, phone, address, description);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gayasahan_User;
	}
	
	
}
