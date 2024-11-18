package com.testperform.web.bdd.Integrations.Common_Utils;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JasonReader {

	public String jasonreader(String value) {
		JSONParser parser = new JSONParser();
		String name = "";
		try {
			Object obj = parser.parse(
					new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\files\\sample2.json"));
			JSONObject jsonObject = (JSONObject) obj;
			name = (String) jsonObject.get(value);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("The expected value is not found check jason reader/file once");
		}
		return name;

	}
}
