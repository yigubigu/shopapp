package com.augmentum.sales.shop.parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShopParser {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	public List<String> parseShop(String url) {
		logger.debug("parse shop from url " + url);
		List<String> shops = new ArrayList<String>();

		HtmlParser parser;
		try {
			parser = new HtmlParser(new URL(url));
			TagNode root = parser.rootNode;
			List list = root.getElementListByName("h2", true);
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				TagNode h2Element = (TagNode) iterator.next();
				List children = h2Element.getChildren();
				if (children.size() >= 2) {
					Object obj = children.get(2);
					String storeName = obj.toString();
					shops.add(storeName);
				}
			}

		} catch (MalformedURLException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}

		return shops;

	}
}
