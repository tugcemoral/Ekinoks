import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBconnection {

	Connection connection = null;
	Statement stmt=null;
	public void connectDB(){
		
	
		try {
		    // Load the JDBC driver
		    String driverName = "oracle.jdbc.driver.OracleDriver";
		    Class.forName(driverName);
	
		    // Create a connection to the database
		    String serverName = "192.168.2.5";
		    String portNumber = "1521";
		    String sid = "orcl";
		    String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
		    String username = "EVREN";
		    String password = "EVREN";
		    connection = DriverManager.getConnection(url, username, password);
		    stmt=connection.createStatement();
		} catch (ClassNotFoundException e1) {
		    // Could not find the database driver
		} catch (SQLException e2) {
		    // Could not connect to the database
		}
	}
}

