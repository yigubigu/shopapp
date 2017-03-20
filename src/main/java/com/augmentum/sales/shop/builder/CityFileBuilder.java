package com.augmentum.sales.shop.builder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.augmentum.sales.shop.model.City;
import com.augmentum.sales.shop.model.CityRate;
import com.augmentum.sales.shop.model.CityZip;
import com.google.gson.Gson;

public class CityFileBuilder {

	public static List<City> buildCities()throws IOException {
		List<City> cities = new ArrayList<City>();
		 
		
		List<CityRate> cityRates = buildCityRate();
		List<CityZip> cityZipes = buildCityZip();
		
			
		cityRates.forEach(item ->{
			City city = CityRate.buildCity(item);
			CityZip cityZip = getLocation(city.getName(), cityZipes);
			if (cityZip != null) {
				city.setLatitude(cityZip.getLatitude());
				city.setLongitude(cityZip.getLontitude());
				cities.add(city);
			}
			city.setTemperature(City.getTemp(city.getLatitude()));
		});
		
		return cities;
	}
	
	
	public static void saveCityToFile(List<City> cities) {
		Gson gson = new Gson();
		String jsonStr = gson.toJson(cities);
		File file = new File("city.json");
		try {
			FileUtils.writeStringToFile(file, jsonStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static CityZip getLocation(String cityName, List<CityZip> cityZip) {		
		Iterator<CityZip> it = cityZip.iterator();
		while (it.hasNext()) {
			CityZip item = it.next();
			if (item.getCity().equalsIgnoreCase(cityName)) {
				return item;
			}
		}
		return null;		
	}
	
	public static List<CityRate> buildCityRate() throws IOException {
		List<CityRate> citieRates = new ArrayList<CityRate>();
		File file = new File("Jan2017_City_Rates.csv");
		List<String> lines = FileUtils.readLines(file, "UTF-8");
		lines.forEach(item -> {
			if (!item.startsWith("City,Rate")) {
				CityRate cityRate = CityRate.buildCityRate(item);
				if (null != cityRate) {
					citieRates.add(cityRate);
				}
			}
		});		
		return citieRates;
	}
	
	public static List<CityZip> buildCityZip() throws IOException {
		List<CityZip> cityZips = new ArrayList<CityZip>();
		File file = new File("city-zip.csv");
		List<String> lines = FileUtils.readLines(file, "UTF-8");
		lines.forEach(item -> {
			// ignore first line
			if (!item.startsWith("ZIP")) {
				CityZip obj = CityZip.buildCityZip(item);
				if (null != obj) {
					cityZips.add(obj);
				}
			}
		});		
		return cityZips;
	}
	
	

}
