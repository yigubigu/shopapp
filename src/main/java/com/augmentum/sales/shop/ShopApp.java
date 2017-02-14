package com.augmentum.sales.shop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.augmentum.sales.shop.builder.StoreBuilder;
import com.augmentum.sales.shop.parser.CityParser;
import com.augmentum.sales.shop.parser.ShopParser;


@SpringBootApplication
public class ShopApp {

	private static final String url = "http://www.racked.com/maps"; 
	static final String className = "c-river__entry";
	public static void main(String[] args) {
		SpringApplication.run(ShopApp.class, args);
		try {
			StoreBuilder.saveCityToFile(StoreBuilder.buildCities());
			StoreBuilder.saveStoreToFile(StoreBuilder.buildStore());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	
}
