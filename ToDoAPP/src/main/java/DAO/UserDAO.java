package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import DBUTILS.DBConnection;
import MODEL.User;

public class UserDAO {
	public int registerUser(User user) throws ClassNotFoundException{
		String INSERT_USERS_SQL = "INSERT INTO users"
				+ "  (first_name, last_name, username, password) VALUES "
				+ " (?, ?, ?, ?);";

		int result = 0;
		try {
			Connection conn=DBConnection.getConnection();
			Class.forName("com.mysql.jdbc.Driver");
			
			PreparedStatement pst=conn.prepareStatement(INSERT_USERS_SQL);
			pst.setString(1, user.getFirstName());
			pst.setString(2,user.getLastName());
			pst.setString(3, user.getUserName());
			pst.setString(4, user.getPassWord());
			System.out.println(pst);
			result=pst.executeUpdate();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Found bug " + e.getMessage());
		}
		return result;
	}

}
