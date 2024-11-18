package com.testperform.web.bdd.common.hooks;

import java.util.ArrayList;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.testperform.web.bdd.Integrations.Common_Utils.ConfigReader;
import com.testperform.web.bdd.Integrations.Common_Utils.DriverFactory;
import com.testperform.web.bdd.Integrations.Report_Utils.ReportManager;

//import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

//import com.web.bdd.Integrations.Common_Utils.ConfigReader;
//import com.web.bdd.Integrations.Common_Utils.DriverFactory;
//import com.web.Integrations.Report_Utils.ReportManager;
//
//import io.cucumber.core.api.Scenario;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;

public class CucumberHooks {
	public static ArrayList<String> passedTests = new ArrayList<String>();
	public static ArrayList<String> failedTests = new ArrayList<String>();
	public static ArrayList<String> totalTestCases = new ArrayList<String>();

	// private static String str_Execution_TYPE = ConfigReader.getValue("execType");
	private static String str_Execution_TYPE = "Web_UI";
	// public String str_BrowserType = ConfigReader.getValue("Browser");
	public String str_BrowserType = System.getProperty("Browser");
	DriverFactory driverFactory = DriverFactory.getInstance();
	// public static String strBrowser = "Chrome";
	// public static String strBrowser = "Firefox";
	public static String featureFileName;

	@Before
	public void before(Scenario scenario) throws Exception {
		System.out.println("+++++++++++++++++++before hooks++++++++++++++++++");

		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
			ReportManager.startTest(scenario.getName(), "@SMOKE");
			System.out.println("Execution started @ " + str_BrowserType + " browser & for type : Web UI");
			try {
				System.out.println("Browser in BeforeHooks");
				driverFactory.setWebDriver(str_BrowserType.trim());
				System.out.println("Browser after  setwebdriver");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new Exception("[-] Please set exection type[Web_UI] value in cucumberhooks class line number 21");
		}

		/**
		 * ReportManager.createAPI_Node(scenario.getName()); String str_Features =
		 * scenario.getId().split(";")[0]; String[] str_arryFeature =
		 * str_Features.split("features/"); System.out.println(str_arryFeature);
		 * String[] str_Feature = str_arryFeature[1].split(".feature"); featureFileName
		 * = str_Feature[0]; System.out.println(str_Feature[0]);
		 **/
	}

	@After
	public void after(Scenario scenario) throws Exception {
		System.out.println("+++++++++++++++++++after hooks+++++++++++++++++++");

		/*
		 * if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
		 * System.out.println("+++++++++++++++++++Web_UI+++++++++++++++++++");
		 * totalTestCases.add(scenario.getName()); if (scenario.isFailed()) {
		 * failedTests.add(scenario.getName()); ReportManager.logFail(scenario.getName()
		 * + " Test case Fail"); System.out.println("Test Fail: " + scenario.getName());
		 * } else {
		 * System.out.println("+++++++++++++++++++++onTestSuccess++++++++++++++++++++");
		 * System.out.println("Test Success: " + scenario.getName());
		 * ReportManager.logPass(scenario.getName() + " Test case passed");
		 * ReportManager.endCurrentTest();
		 * 
		 * driverFactory.getWebDriver().quit(); passedTests.add(scenario.getName()); }
		 * 
		 * }
		 */
		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
			totalTestCases.add(scenario.getName());
			if (totalTestCases.contains(scenario.getName())) {
				System.out.println("Removing TC from totalTestCases: " + scenario.getName());
				totalTestCases.remove(scenario.getName());
			}
			if (failedTests.contains(scenario.getName())) {
				System.out.println("Removing TC from failedTests: " + scenario.getName());
				failedTests.remove(scenario.getName());
			}
			if (passedTests.contains(scenario.getName())) {
				System.out.println("Removing TC from passedTests: " + scenario.getName());
				passedTests.remove(scenario.getName());
			}
			if (scenario.isFailed()) {
				failedTests.add(scenario.getName());
				final byte[] screenshot = ((TakesScreenshot) DriverFactory.getInstance().getWebDriver())
						.getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", featureFileName);
				scenario.log("ScreenShot Attached");
			} else {
				passedTests.add(scenario.getName());
			}

		} else {
			throw new Exception("[-] Please set exection type[Web_UI] value in cucumberhooks class line number 21");
		}

	}

}
