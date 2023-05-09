package DBUTILS;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.time.LocalDate;

public class DBConnection {
	public static Connection getConnection() {
		Connection conn=null;
		try {
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/todoApp","root","123456789");
			return conn;
		}catch(Exception e){
			System.out.println("Found mistake "+ e.getMessage());
			e.printStackTrace();
			
		}
		return null;
		
		
	}
	public static void main(String[] args) {
		new DBConnection();
		System.out.println(DBConnection.getConnection());
	}
	public static Date getSQLDate(LocalDate date) {
		return java.sql.Date.valueOf(date);
	}

	public static LocalDate getUtilDate(Date sqlDate) {
		return sqlDate.toLocalDate();
	}
}
