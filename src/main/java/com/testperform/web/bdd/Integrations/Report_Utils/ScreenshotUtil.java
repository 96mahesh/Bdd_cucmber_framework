package com.testperform.web.bdd.Integrations.Report_Utils;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

	public static String takeScreenshot(WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		String source = ts.getScreenshotAs(OutputType.BASE64);
		return source;
	}

}
