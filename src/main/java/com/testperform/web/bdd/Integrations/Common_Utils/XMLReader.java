package com.testperform.web.bdd.Integrations.Common_Utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLReader {

	public String xmlReader(String nodename, String nodevalue) {
		String Data = "";
		try {

			File file = new File((System.getProperty("user.dir")+"\\src\\test\\resources\\files\\employee.xml"));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName(nodename);
			Node node = nodeList.item(0);
			Element eElement = (Element) node;
			Data = eElement.getElementsByTagName(nodevalue).item(0).getTextContent();

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return Data;

	}
}
