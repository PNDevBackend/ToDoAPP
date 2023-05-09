package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DBUTILS.DBConnection;
import MODEL.Login;

public class LoginDAO {
	public boolean validated(Login login)throws ClassNotFoundException {
		boolean status = false;
		Class.forName("com.mysql.jdbc.Driver");
		try {
			String query="SELECT * FROM todoApp WHERE userName=? and passWord=?";
			Connection conn=DBConnection.getConnection();
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				status=true;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Find bug "+e.getMessage());
			
			
		}
		return status;
		
		
	}

}
