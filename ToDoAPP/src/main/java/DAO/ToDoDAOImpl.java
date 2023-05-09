package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DBUTILS.DBConnection;
import MODEL.ToDo;

public class ToDoDAOImpl implements ToDoDAO{
	private static final String INSERT_TODOS_SQL = "INSERT INTO todos"
			+ "  (title, username, description, target_date,  is_done) VALUES " + " (?, ?, ?, ?, ?);";

	private static final String SELECT_TODO_BY_ID = "select id,title,username,description,target_date,is_done from todos where id =?";
	private static final String SELECT_ALL_TODOS = "select * from todos";
	private static final String DELETE_TODO_BY_ID = "delete from todos where id = ?;";
	private static final String UPDATE_TODO = "update todos set title = ?, username= ?, description =?, target_date =?, is_done = ? where id = ?;";

	@Override
	public void insertWork(ToDo todo) throws SQLException {
		System.out.println(INSERT_TODOS_SQL);
		try {
			Connection conn=DBConnection.getConnection();
			PreparedStatement pst=conn.prepareStatement(INSERT_TODOS_SQL);
			pst.setString(1, todo.getTitle());
			pst.setString(2, todo.getDescription());
			pst.setString(3, todo.getUserName());
			pst.setLong(4, todo.getId());
			pst.setDate(5, DBConnection.getSQLDate(todo.getTargetDate()));
			pst.setBoolean(6, todo.isStatus());
			System.out.println(pst);
			pst.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}

	@Override
	public boolean updateWork(ToDo todo) throws SQLException {
		boolean rowUpdated=false;
		try {
			
			Connection conn=DBConnection.getConnection();
			PreparedStatement pst=conn.prepareStatement(UPDATE_TODO);
			pst.setString(1, todo.getTitle());
			pst.setString(2, todo.getDescription());
			pst.setString(3, todo.getUserName());
			pst.setLong(4, todo.getId());
			pst.setDate(5, DBConnection.getSQLDate(todo.getTargetDate()));
			pst.setBoolean(6, todo.isStatus());
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return rowUpdated;
	}

	@Override
	public boolean deleteWork(int id) throws SQLException {
		boolean rowDelete=false;
		try {
			Connection conn=DBConnection.getConnection();
			PreparedStatement pst=conn.prepareStatement(DELETE_TODO_BY_ID);
			pst.setInt(1, id);
			rowDelete=pst.executeUpdate()>0;
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return rowDelete;
	}

	@Override
	public ArrayList<ToDo> selectAllWorks() {
		
		ArrayList<ToDo> todos = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = DBConnection.getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODOS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("userName");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("targetDate").toLocalDate();
				boolean isStatus = rs.getBoolean("isStatus");
				todos.add(new ToDo(id, title, username, description, targetDate,isStatus));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return todos;
	}

	@Override
	public ToDo selectSingleWork(long toDoID) {
		ToDo todo = null;
		// Step 1: Establishing a Connection
		try (Connection connection = DBConnection.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODO_BY_ID);) {
			preparedStatement.setLong(1, toDoID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("userName");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("targetDate").toLocalDate();
				boolean isStatus = rs.getBoolean("isStatus");
				todo = new ToDo(id, title, username, description, targetDate, isStatus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return todo;
	}
	

}
