package todoList;

import java.sql.SQLException;
import java.util.List;

public interface TodoDao {
	
	static void insertTodo(Todo todo) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	static Todo selectTodo(long todoId) {
		// TODO Auto-generated method stub
		return null;
	}

	static List<Todo> selectAllTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	static boolean deleteTodo(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	static boolean updateTodo(Todo todo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
