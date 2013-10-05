package com.samir;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ItemXMLHandler extends DefaultHandler {

	Boolean currentElement = false;
	String currentValue = "";
	String item = null;
	private ArrayList<String> itemsList = new ArrayList<String>();

	public ArrayList<String> getItemsList() {
		return itemsList;
	}
 int i = 0;
	// Called when tag starts
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		currentElement = true;
		currentValue = "";

//		if (localName.equals("item")) {
//			item = new String();
//		}
//		
		
		if (localName.equals("Guest")) {
			//item = new String();
			System.out.println("startNode:"+localName +" : "+i++);
		}
		

	}

	// Called when tag closing
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		currentElement = false;
		System.out.println("endElement:"+localName);
		// if (localName.equals("title")) {
			//item.setTitle(currentValue);
		
	}

	// Called to get tag characters
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		if (currentElement) {
			currentValue = currentValue + new String(ch, start, length);
		}
	}

}
