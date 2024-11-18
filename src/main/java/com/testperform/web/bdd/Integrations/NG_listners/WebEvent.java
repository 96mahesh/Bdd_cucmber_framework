package com.testperform.web.bdd.Integrations.NG_listners;

import static com.testperform.web.bdd.constants.FrameworkConstants.ICON_BUG;
import static com.testperform.web.bdd.constants.FrameworkConstants.ICON_SMILEY_FAIL;

import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.testperform.web.bdd.Integrations.Common_Utils.ConfigReader;
import com.testperform.web.bdd.Integrations.Common_Utils.DriverFactory;
import com.testperform.web.bdd.Integrations.Common_Utils.VideoReord;
import com.testperform.web.bdd.Integrations.Report_Utils.ReportManager;

public class WebEvent implements ITestListener {
	// private String str_BrowserType = System.getProperty("Browser");
	// private String str_BrowserType = ConfigReader.getValue("Browser");
	DriverFactory driverFactory = DriverFactory.getInstance();
	public static String strBrowser;

	// RetryFailedTests retry = new RetryFailedTests();
	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("+++++++++++++++++++++onTestStart++++++++++++++++++++");
		// ReportManager.startTest(arg0.getMethod().getMethodName(),"WEB");
		System.out.println("Execution started @ " + strBrowser + " browser & for type : Web UI");
		try {

			// initDriver.startWebDriver();
			VideoReord.startRecord(arg0.getMethod().getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		// driverFactory.getWebDriver().quit();
		driverFactory.getWebDriver().close();
		try {
			VideoReord.stopRecord();
			// initDriver.tearDownWebDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		try {
////			 Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
////			Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
////			 Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
////			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {

		// retry.retry(iTestResult);
		System.out.println("+++++++++++++++++++++onTestFailure++++++++++++++++++++");
		ReportManager.logFail(iTestResult.getThrowable().toString());

		try {
			ReportManager.logScreenshot();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReportManager.endCurrentTest();
		// driverFactory.getWebDriver().close();

		try {
			VideoReord.stopRecord();
			// initDriver.tearDownWebDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * try { Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
		 * Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
		 * Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe"); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

		// ExtentLogger.skip("<b><i>" + result.getThrowable().toString() + "</i></b>");
		String logText = "<b>" + arg0.getMethod().getMethodName() + " is skipped.</b>" + "  " + ICON_SMILEY_FAIL;
		Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);

	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext arg0) {

		// strBrowser = arg0.getCurrentXmlTest().getParameter("browser");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}
