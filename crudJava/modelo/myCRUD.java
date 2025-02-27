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

            String query = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(255), email VARCHAR(255), PRIMARY KEY (id))";
            stmt.executeUpdate(query);

            Scanner scan = new Scanner(System.in);
            boolean loop_control = true;

            while (loop_control) {
            	System.out.println("\n\n\n\n\n\n\n");
                System.out.println("1. Add user");
                System.out.println("2. Read user");
                System.out.println("3. Update user");
                System.out.println("4. Delete user");
                System.out.println("5. Exit");

                System.out.print("Enter Choice: ");
                String choice = scan.nextLine();

                switch (choice) {
                    case "1": {
                        System.out.print("User name: ");
                        String name = scan.nextLine();

                        System.out.print("User email: ");
                        String email = scan.nextLine();

                        query = "INSERT INTO users (name, email) VALUES ('" + name + "', '" + email + "')";
                        stmt.executeUpdate(query);
                        break;
                    }
                    case "2": {
                        System.out.print("Enter user id: ");
                        int id = scan.nextInt();

                        query = "SELECT * FROM users WHERE id = " + id;
                        ResultSet rs = stmt.executeQuery(query);

                        if (rs.next()) {
                            System.out.println("ID: " + rs.getInt("id"));
                            System.out.println("Name: " + rs.getString("name"));
                            System.out.println("Email: " + rs.getString("email"));
                        } else {
                            System.out.println("User not found.");
                        }
                        break;
                    }
                    case "3": {
                        System.out.print("Enter user id: ");
                        int id = scan.nextInt();
                        scan.nextLine();

                        System.out.print("User new name: ");
                        String name = scan.nextLine();

                        System.out.print("User new email: ");
                        String email = scan.nextLine();

                        query = "UPDATE users SET name = '" + name + "', email = '" + email + "' WHERE id = " + id;
                        stmt.executeUpdate(query);
                        break;
                    }
                    case "4": {
                        System.out.print("Enter user id: ");
                        int id = scan.nextInt();
                        scan.nextLine();

                        query = "DELETE FROM users WHERE id = " + id;
                        stmt.executeUpdate(query);
                        break;
                    }
                    case "5": {
                        loop_control = false;
                        stmt.close();
                        conn.close();
                        System.out.println("Disconnected");
                        break;
                    }
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
