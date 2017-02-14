package com.augmentum.sales.shop.model;

import java.util.ArrayList;

import java.util.List;

import org.mockito.internal.util.collections.ArrayUtils;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;

public class City {

	private String name;
	private String county;
	private double rate;
	private double latitude;
	private double longitude;

	private Double[] temperature;
	
	// city location http://www.latlong.net/

	
	private static double calcF(double c) {

		return (double) Math.round((9 * c / 5 + 32) * 100) / 100;
	}

	public Double[] getTemperature() {
		return temperature;
	}

	public void setTemperature(Double[] temperature) {
		this.temperature = temperature;
	}

	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRate() {

		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public static String toJson(City object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}

	public static City buildCity(String cityString) {
		if (StringUtils.isEmpty( cityString)) {
			return null;
		}
		// Aguanga,7.75%,Riverside
		City city = new City();

		System.out.println(cityString);
		
		String[] strs = cityString.split(",");
		if (strs.length == 17) {
			city.setName(strs[0]);
			try {
				String rate = strs[1].substring(0, strs[1].length() - 1);
				city.setRate(Double.parseDouble(rate) / 100);
			} catch (NumberFormatException nfex) {
			}
			city.setCounty(strs[2]);
			city.setLatitude(Double.parseDouble(strs[3]));
			city.setLongitude(Double.parseDouble(strs[4]));

			List<Double> temperatures = new ArrayList<Double>();
			for (int i=5; i< 17; i ++) {
				temperatures.add(calcF(Double.parseDouble(strs[i])));
			}
			
			city.setTemperature(temperatures.toArray(new Double[temperatures.size()]));
							
		}

		return city;
	}

}
