package com.augmentum;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.augmentum.sales.shop.builder.CityFileBuilder;


@SpringBootApplication
public class ShopApp {

	private static final String url = "http://www.racked.com/maps"; 
	static final String className = "c-river__entry";
	public static void main(String[] args) {
		SpringApplication.run(ShopApp.class, args);
		try {
			/*StoreBuilder.saveCityToFile(StoreBuilder.buildCities());
			StoreBuilder.saveStoreToFile(StoreBuilder.buildStore());*/
			CityFileBuilder.saveCityToFile( CityFileBuilder.buildCities());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	
}
