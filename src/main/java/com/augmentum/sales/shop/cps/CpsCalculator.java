package com.augmentum.sales.shop.cps;

import java.util.ArrayList;
import java.util.List;

public class CpsCalculator {

	public static final int RATE = 15;

	public static final int YEAR_TOTAL = 10;
	public static final double INIT_TOTAL_STOCK = 100;
	
	public static final float[] CPS_UNLOCK_RATE = { 0.20f, 0.15f, 0.10f, 0.10f, 0.10f, 0.10f, 0.10f, 0.05f, 0.05f, 0.05f};

	List<Double> stockPrices = new ArrayList<Double>();		
	List<Double> stocks = new ArrayList<Double>();
	
	/*
	 * calculate each alliance cps
	 */
	public void calculateCps() {
		List<Double> cpss = new ArrayList<Double>();
		List<AllianceBusiness> alliances = AllianceBusiness.alliances;		
		stocks.clear();
		stockPrices.clear();
		stocks.add(INIT_TOTAL_STOCK);
		for (int i=0; i<alliances.size(); i++) {
			
			double totalCapital = calculateCapitalByYear(i, alliances);
			double stockCurrentYear = stocks.get(i);
			double price = totalCapital * RATE / stockCurrentYear;
			stockPrices.add(price);
			double cps = alliances.get(i).getCapital() * YEAR_TOTAL / price;
			alliances.get(i).setCpsTotal(cps);
			double nextYearStockTotal = calculateNextYearUnlockCps(i+1,  alliances);
			stocks.add( stockCurrentYear+ nextYearStockTotal);
		}
				
		alliances.forEach(item -> {
			System.out.println("Name:" + item.getName() +",cps: " + String.format("%.2f", item.getCpsTotal() ) );
		});
		
		
		
	}
	
	/**
	 * Calculate the unlock cps for the given year 
	 * @param year
	 * @param alliances
	 * @return
	 */
	private double calculateNextYearUnlockCps(int year,List<AllianceBusiness> alliances) {
		double totalUnlockCps =0;
		if (year == 11) {
			return 0;
		}
		for (int i=0; i<year; i++) {
			try {
				totalUnlockCps +=  alliances.get(i).getCpsTotal()* (double)CPS_UNLOCK_RATE [year -i-1];
			} catch (Exception ex) {
				System.out.println("year: " + year);
				System.out.println("index" + i);
				
			}
		}
		return totalUnlockCps;
	}


	/**
	 * Calculate total capital for the given year 
	 * @param year
	 * @param alliances
	 * @return
	 */
	private double calculateCapitalByYear(int year, List<AllianceBusiness> alliances ) {
		double totalCapital = 0;
		if (year == 0) {
			totalCapital = alliances.get(0).getCapital();
		} else {
			for (int i = 0; i <= year; i++) {
				totalCapital =  totalCapital + alliances.get(i).getCapital();
			}
		}
		
		
		return totalCapital;
	}
	
	public List<Double> stockPrice() {
		List<Double> prices = new ArrayList<Double>();
						
		return prices;
	}
	
	public double getFirstYearStockPrice() {
		
		double price = AllianceBusiness.alliances.get(0).getCapital() * RATE / 100;
		
		return price;
	}
	
	public double getFirstAllianceCps() {
		double cps = AllianceBusiness.alliances.get(0).getCapital() * 10 / getFirstYearStockPrice();
		return cps;
	}
	
	public double getFirstYearEndStockTotal() {
		return INIT_TOTAL_STOCK + getFirstAllianceCps() * CPS_UNLOCK_RATE[0];
	}
}
