package com.testperform.web.bdd.Integrations.Common_Utils;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public final class ScreenshotUtils {

	private ScreenshotUtils() {
	}

	public static String getBase64Image() throws WebDriverException, SQLException {
		return ((TakesScreenshot) DriverManager.getDriver(null)).getScreenshotAs(OutputType.BASE64);
	}

	public static String takeScreenshot(WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		String source = ts.getScreenshotAs(OutputType.BASE64);
		return source;
	}

}
