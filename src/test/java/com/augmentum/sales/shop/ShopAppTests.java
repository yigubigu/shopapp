package com.augmentum.sales.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.augmentum.sales.shop.parser.ShopParser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopAppTests {

	@Test
	public void contextLoads() {
		ShopParser shopParser = new ShopParser();
		shopParser.parseShop("http://www.racked.com/maps/shopping-aspen-indie-boutiques");
		
	}

}
