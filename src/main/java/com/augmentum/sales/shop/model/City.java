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
	
	private static double DEFAULT_LATITUDE = 34.052234;
	private static double  COEFFICIENT = 1;
			
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

	public static Double[] getTemp(double latitude) {
		//34.052234
		//13.2,13.9,15.2,16.6,18.2,20.0,22.8,22.9,22.2,19.7,17.1,14.6
		
		double[] fixed = {calcF(13.2), calcF(13.9),calcF(15.2),calcF(16.6),
				calcF(18.2),calcF(20.0),calcF(22.8),calcF(22.9),calcF(22.2),calcF(19.7),calcF(17.1),calcF(14.6)};	
		
		double delta = latitude - DEFAULT_LATITUDE;
		
		Double[] newVal = new Double[fixed.length];
		for (int i=0 ; i < newVal.length; i++) {
			newVal[i] = fixed[i] * COEFFICIENT;
		}
		
		
		
		return newVal;
	}	
	
	
	
	public static City buildCity(String cityString) {
		if (StringUtils.isEmpty( cityString)) {
			return null;
		}
		// Aguanga,7.75%,Riverside
		City city = new City();
		
		String[] strs = cityString.split(",");
		if (strs.length == 3) {
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
