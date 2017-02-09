package com.augmentum.sales.shop.parser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlParser {

    TagNode rootNode;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    public HtmlParser(URL htmlPage) throws IOException
    {
        HtmlCleaner cleaner = new HtmlCleaner();
        rootNode = cleaner.clean(htmlPage);
    }

    List getDivsByClass(String CSSClassname)
    {
        List divList = new ArrayList();

        TagNode divElements[] = rootNode.getElementsByName("div", true);
        for (int i = 0; divElements != null && i < divElements.length; i++)
        {
            String classType = divElements[i].getAttributeByName("class");
            if (classType != null && classType.equals(CSSClassname))
            {
                divList.add(divElements[i]);
            }
        }
        return divList;
    }

}
