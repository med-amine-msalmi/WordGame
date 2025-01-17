package dataAccess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
private static final String Host="localhost";
private static final int Port=3306;
private static final String DataBaseName="Game";
private static final String UserName="root";
private static final String Password="";

private static Connection connection;
public static Connection GetConnection()
	{
		try {
			connection=DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",Host,Port,DataBaseName),UserName,Password);
		
		}catch(SQLException  ex)
		{
		ex.printStackTrace();
		}

		return connection;
	}

}
