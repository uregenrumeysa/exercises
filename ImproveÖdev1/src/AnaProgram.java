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
					+ "1)�stasyonlar� Listele\n"
					+ "2)�stasyon Ekle\n"
					+ "3)�stasyon Sil\n"
					+ "4)Kanallar� Listele\n"
					+ "5)Kanal Ekle\n"
					+ "6)Kanal Sil\n"
					+ "7)�l��mleri Listele\n"
					+ "8)�l��m Kaydet\n"
					+ "9)�l��m Sil\n"
					+ "10)��k��\n"
					+ "*********************\n ");
			System.out.print("Se�iminiz:");
			int secim=scan.nextInt();
			
			
			switch(secim) 
			{
			case 1:
				Station.StationListele();
				break;
			case 2:
				Station.StationEkle();
				break;
			case 3:
				Station.StationSil();
				break;
			
				
				
				
			}
			System.out.print("Yapmak istedi�iniz ba�ka bir �ey var m�?(e/E veya h/H):");
			String soru1=scan.next();
			soru=soru1;
		}
		System.out.println("Mutlu g�nler :)");
		
		
		
	}

	private static void StationSil() {
		// TODO Auto-generated method stub
		
	}

}
