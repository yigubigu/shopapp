package com.augmentum.sales.shop;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.augmentum.sales.shop.parser.HtmlParser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopAppTests {

	public void contextLoads() {

	}

	public void verifyXpath() throws MalformedURLException, IOException,
			XPatherException {

		HtmlCleaner cleaner = new HtmlCleaner();
		String url = "https://www.amazon.com/gp/search/ref=sr_nr_p_n_feature_three_br_1/ref=s9_acss_bw_cts_wdnmvd1_T1_w?fst=as:off&rh=n:7141123011,n:7147440011,n:1040660,n:1048188,p_6:ATVPDKIKX0DER,p_n_feature_three_browse-bin:2359303011|2359301011&bbn=1048188&ie=UTF8&qid=1469216114&rnid=2359299011&pf_rd_m=ATVPDKIKX0DER&pf_rd_s=merchandised-search-5&pf_rd_r=FBZJ5KXMBFGSPSMRSF74&pf_rd_t=101&pf_rd_p=272bc66a-8b3e-4f50-afb7-e13b5647cb55&pf_rd_i=1048188";
		String url2 = "https://www.amazon.com/Joes-Jeans-Womens-Midrise-Skinny/dp/B01N9M6FZ1/ref=pd_sbs_193_3?_encoding=UTF8&pd_rd_i=B01N9M6FZ1&pd_rd_r=1PHZZECB5W2BE2J858KT&pd_rd_w=fa9xe&pd_rd_wg=b5mFt&psc=1&refRID=1PHZZECB5W2BE2J858KT";
		URL newurl2 = new URL(url2);
		TagNode node = cleaner.clean(newurl2);
		// String xpath = "/span[@id='productTitle']";
		// Object[] objs = node.evaluateXPath("#productTitle");
		TagNode[] nodes = node.getElementsByName("div", true);
		System.out.println("div " + nodes.length);
	}

	public void verifyXpath2() throws MalformedURLException, IOException,
			XPatherException {

		String url2 = "https://www.amazon.com/Joes-Jeans-Womens-Midrise-Skinny/dp/B01N9M6FZ1/ref=pd_sbs_193_3?_encoding=UTF8&pd_rd_i=B01N9M6FZ1&pd_rd_r=1PHZZECB5W2BE2J858KT&pd_rd_w=fa9xe&pd_rd_wg=b5mFt&psc=1&refRID=1PHZZECB5W2BE2J858KT";
		HtmlParser parser = new HtmlParser(new URL(url2));

		List<TagNode> nodes = parser.getElementByClass("span", "a-size-large");

		if (nodes.size() > 0) {
			TagNode node = nodes.get(0);
			StringBuffer sb = node.getText();
			if (sb != null) {
				System.out.println(sb.toString());
			}
		}
		System.out.println(nodes.size());
	}

	@Test
	public void verifyHref() {

		String url2 = "https://www.amazon.com/gp/search/ref=sr_nr_p_n_feature_three_br_1/ref=s9_acss_bw_cts_wdnmvd1_T1_w?fst=as:off&rh=n:7141123011,n:7147440011,n:1040660,n:1048188,p_6:ATVPDKIKX0DER,p_n_feature_three_browse-bin:2359303011|2359301011&bbn=1048188&ie=UTF8&qid=1469216114&rnid=2359299011&pf_rd_m=ATVPDKIKX0DER&pf_rd_s=merchandised-search-5&pf_rd_r=FBZJ5KXMBFGSPSMRSF74&pf_rd_t=101&pf_rd_p=272bc66a-8b3e-4f50-afb7-e13b5647cb55&pf_rd_i=1048188";
		//String url = "https://www.amazon.cn/s/ref=lp_2152154051_nr_n_0?fst=as:off&rh=n:2016156051,n:!2016157051,n:2152154051,n:2154351051&bbn=2152154051&ie=UTF8&qid=1486696778&rnid=2152154051";
		//String url3 = "https://www.amazon.com/b/ref=s9_acss_bw_cts__T1_w?node=15711392011&lo=fashion&sort=date-desc-rank&pf_rd_m=ATVPDKIKX0DER&pf_rd_s=merchandised-search-5&pf_rd_r=8NGCDGH4SY7B7WC9GYDQ&pf_rd_t=101&pf_rd_p=17b1667e-6438-4ab2-b664-347119cb800a&pf_rd_i=1045024";

		HtmlCleaner cleaner = new HtmlCleaner();
		TagNode root = null;
		List<String> productUrls = new ArrayList<String>();

		try {
			root = cleaner.clean(new URL(url2));
			List nodes = root.getElementListByName("A", true);

			nodes.forEach(item -> {
				Map atts = ((TagNode) item).getAttributes();
				Set<String> keySet = atts.keySet();
				keySet.forEach(key -> {
					if (key.equalsIgnoreCase("href")) {
						String tmpProductUrl = (String) atts.get(key);
						if (tmpProductUrl.contains("https://www.amazon.com/")
								&& tmpProductUrl.contains("/dp/")) {
							productUrls.add(tmpProductUrl);
						}
					}
				});

			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Set<String> productNames = new HashSet<String>();
		
		productUrls.forEach(item ->{
			System.out.println("get content from  " +item);
			try {
				Thread.sleep(1* 1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String productName = productName(item);
			if (productName != null) {
				productNames.add(productName);
			}
		});
		
		productNames.forEach(item->{
			System.out.println(item);
		});
		
		
	}

	private String productName(String url) {

		String productName = null;
		HtmlParser parser;
		try {
			parser = new HtmlParser(new URL(url));
			List<TagNode> nodes = parser.getElementByClass("span", "a-size-large");

			if (nodes.size() > 0) {
				TagNode node = nodes.get(0);
				StringBuffer sb = node.getText();
				if (sb != null) {
					productName = sb.toString();
				}
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

				return productName;
	}

}
