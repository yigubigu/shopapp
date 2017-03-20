package com.augmentum.sales.city.parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

public class WikiCityParser {

	public static final String CA_URL = "https://en.wikipedia.org/wiki/List_of_cities_and_towns_in_California";
	
	public void parseCity(String url) throws MalformedURLException, IOException {
		HtmlCleaner cl = new HtmlCleaner();
		TagNode node = cl.clean(new URL(url));
		List<TagNode> trs = node.getElementListByName("th", true);
		trs.forEach(item ->{
			
		});
		
	}
}
