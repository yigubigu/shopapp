package com.augmentum.sales.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.augmentum.sales.shop.parser.CityParser;
import com.augmentum.sales.shop.parser.ShopParser;


@SpringBootApplication
public class ShopApp {

	private static final String url = "http://www.racked.com/maps"; 
	static final String className = "c-river__entry";
	public static void main(String[] args) {
		SpringApplication.run(ShopApp.class, args);
		
		CityParser cityParser = new CityParser();
		Set<String> cities = cityParser.getCityUrl(url);
		List<String> shops =  new ArrayList<String>();
		cities.forEach(city ->{
			if (null != city) {						
				System.out.println("city url" + city);
				ShopParser shopParser = new ShopParser();
				shops.addAll(shopParser.parseShop(city));
			}
		});
		
		shops.forEach(shop-> {
			System.out.println(shop);
		});
	}
}
