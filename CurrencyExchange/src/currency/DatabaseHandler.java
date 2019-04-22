package currency;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {
	private Connection conn = null;
	String url = "jdbc:sqlite:C://sqlite/data.db";
	
	public void connect() {
		try {
			conn = DriverManager.getConnection(url);
           
			System.out.println("Connection to SQLite has been established.");
           
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void disconnect() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
 
   
	public void createNewTableIfNotExist(String fileName) {
		String sql = "CREATE TABLE IF NOT EXISTS " + fileName + " (\n"
               + "	id integer PRIMARY KEY,\n"
               + "	name text NOT NULL,\n"
               + "	date text NOT NULL"
               + ");";

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				stmt.execute(sql);
				System.out.println("Table " + fileName + " has been created.");
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

	}
	
	public ArrayList<String> selectAll(String fileName, String queryName){
        String sql = "SELECT " + queryName + " FROM " + fileName;
        ArrayList<String> query = new ArrayList<String>();
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs  = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
            	query.add(rs.getString(queryName));
                System.out.println(rs.getString(queryName));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("a");
        System.out.println(query);
        return query;
    }
}

