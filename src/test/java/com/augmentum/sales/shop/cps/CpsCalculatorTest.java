package com.augmentum.sales.shop.cps;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CpsCalculatorTest {
	
	
	@Test
	public void verifyGetFirstYearStockPrice() {
		CpsCalculator calc = new CpsCalculator();
		Assert.assertEquals(150,  calc.getFirstYearStockPrice(), 0.01);
	}
	
	@Test
	public void verifyGetFirstAllianceCps() {
		CpsCalculator calc = new CpsCalculator();
		Assert.assertEquals(66.67, calc.getFirstAllianceCps(), 0.01);
	}
	
	@Test
	public void verifyGetFirstYearEndStockTotal() {
		CpsCalculator calc = new CpsCalculator();
		Assert.assertEquals(113.33 , calc.getFirstYearEndStockTotal(), 0.01);
	}
	
	@Test
	public void verifyCalculateCps() {
		CpsCalculator calc = new CpsCalculator();
		calc.calculateCps();
	}
}
