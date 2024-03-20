package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.testng.annotations.Test;

import DBActions.DatabaseActions;

public class DBconnectionTest{
	
	DatabaseActions dbAction = new DatabaseActions();
	
	@SuppressWarnings("static-access")
	@Test
	public void connect_to_sql_server() throws ClassNotFoundException, SQLException {
		//DatabaseActions dbAction = new DatabaseActions();
		dbAction.connectToDatabase("jdbc:mysql://localhost:3306/gkv_test", "root", "gkv123");
//		String url = "jdbc:mysql://localhost:3306/gkv_test";
//        String username = "root";
//        String password = "gkv123";
//        
//        int id = 0;
//        String name = null;
//        
//        // Connect to the MySQL database
//        Connection connection = DriverManager.getConnection(url, username, password);
			}
	
	@SuppressWarnings("static-access")
	@Test
	public void create_a_table() throws SQLException {
	
		dbAction.createTable();
		
	}

}
