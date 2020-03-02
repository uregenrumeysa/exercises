

import java.sql.*;
import java.util.Scanner;

import postgresql.PostgresConnect;


// bu sınıd Channel csınıfı gibi olmalı. 
public class Station 
{
    static Scanner scan;
	int id;
	String name;
    
	// bu methodlar DBService classında olmalı.
	public static void StationListele() 
	{
		try
		{
			
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ödev1","postgres","1234*");
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
		System.out.print("Ýstasyon Numarasý(id) Giriniz:");
		int stationId=scan.nextInt();
		System.out.print("Ýstasyon Adý Giriniz:");
		String name=scan.next();
		
		
		try 
		{
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ödev1","postgres","1234*");
			PreparedStatement pstmt=con.prepareStatement(" insert into station values(?,?)");
			pstmt.setInt(1, stationId);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
			System.out.println("Kayýt baþarýyla tamamlandý.");
			
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
		System.out.print("silinecek id yi yazýn:");
		int stationId=scan.nextInt();
		
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ödev1","postgres","1234*");
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
			
			System.out.println("Kayýt silindi.");
		}
		catch(Exception ex) {
			System.err.println("Hata :"+ ex);
			
		}
	}
	

}
