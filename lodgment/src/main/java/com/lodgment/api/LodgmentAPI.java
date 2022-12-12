package com.lodgment.api;

import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class LodgmentAPI {
	
	final String serviceKey="i78uTwdXPPgqdYiKZ6HLsD9TvQcb0SPTGZjcUly%2BYIZHlfKwrKa7SDDbBitBH5TSmWJqM45XhnPsdbFQO74U3w%3D%3D";

	public static String getTagValue(String tag, Element eElement) {
		try {
			NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
			Node nValue = (Node) nlList.item(0);
			if(nValue == null) {
				System.out.println("null");
				return null;
			}
			System.out.println(nValue.getNodeValue());
			return nValue.getNodeValue();
		} catch(NullPointerException e) {
			System.out.println("catch");
			return null;
		}
	}
}
