import java.sql.*;

public class SQLiteTester {
	public static void main(String args[]) {
		
		/*
		 * Class to demonstrate how to connect to an existing SQLite database, 
		 * and how to access/modify it using the SQLite-JDBC.
		 * 
		 * Ensure that "sqlite-jdbc-3.8.11.2.jar" is added to the class path.
		 * 
		 * ADAPTED FROM:
		 * 		http://www.tutorialspoint.com/sqlite/sqlite_java.htm
		 */
		
		// Create a connection to an existing database
		Connection c = null;
		Statement stmt = null;
		
		// Attempt connection to existing database (in the Java project) called "test.db"
		try {
			// Establish connection and open the database
			Class.forName("org.sqlite.JDBC");
			// c = DriverManager.getConnection("jdbc:sqlite:test.db");
			//c = DriverManager.getConnection("jdbc:sqlite:SDB DB.sqlite");
			//c = DriverManager.getConnection("jdbc:sqlite:SDB DB.sqlite");
			c = DriverManager.getConnection("jdbc:sqlite:vault_databse.sqlite");
			
			c.setAutoCommit(false); // Forces database changes to be saved ONLY after a commit
			System.out.println("Database opened successfully!");
			
			// Write an SQL statement to execute
			// In this case, create a table with various columns
			stmt = c.createStatement();
			String sql =	"CREATE TABLE Company " + 
						 	"(ID INT PRIMARY KEY	NOT NULL,"	+
						 	"NAME		TEXT		NOT NULL,"	+
						 	"AGE		INT			NOT NULL,"	+
						 	"ADDRESS	CHAR(50),"				+
						 	"SALARY		REAL)"					;
			// Execute the statement
			stmt.executeUpdate(sql);
			System.out.println("Table created successfully!");
			
			// Insert entries into a table
		    sql = "INSERT INTO Company (ID, NAME, AGE, ADDRESS, SALARY) " +
		          "VALUES (2, 'Becky', 31, 'New York', 30000.00);";
		    stmt.executeUpdate(sql);
		    
		    sql = "INSERT INTO Company (ID, NAME, AGE, ADDRESS, SALARY) " +
		          "VALUES (3, 'Allen', 25, 'Texas', 15000.00);"; 
		    stmt.executeUpdate(sql);
		    c.commit(); // Save all database changes made
		    System.out.println("Records created successfully!\n");
		    
		    // Select all entries from a table
		    ResultSet rs = stmt.executeQuery( "SELECT * FROM Company;" ); // Stores a set of queued entries
		    while (rs.next()) {
		    	int id = rs.getInt("id");
		    	String  name = rs.getString("name");
		    	int age  = rs.getInt("age");
		    	String  address = rs.getString("address");
		    	float salary = rs.getFloat("salary");
		    	
		    	// Print out retrieved entry
		    	System.out.println( "ID = " + id );
		    	System.out.println( "NAME = " + name );
		    	System.out.println( "AGE = " + age );
		    	System.out.println( "ADDRESS = " + address );
		    	System.out.println( "SALARY = " + salary );
		    	System.out.println();
		    }
		    System.out.println("Operation done successfully!");
			
			// Close all connections to database.
		    rs.close();
			stmt.close();
			c.close();
			
		}
		catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		}
	}

}
