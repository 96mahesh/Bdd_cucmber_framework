package com.testperform.web.bdd.Integrations.Common_Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

public class JsonComponent {

	// Verifies whether files are exists in repective path
	public static boolean fileExists(String filePath) {
		File f = new File(filePath);
		return f.exists();
	}

	// Reads the json file - Requesting for the body
	public static String readTextFile(String filePath) throws IOException {

		if (!fileExists(filePath))
			return "";
		FileInputStream inputStream = new FileInputStream(filePath);
		String sTemp = IOUtils.toString(inputStream);
		inputStream.close();
		return sTemp.replace("&amp;", "&");
	}

}
