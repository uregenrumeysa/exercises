package postgresql;
import java.sql.*;
public class PostgresConnect {
	static Connection con;
	public static Connection connect()
	{
		try 
		{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ödev1","postgres","1234*");
			System.out.println("Veritabanýna baðlantý yapýldý");
			
		}
		catch(Exception ex) {
			System.out.println("Hata : " + ex.getMessage());
		}
		return con;
		
	}

}
