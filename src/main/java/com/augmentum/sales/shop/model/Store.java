package com.augmentum.sales.shop.model;

import com.google.gson.Gson;

public class Store {

	private String name;

	private String cityName;
	
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	

	public static String toJson(Store object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}

	@Override
	public String toString() {
		return "Store [name=" + name + "]";
	}
	
	
}
