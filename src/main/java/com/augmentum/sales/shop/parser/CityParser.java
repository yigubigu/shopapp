package com.augmentum.sales.shop.parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CityParser {

	private static final String DIV_CLASS = "c-river__entry ";
	private static final String URL_CLASS = "c-entry-box__image-wrapper";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Set<String> getCityUrl (String mapUrl) {
		logger.debug("parse city url:  " + mapUrl);
		Set<String> cityUrl = new HashSet<String>();
		
		try {
			HtmlParser parser = new HtmlParser(new URL (mapUrl));
			List divs = parser.getDivsByClass(DIV_CLASS);
			
			for (Iterator iterator = divs.iterator(); iterator.hasNext();)
            {
                TagNode divElement = (TagNode) iterator.next();
                TagNode[] children = divElement.getAllElements(true);
                for (int i= 0; i <children.length; i++) {
                	if (children[i].getName().equalsIgnoreCase("a")) {
                		String href = children[i].getAttributeByName("href");
                		cityUrl.add(href);                		
                		ShopParser shopParser = new ShopParser();
                		shopParser.parseShop(href);
                	}
                }
                Map atts= divElement.getAttributes();
                System.out.println("t");
            }
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			logger.error("cannot paser url", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("cannot get content", e);
		}
		return cityUrl;
	}
	
}
