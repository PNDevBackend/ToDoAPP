package DAO;

import java.sql.SQLException;
import java.util.ArrayList;


import MODEL.ToDo;

public interface ToDoDAO {
	void insertWork(ToDo todo) throws SQLException;
	boolean updateWork(ToDo todo)throws SQLException;
	boolean deleteWork(int id) throws SQLException;
	ArrayList<ToDo> selectAllWorks();
	ToDo selectSingleWork(long toDoID);
	
}
