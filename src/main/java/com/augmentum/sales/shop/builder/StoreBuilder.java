package com.augmentum.sales.shop.builder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;

import com.augmentum.sales.shop.model.City;
import com.augmentum.sales.shop.model.Store;
import com.google.gson.Gson;

public class StoreBuilder {

	public static List<Store> buildStore() throws IOException {
		List<Store> stores = new ArrayList<Store>();
		File file = new File("store-name.txt");
		List<String> lines = FileUtils.readLines(file, "UTF-8");
		lines.forEach(item -> {
			Store store = new Store();
			store.setName(item);
			stores.add(store);
		});

		List<City> cities = buildCities();
		for (int i = 0; i < stores.size(); i++) {
			int cityIndex = i % cities.size();
			stores.get(i).setCityName(cities.get(cityIndex).getName());
		}
		return stores;
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

	public static void saveStoreToFile(List<Store> stores) {

		Gson gson = new Gson();
		String jsonStr = gson.toJson(stores);

		File file = new File("stores.json");
		try {

			FileUtils.write(file, jsonStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<City> buildCities() throws IOException {
		List<City> cities = new ArrayList<City>();
		File file = new File("city-rate.csv");
		List<String> lines = FileUtils.readLines(file, "UTF-8");

		lines.forEach(item -> {
			if (!item.startsWith("City,Rate")) {
				City city = City.buildCity(item);
				if (null != city) {
					cities.add(city);
				}
			}
		});

		return cities;
	}

}
