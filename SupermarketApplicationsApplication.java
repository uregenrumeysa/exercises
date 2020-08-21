package com.supermarket.application.supermarketapplications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Statement;
import java.util.Scanner;

@SpringBootApplication
public class SupermarketApplicationsApplication {

	@Autowired
	ProductRepository productRepository;

	private static final Logger log = LoggerFactory.getLogger(SupermarketApplicationsApplication.class);

	public static void main(String[] args) { SpringApplication.run(SupermarketApplicationsApplication.class, args); }


	public static void Listele()
	{
		Scanner scannerr=new Scanner(System.in);
		System.out.println(""
				+ "*********************\n"
				+ "1)Sepete Ürün Ekle\n"
				+ "2)Sepetten Ürün Sil\n"
				+ "3)Alışveriş Sepetini Düzenle\n"
				+ "4)Sepetini Kontrol Et\n"
				+ "10)Çıkış\n"
				+ "*********************\n "
		);
		System.out.print("Yapmak İstediğiniz İşlemi Seçin:");
		int choose =scannerr.nextInt();


	};


	@Bean
	public CommandLineRunner supermarketDemo(CartRepository cartRepository,ProductRepository productRepository) {
		return (args) -> {

			Scanner scanner=new Scanner(System.in);
			Cart cart = null;
			System.out.print("Ne kadar paranız var?:");
			int balance=scanner.nextInt();
			Iterable<Product> products = productRepository.findAll();
			for (Product product:products) {
				System.out.println(product);
				
			}

			System.out.print("Mağazaya Eklemek İstediğiniz Ürün Var Mı?(e/h):");
			String cevap=scanner.next();

			while ("e".equals(cevap.toLowerCase())){

				System.out.print("Ekleyeceğiniz Ürünün Adı:");
				String productss = scanner.next();
				System.out.println("");
				System.out.print("Ekleyeceğiniz Ürünün Kilo Fiyatı:");
				double price = scanner.nextDouble();
				Product productt = productRepository.save(new Product(productss, price));
				System.out.println("Ürün Başarıyla Eklendi...");

				System.out.print("Eklemeye devam etmek istiyor musunuz?(e/h):");
				String choosee = scanner.next();
				cevap = choosee;

			}



			System.out.println(""
					+ "*********************\n"
					+ "1)Ürünleri Listele\n"
					+ "2)Sepete Ürün Ekle\n"
					+ "3)Sepetten Ürün Sil\n"
					+ "4)Alışveriş Sepetini Düzenle\n"
					+ "5)Sepetini Kontrol Et\n"
					+ "6)Çıkış\n"
					+ "*********************\n "
			);
			System.out.print("Yapmak İstediğiniz İşlemi Seçin:");
			int choose =scanner.nextInt();



			if(choose==2){

			}

			if(choose==1){
				System.out.println("Ürünler yükleniyor...");
				System.out.println("---------------------------");
				System.out.println(""
						+"***********************\n"
						+"ÜRÜN              FİYAT\n"
				);





				System.out.println();
				Listele();
			}
			else if(choose==2) {
				String choose1="e";
				while("e".equals(choose1.toLowerCase())) {
					System.out.print("Ekleyeceğiniz Ürünün Adı:");
					String productss = scanner.next();
					System.out.println("");
					System.out.print("Ekleyeceğiniz Ürünün Kilo Fiyatı:");
					double price = scanner.nextDouble();
					Product productt = productRepository.save(new Product(productss, price));

					cart.addProduct(new Product(productss, price));
					cartRepository.save(cart);
					System.out.println("Ürün Başarıyla Eklendi...");

					System.out.print("Eklemeye devam etmek istiyor musunuz?(e/h):");
					String choosee = scanner.next();
					choose1 = choosee;
				}
			}






			scanner.close();
		};


	}
}


