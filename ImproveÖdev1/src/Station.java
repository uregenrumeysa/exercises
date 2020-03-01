

import java.sql.*;
import java.util.Scanner;

import postgresql.PostgresConnect;

public class Station 
{
    static Scanner scan;
	int id;
	String name;
    
	
	public static void StationListele() 
	{
		try
		{
			
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/�dev1","postgres","1234*");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from station");
			System.out.println(" id "+ "  "+ " name ");
			while (rs.next()) 
			{
				
				System.out.println(rs.getInt(1)+"   "+rs.getString(2));
			}
			
		}
		catch(Exception ex) 
		{
			System.err.println("Hata : "+ex);
		}
		
	}
	
	public static void StationEkle() 
	{
		scan=new Scanner(System.in);
		System.out.print("�stasyon Numaras�(id) Giriniz:");
		int stationId=scan.nextInt();
		System.out.print("�stasyon Ad� Giriniz:");
		String name=scan.next();
		
		
		try 
		{
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/�dev1","postgres","1234*");
			PreparedStatement pstmt=con.prepareStatement(" insert into station values(?,?)");
			pstmt.setInt(1, stationId);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
			System.out.println("Kay�t ba�ar�yla tamamland�.");
			
		}
		
		catch(Exception ex) 
		{
			System.out.println("Hata :"+ex);
		}
		
		
		
	}
	public static void StationSil()
	{
		StationListele();
		scan=new Scanner(System.in);
		System.out.print("silinecek id yi yaz�n:");
		int stationId=scan.nextInt();
		
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/�dev1","postgres","1234*");
			PreparedStatement pstmt=con.prepareStatement("DELETE FROM station  WHERE id = ? ");
			Statement stmt=con.createStatement();
			
			
			ResultSet rs=stmt.executeQuery("select * from station ");
			while(rs.next())
			{
				if (rs.getInt("id")==stationId)
				{
					String name1=rs.getString("name");
					pstmt.setInt(1, stationId);
					//pstmt.setString(2, name1);
					pstmt.executeUpdate();
				}
			}
			
			System.out.println("Kay�t silindi.");
		}
		catch(Exception ex) {
			System.err.println("Hata :"+ ex);
			
		}
	}
	

}
