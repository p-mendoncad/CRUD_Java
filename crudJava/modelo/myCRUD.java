package modelo;

import java.sql.*;
import java.util.*;

public class myCRUD {

	public static void main(String[] args) {
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/crud_db";
		String USER = "root";
		String PASS = "";
		
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			
			String query = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT,name VARCHAR(255),email VARCHAR(255),PRIMARY KEY (id))";
			
			stmt.executeUpdate(query);
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("1. Add user");
			System.out.println("2. Read user");
			System.out.println("4. Delete user");
			
			System.out.print("Enter Choice: ");
			String choice = scan.nextLine();
			
			switch(choice) {
				case "1":
					System.out.print("User name: ");
					String name = scan.nextLine();
					
					System.out.print("User email: ");
					String email = scan.nextLine();
					
					query = "INSERT INTO users (name, email) VALUES ('"+name+"','"+email+"')";
					
					stmt.executeUpdate(query);
					break;
				case "2":
					System.out.print("Enter user id: ");
					int id = scan.nextInt();
					
					query = "SELECT * FROM users WHERE id =" + id;
					
					ResultSet rs = stmt.executeQuery(query);
					
					if(rs.next()) {
						System.out.println("ID: " + rs.getInt("id"));
						System.out.println("Name: " + rs.getString("name"));
						System.out.println("Email: " + rs.getString("email"));
					}else {
						System.out.println("User not found.");
					}
					break;	
				case "4":
					System.out.print("Enter user id: ");
					int userID = scan.nextInt();
					
					query = "DELETE FROM users WHERE id =" +userID;
					
					stmt.executeUpdate(query);
					break;
			}
			
			stmt.close();
			conn.close();
			
		}catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
