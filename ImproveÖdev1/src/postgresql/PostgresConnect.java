package postgresql;
import java.sql.*;
public class PostgresConnect {
	static Connection con;
	public static Connection connect()
	{
		
		try 
		{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/�dev1","postgres","1234*");
			System.out.println("Veritaban�na ba�lant� yap�ld�");
			
		}
		catch(Exception ex) {
			System.out.println("Hata : " + ex.getMessage());
		}
		return null;
		
	}

}
