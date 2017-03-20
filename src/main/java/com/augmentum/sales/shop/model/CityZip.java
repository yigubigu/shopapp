package com.augmentum.sales.shop.model;

import org.springframework.util.StringUtils;


public class CityZip {

	private int zip;
	
	private double latitude;
	
	private double lontitude;
	
	private String city;
	
	private String state;
	
	private String county;

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLontitude() {
		return lontitude;
	}

	public void setLontitude(double lontitude) {
		this.lontitude = lontitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	
	public static CityZip buildCityZip(String cityString) {
		if (StringUtils.isEmpty( cityString)) {
			return null;
		}
		CityZip obj = new CityZip();
		
		String[] strs = cityString.split(",");
		if (strs.length == 6) {			
			obj.setZip(Integer.parseInt(strs[0]));
			obj.setLatitude(Double.parseDouble(strs[1]));
			obj.setLontitude(Double.parseDouble(strs[2]));
			obj.setCity(strs[3]);
			obj.setState(strs[4]);
			obj.setCounty(strs[5]);			
		}
		
		
		return obj;
	}
	
}
