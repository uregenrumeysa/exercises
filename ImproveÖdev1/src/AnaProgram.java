import java.util.Scanner;

import postgresql.PostgresConnect;

//import postgresql.PostgresConnect;

public class AnaProgram {
	static public void main(String[] args) {
		String soru="e";
	
		while("e".equals(soru.toLowerCase())) {
			Scanner scan=new Scanner(System.in);
			System.out.print(""
					+ "*********************\n"
					+ "1)Ýstasyonlarý Listele\n"
					+ "2)Ýstasyon Ekle\n"
					+ "3)Ýstasyon Sil\n"
					+ "4)Kanallarý Listele\n"
					+ "5)Kanal Ekle\n"
					+ "6)Kanal Sil\n"
					+ "7)Ölçümleri Listele\n"
					+ "8)Ölçüm Kaydet\n"
					+ "9)Ölçüm Sil\n"
					+ "10)Çýkýþ\n"
					+ "*********************\n ");
			System.out.print("Seçiminiz:");
			int secim=scan.nextInt();
			DBService dbService = new DBService();
			
			switch(secim) 
			{
			case 1:// Böyle DB Serviceten çağrılacak. 
				dbService.stationListele();
				break;
			case 2:
				Station.StationEkle();
				break;
			case 3:
				Station.StationSil();
				break;
			
				
				
				
			}
			System.out.print("Yapmak istediðiniz baþka bir þey var mý?(e/E veya h/H):");
			String soru1=scan.next();
			soru=soru1;
		}
		System.out.println("Mutlu günler :)");
		
		
		
	}

	private static void StationSil() {
		// TODO Auto-generated method stub
		
	}

}
