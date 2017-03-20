package com.augmentum.sales.shop.model;

import org.springframework.util.StringUtils;

public class CityRate {
	
	private String city;
	
	private double rate;
	
	private String county;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	
	public static City buildCity(CityRate source) {
		City dest = new City();
		dest.setCounty(source.getCounty());
		dest.setName(source.getCity());
		dest.setRate(source.getRate());
		return dest;
	}
	
	public static CityRate buildCityRate(String cityString) {
		if (StringUtils.isEmpty( cityString)) {
			return null;
		}
		// Aguanga,7.75%,Riverside
		CityRate obj = new CityRate();
		
		String[] strs = cityString.split(",");
		
		if (strs.length == 3) {
			obj.setCity(strs[0]);
			try {
				String rate = strs[1].substring(0, strs[1].length() - 1);
				obj.setRate(Double.parseDouble(rate) / 100);
			} catch (NumberFormatException nfex) {
			}
			obj.setCounty(strs[2]);
		}
		
		return obj;
	}

}
