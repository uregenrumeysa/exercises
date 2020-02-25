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
	// deneme zekaþi
	{
		try
		{
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ödev1","postgres","1234*");
			
			scan=new Scanner(System.in);
			int secim;
			while (true)
			{
				System.out.print(""
						+ "*********************\n"
						+ "1)Ýstasyonlarý Listele\n"
						+ "2)Ýstasyon EKle\n"
						+ "3)Kanallarý Listele\n"
						+ "4)Kanal EKle\n"
						+ "5)Ölçümleri Listele\n"
						+ "6)Ölçüm Kaydet\n"
						+ "7)Çýkýþ\n"
						+ "*********************\n ");
				System.out.print("Seçiminiz:");
				secim=scan.nextInt();
				if(secim==1) istasyon_listele();
				if(secim==2) istasyon_ekle();
				if(secim==3) kanalListele();
				if(secim==4) kanal_ekle();
				if(secim==5) ölcüm_listele();
				if(secim==6) ölcüm_kaydet();
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
		System.out.print("Ýstasyon Numarasý Giriniz:");
		int yenino=scan.nextInt();
		System.out.print("Ýstasyon Adý Giriniz:");
		String name=scan.next();
		
		try 
		{
			PreparedStatement pstmt=con.prepareStatement(" insert into station values(?,?)");
			pstmt.setInt(1, yenino);
			pstmt.setString(2, name);
			pstmt.executeUpdate(); // bunu unutmuþsun bu olmadan nsýl kaydetsin
			System.out.println("Kayýt baþarýyla tamamlandý.");
			
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
			System.out.print("Hangi istasyon kanalýný görüntülemek istiyorsunuz?(hepsini görmek istioysanýz e/E):");
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
				//pstmt.setInt(1, Integer.valueOf(secim));//Eðer e girmediyse rakam girmiþtir. Ýstasyon id oluyor. onu integera çevirdik
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
				System.out.println("kayýt bulunamadý.");
				
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
		System.out.print("Ne eklemek istiyorsunuz?(ph,sýcaklýk,..):");
		String name=scan.next();
		try 
		{
			PreparedStatement pstmt=con.prepareStatement(" insert into channel (name,station_id) values(?,?)");
			pstmt.setInt(2, stationId);
			pstmt.setString(1, name);
			
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("kayýt yapýldý");
			
	
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}

		
	}
	
	public static void ölcüm_listele()
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
	
	public static void ölcüm_kaydet() 
	{
		
		
		try 
		{
			
			//System.out.print("hangi ölçümleri kayýt edeceksiniz(ph,sýcaklýk):");
			
			kanalListele();
			int channelId =scan.nextInt();
			System.out.print("Tarih giriniz(??/??/????):");
			String strDate=scan.next();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
			java.util.Date date = sdf.parse(strDate);
			java.sql.Date sqlDate = new Date(date.getTime());
			
			System.out.print("Ölçülen deðerini giriniz:");
			double value=scan.nextInt();
			
			PreparedStatement pstmt=con.prepareStatement(" insert into measure (measure_date,channel_id,value) values (?,?,?)");
			//Statement stmt=con.createStatement();
			//ResultSet rs=stmt.executeQuery("select * from channel ");
			pstmt.setDate(1, sqlDate);
			pstmt.setInt(2, channelId);
			pstmt.setDouble(3, value);
			pstmt.executeUpdate();
			
			
			
			System.out.println("kayýt baþarýlý");
			
			
		}
		
		catch(Exception e) 
		{
			System.out.println(e);
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
}
