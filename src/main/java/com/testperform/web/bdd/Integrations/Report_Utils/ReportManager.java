package com.testperform.web.bdd.Integrations.Report_Utils;

import static com.testperform.web.bdd.constants.FrameworkConstants.ICON_LAPTOP;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.testperform.web.bdd.Integrations.Common_Utils.DriverFactory;
import com.testperform.web.bdd.constants.FrameworkConstants;

public class ReportManager {

	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static Map<Long, ExtentTest> testMap = new HashMap<>();
	public static Map<String, ExtentTest> extentMap = new HashMap<>();

	public static void startReport() {

		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
			String dateStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
//                            htmlReporterWeb = new ExtentSparkReporter(System.getProperty("user.dir") + "/Execution_Reports/Web_Reports/Web.html"+
//                                                   dateStamp + "/" + "QOBOX-" + timeStamp + ".html");

//                           
			ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());
			extent.attachReporter(spark);

			// spark.config().setEncoding("utf-8");
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle(FrameworkConstants.getProjectName() + " - ALL");
			spark.config().setReportName(FrameworkConstants.getProjectName() + " - ALL");

			extent.setSystemInfo("Organization", "TestPerform");
//			extent.setSystemInfo("Employee",
//					"<b> Test User1</b>" + " " + ICON_SOCIAL_LINKEDIN + " " + ICON_SOCIAL_GITHUB);
			extent.setSystemInfo("Domain", "Engineering (IT - Software)" + "  " + ICON_LAPTOP);
			extent.setSystemInfo("Skill", "Test Automation Engineer");

		}

	}

	public static void startTest(String testName, String categories) {
//                      if(extentMap.containsKey(testName)) {
//                                 extent.removeTest(extentMap.get(testName));
//                                 testName = "Rerun - "+testName;
//                      }
		ExtentTest test = extent.createTest(testName);
		test.assignCategory(categories);
		testMap.put(Thread.currentThread().getId(), test);
		extentMap.put(testName, test);
	}

	public static void logScreenshot() throws IOException {
		// getCurrentTest().addScreenCaptureFromBase64String(ScreenshotUtil.takeScreenshot(DriverFactory.getInstance().getWebDriver()));
		Media mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(
				ScreenshotUtil.takeScreenshot(DriverFactory.getInstance().getWebDriver())).build();
		getCurrentTest().fail("", mediaModel);

	}

	public static void logPass(String message) {
		getCurrentTest().log(Status.PASS, message);

	}

	public static void logFail(String message) {
		getCurrentTest().log(Status.FAIL, message);
	}

	public static void logInfo(String message) {
		getCurrentTest().log(Status.INFO, message);

	}

	public static void endCurrentTest() {
		getCurrentTest().getExtent().flush();

		testMap.remove(Thread.currentThread().getId());
	}

	public static ExtentTest getCurrentTest() {
		return testMap.get(Thread.currentThread().getId());

	}

	public static void endReport() {

		extent.flush();
	}

//           public static ExtentHtmlReporter htmlReporterMobile;
	public static ExtentReports extentMobile;
	public static Map<Long, ExtentTest> testMapMobile = new HashMap<>();

}
