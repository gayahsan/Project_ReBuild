package todoList;

import java.sql.Connection;

import com.mysql.jdbc.PreparedStatement;

public class UserDao {
	
	public int registerUser(User user){
		
		String INSERT_USER_SQL = "insert into users" + "(first_name, last_name, username, password) values " + "(?, ?, ?, ?);";
		
		int result = 0;
		
		try 
			(Connection connection = JDBCUtils.getConnection();
				
// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(INSERT_USER_SQL)) 
		{
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getUserName());
			preparedStatement.setString(4, user.getPassword());
			
			System.out.println(preparedStatement);
			
// Step 3: Execute the query or update query
			
			result = preparedStatement.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
