package todoList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class TodoDaoImpl implements TodoDao {
	
	private static final String INSERT_TODOS_SQL = "INSERT INTO todos" + "(title, userName, description, target_date, status) VALUES " + "(?, ?, ?, ?, ?);";
	
	private static final String SELECT_TODO_BY_ID = "select id,title,userName,description,target_date,status from todos where id =?";
	
	private static final String SELECT_ALL_TODOS = "select * from todos";
	
	private static final String DELETE_TODO_BY_ID = "delete from todos where id = ?;";
	
	private static final String UPDATE_TODO = "update todos set title = ?, userName= ?, description =?, target_date =?, status = ? where id = ?;";


	public TodoDaoImpl() {
	}
	
	public void insertTodo(Todo todo) throws SQLException {
		System.out.println(INSERT_TODOS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = (Connection) JDBCUtils.getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(INSERT_TODOS_SQL)) {
			preparedStatement.setString(1, todo.getTitle());
			preparedStatement.setString(2, todo.getUserName());
			preparedStatement.setString(3, todo.getDescription());
			preparedStatement.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
			preparedStatement.setBoolean(5, todo.getStatus());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			//JDBCUtils.printSQLException(exception);
			exception.printStackTrace();
		}
	}
	
	
	
	public Todo selectTodo(long todoId) {
		Todo todo = null;
		// Step 1: Establishing a Connection
		try (Connection connection = (Connection) JDBCUtils.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(SELECT_TODO_BY_ID);) {
			preparedStatement.setLong(1, todoId);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				boolean isDone = rs.getBoolean("status");
				todo = new Todo(id, title, username, description, targetDate, isDone);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return todo;
	}
	
	
	
	
	
	public List<Todo> selectAllTodos() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Todo> todos = new ArrayList<>();

		// Step 1: Establishing a Connection
		try (Connection connection = (Connection) JDBCUtils.getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_TODOS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				boolean isDone = rs.getBoolean("status");
				todos.add(new Todo(id, title, username, description, targetDate, isDone));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return todos;
	}
	
	
	
	
	
	public boolean deleteTodo(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = (Connection) JDBCUtils.getConnection();
				PreparedStatement statement = (PreparedStatement) connection.prepareStatement(DELETE_TODO_BY_ID);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateTodo(Todo todo) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = (Connection) JDBCUtils.getConnection();
				PreparedStatement statement = (PreparedStatement) connection.prepareStatement(UPDATE_TODO);) {
			statement.setString(1, todo.getTitle());
			statement.setString(2, todo.getUserName());
			statement.setString(3, todo.getDescription());
			statement.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
			statement.setBoolean(5, todo.getStatus());
			statement.setInt(6, todo.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	
	
	
}
