package com.augmentum.sales.city.parser;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WikiCityParserTest {

	@Test
	public void verifyarseCity() throws MalformedURLException, IOException {
		WikiCityParser wiki = new WikiCityParser();
		wiki.parseCity(WikiCityParser.CA_URL);
		
		
	}
}
