package postgresql;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class StationWork 
{
	static Connection con;
	private static Scanner scan;
	public static void main(String[] args) throws SQLException
	// deneme zeka�i
	{
		try
		{
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/�dev1","postgres","1234*");
			
			scan=new Scanner(System.in);
			int secim;
			while (true)
			{
				System.out.print(""
						+ "*********************\n"
						+ "1)�stasyonlar� Listele\n"
						+ "2)�stasyon EKle\n"
						+ "3)Kanallar� Listele\n"
						+ "4)Kanal EKle\n"
						+ "5)�l��mleri Listele\n"
						+ "6)�l��m Kaydet\n"
						+ "7)��k��\n"
						+ "*********************\n ");
				System.out.print("Se�iminiz:");
				secim=scan.nextInt();
				if(secim==1) istasyon_listele();
				if(secim==2) istasyon_ekle();
				if(secim==3) kanalListele();
				if(secim==4) kanal_ekle();
				if(secim==5) �lc�m_listele();
				if(secim==6) �lc�m_kaydet();
				if(secim==7) 
				{
					try
					{
						con.close();
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
					break;
				}
				
			}
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		
	}
	
	public static void istasyon_listele() 
	{
		try
		{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(" select * from station ");
			while(rs.next()) 

		    System.out.println(rs.getInt(1)+"   "+rs.getString(2));
			
		}
		
		catch(Exception e) 
		{
			System.out.println(e);
		}
	}
	
	public static void istasyon_ekle() 
	{
		scan=new Scanner(System.in);
		System.out.print("�stasyon Numaras� Giriniz:");
		int yenino=scan.nextInt();
		System.out.print("�stasyon Ad� Giriniz:");
		String name=scan.next();
		
		try 
		{
			PreparedStatement pstmt=con.prepareStatement(" insert into station values(?,?)");
			pstmt.setInt(1, yenino);
			pstmt.setString(2, name);
			pstmt.executeUpdate(); // bunu unutmu�sun bu olmadan ns�l kaydetsin
			System.out.println("Kay�t ba�ar�yla tamamland�.");
			
		}
		
		catch(Exception e) 
		{
			System.out.println(e);
		}
		
	}
	
	public static void kanalListele()
	{
		try 
		{
			istasyon_listele();
			scan=new Scanner(System.in);
			System.out.print("Hangi istasyon kanal�n� g�r�nt�lemek istiyorsunuz?(hepsini g�rmek istioysan�z e/E):");
			String secim=scan.next();
			Statement stmt=con.createStatement();
			ResultSet rst = null;
			
			if ("e".equals(secim.toLowerCase())) 
			{
				rst=stmt.executeQuery(" select id,name,station_id from channel "); 
				while(rst.next())
				{
					int id=rst.getInt(1);
					String isim=rst.getString(2);
					int station_id=rst.getInt(3);
					System.out.println(" id " + "  "+  "name"+ "   " + "station_id");
					System.out.println(String.valueOf(id)+"   "+ isim+"   "+String.valueOf(station_id));
				}
				
			}
				
			
			else
			{
				//PreparedStatement pstmt= con.prepareStatement("select id,name,station_id from channel where station_id = ?");
				//pstmt.setInt(1, Integer.valueOf(secim));//E�er e girmediyse rakam girmi�tir. �stasyon id oluyor. onu integera �evirdik
				//rst=pstmt.executeQuery("select * from channel");
				rst=stmt.executeQuery("select * from channel");
				while(rst.next())
				{
					
					int id=rst.getInt(1);
					String isim=rst.getString(2);
					int station_id=rst.getInt(3);
					System.out.println(" id " + "  "+  "name"+ "   " + "station_id");
					System.out.println(String.valueOf(id)+"   "+ isim+"   "+String.valueOf(station_id));
				}
				System.out.println("kay�t bulunamad�.");
				
			}
			
			

	
		}
		
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		
	}
	
	private static void main(Object args) {
		// TODO Auto-generated method stub
		
	}

	public static void kanal_ekle() throws SQLException 
	{
		scan=new Scanner(System.in);
		istasyon_listele();
		System.out.print("Hangi istasyona kanal eklemek istiyorsunuz?: ");
		int stationId=scan.nextInt();
		System.out.print("Ne eklemek istiyorsunuz?(ph,s�cakl�k,..):");
		String name=scan.next();
		try 
		{
			PreparedStatement pstmt=con.prepareStatement(" insert into channel (name,station_id) values(?,?)");
			pstmt.setInt(2, stationId);
			pstmt.setString(1, name);
			
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("kay�t yap�ld�");
			
	
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}

		
	}
	
	public static void �lc�m_listele()
	{
		try 
		{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select measure_date,channel_id,value from measure");
			while (rs.next()) 
			{
				String date=rs.getString(1);
				int channel_id=rs.getInt(2);
				int value=rs.getInt(3);
				System.out.println(" id " + "  "+  "name"+ "   " + "station_id");
				System.out.println(date+"   "+ String.valueOf(channel_id)+"   "+String.valueOf(value));
				
			}
			
		}
		
		catch(Exception e) 
		{
			System.out.println(e);
			
		}
		
		
		
	}
	
	public static void �lc�m_kaydet() 
	{
		
		
		try 
		{
			
			//System.out.print("hangi �l��mleri kay�t edeceksiniz(ph,s�cakl�k):");
			
			kanalListele();
			int channelId =scan.nextInt();
			System.out.print("Tarih giriniz(??/??/????):");
			String strDate=scan.next();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
			java.util.Date date = sdf.parse(strDate);
			java.sql.Date sqlDate = new Date(date.getTime());
			
			System.out.print("�l��len de�erini giriniz:");
			double value=scan.nextInt();
			
			PreparedStatement pstmt=con.prepareStatement(" insert into measure (measure_date,channel_id,value) values (?,?,?)");
			//Statement stmt=con.createStatement();
			//ResultSet rs=stmt.executeQuery("select * from channel ");
			pstmt.setDate(1, sqlDate);
			pstmt.setInt(2, channelId);
			pstmt.setDouble(3, value);
			pstmt.executeUpdate();
			
			
			
			System.out.println("kay�t ba�ar�l�");
			
			
		}
		
		catch(Exception e) 
		{
			System.out.println(e);
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
}
