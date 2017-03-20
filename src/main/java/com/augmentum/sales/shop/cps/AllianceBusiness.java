package com.augmentum.sales.shop.cps;

import java.util.ArrayList;
import java.util.List;

public class AllianceBusiness {

	private String name;
	private int capital;
	private double cpsTotal;
	private double[] unlockCps;
	
	static List<AllianceBusiness> alliances = new ArrayList<AllianceBusiness>();
	static {
		alliances.add( new AllianceBusiness( "a1", 1000));
		alliances.add( new AllianceBusiness( "a2", 1000));
		alliances.add( new AllianceBusiness( "a3", 2000));
		alliances.add( new AllianceBusiness( "a4", 4000));
		alliances.add( new AllianceBusiness( "a5", 8000));
		alliances.add( new AllianceBusiness( "a6", 16000));
		alliances.add( new AllianceBusiness( "a7", 32000));
		alliances.add( new AllianceBusiness( "a8", 64000));
		alliances.add( new AllianceBusiness( "a9", 128000));
		alliances.add( new AllianceBusiness( "a10", 256000));
		alliances.add( new AllianceBusiness( "a11", 512000));		
	}
	
	public AllianceBusiness(String name, int capital) {
		this.name = name;
		this.capital = capital;
	}

	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}

	public int getCapital() {
		return capital;
	}

	public void setCapital(int capital) {
		this.capital = capital;
	}

	public double getCpsTotal() {
		return cpsTotal;
	}

	public void setCpsTotal(double cpsTotal) {
		this.cpsTotal = cpsTotal;
	}

	public double[] getUnlockCps() {
		return unlockCps;
	}

	public void setUnlockCps(double[] unlockCps) {
		this.unlockCps = unlockCps;
	}

		

}
