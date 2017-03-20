package com.augmentum.sales.city;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.io.FileUtils;

import org.mockito.internal.util.collections.ArrayUtils;

import com.google.gson.Gson;



public class CityBuilder {
	public static List<CityModel> buildCity() throws IOException {
		List<CityModel> cities = new ArrayList<CityModel>();
		File file = new File("worldcitiespop.csv");
		List<String> lines = FileUtils.readLines(file, "UTF-8");
		lines.forEach(item -> {			
			String[] cityStrs = item.split(",");
			if (cityStrs.length == 7) {
				if (cityStrs[3].equalsIgnoreCase("CA")) {
					CityModel cityModel = new CityModel();
					cityModel.setCity(cityStrs[2]);
					cityModel.setLatitude(Double.parseDouble(cityStrs[5]));
					cityModel.setLongitude(Double.parseDouble(cityStrs[6]));
					cities.add(cityModel);
				}
			}						
		});
		return cities;
	}
	
	public static List<CityModel> filter(List<CityModel> cities) {
		List<CityModel> filteredCities = new ArrayList<CityModel>();
		String[] cityNames = {"Los Angeles","San Diego","Santa Clara", "San Francisco", "Fresno", 
				"Long Beach", "Sacramento","Oakland","Bakersfield", "Anaheim","Santa Ana", 
				"Riverside", "Stockton","Chula Vista", "Fremont","Irvine","San Bernardino" ,
				};
		
		List<String> cityNameList = new ArrayList<String>();
		for (int i=0; i< cityNames.length; i++) {
			cityNameList.add(cityNames[i]);
		}
		
		cities.forEach(item ->{
			if (cityNameList.contains(item.getCity())) {
				filteredCities.add(item);
			}
		});
		
		return filteredCities;
	}
	
	public static void outputJsonString(List<CityModel> cities) {
		Gson gson = new Gson();
		String jsonStr = gson.toJson(cities);
		File file = new File("ca-city.json");
		try {
			FileUtils.writeStringToFile(file, jsonStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
