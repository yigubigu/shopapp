package com.augmentum.sales.shop.parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

public class AmazonLinkParser {

	public List parseLink(String url) throws MalformedURLException, IOException {
		HtmlCleaner cleaner = new HtmlCleaner();
		TagNode root = cleaner.clean(new URL(url));
		List nodes = root.getElementListByName("A", true);
		nodes.forEach(item ->{
			
		});
		return nodes;
	}
	
	private String getProductHref(TagNode tagNode) {
		return null;
	}
}
