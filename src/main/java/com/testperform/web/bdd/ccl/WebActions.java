package com.testperform.web.bdd.ccl;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.testperform.web.bdd.Integrations.Common_Utils.DriverFactory;
import com.testperform.web.bdd.Integrations.Report_Utils.ReportManager;

import org.openqa.selenium.Keys;

public class WebActions {

	JavascriptExecutor scrollBarPresent = (JavascriptExecutor) DriverFactory.getInstance().getWebDriver();
	Actions action = new Actions(DriverFactory.getInstance().getWebDriver());

	// @SuppressWarnings("deprecation")
	public WebElement waitForVisible(By locator) {

		WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getWebDriver(), Duration.ofSeconds(30));

		// WebDriverWait wait = new
		// WebDriverWait(DriverFactory.getInstance().getWebDriver(),Duration.ofSeconds(10));
		wait.pollingEvery(Duration.ofSeconds(1));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// @SuppressWarnings("deprecation")
	public WebElement waitForVisible_MilliSeconds(By locator) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getWebDriver(), Duration.ofSeconds(10));
		wait.pollingEvery(Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void sendKeysOTP(By locator) {
		WebElement elm = waitForVisible(locator);
		Scanner scan = new Scanner(System.in);
		String otp = scan.nextLine();
		elm.sendKeys(otp);
	}

	public void alerthandling() {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getWebDriver(), Duration.ofSeconds(10));
		if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
			DriverFactory.getInstance().getWebDriver().switchTo().alert().accept();
			ReportManager.logInfo("Successfully handled the alert!");
			System.out.println("Successfully handled the alert!");
		}
	}

	public void waitforbuttonandclick(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		if (elm.isEnabled()) {
			elm.click();
			ReportManager.logInfo("Successfully clicked on - " + info);
			System.out.println("Successfully clicked on - " + info);
		}
	}

	public void scrollDownUsingJS() {
		scrollBarPresent.executeScript(
				"window.scrollBy(0,document.body.scrollHeight || document.documentElement.scrollHeight)", "");
	}

	public void openURL(String URL) {
		DriverFactory.getInstance().getWebDriver().get(URL);
		String strActualText = DriverFactory.getInstance().getWebDriver().getCurrentUrl();
		verifyText(strActualText, URL);
		ReportManager.logInfo("Successfully Entered the URL - " + URL);
		System.out.println("Successfully Entered the URL - " + URL);
	}

	public void Click(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		elm.click();
		ReportManager.logInfo("Successfully clicked on - " + info);
		System.out.println("Successfully clicked on - " + info);
	}

	public void selectByVisibleText(By locator, String value) {
		WebElement elm = waitForVisible(locator);
		Select sel = new Select(DriverFactory.getInstance().getWebDriver().findElement(locator));
		sel.selectByVisibleText(value);
		ReportManager
				.logInfo("Successfully selected dropdown value - " + "<b style=\"color:green;\">" + value + "</b>");

	}

	public void sendKeys(By locator, String text) {
		WebElement elm = waitForVisible(locator);
		elm.sendKeys(text);
		ReportManager.logInfo("Successfully Entered text - " + text);
		System.out.println("Successfully Entered text - " + text);
	}

	public void sendKeys_ENTER(By locator, String text) {
		WebElement elm = waitForVisible(locator);
		elm.sendKeys(text, Keys.ENTER);
		ReportManager.logInfo("Successfully Entered text - " + text);
		System.out.println("Successfully Entered text - " + text);
	}

	public String getElmText(By locator) {
		WebElement elm = waitForVisible(locator);
		String elamText = elm.getText();
		ReportManager.logInfo("Successfully get element text - " + elamText);
		System.out.println("Successfully get element text - " + elamText);
		return elamText;
	}

	public boolean isDisplayed(By locator, String info) {
		// WebElement elm = waitForVisible(locator);
		boolean isPresent = DriverFactory.getInstance().getWebDriver().findElement(locator).isDisplayed();
		if (isPresent == true) {
			ReportManager.logInfo("Successfully element displayed: " + info);
			System.out.println("Successfully element displayed: " + info);
		} else {
			ReportManager.logInfo("element not displayed: " + info);
			System.out.println("element not displayed: " + info);

		}
		return isPresent;
	}

	public boolean isEnabled(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		boolean isEnable = elm.isEnabled();
		if (isEnable == true) {
			ReportManager.logInfo("Element Enabled: " + info);
			System.out.println("Element Enabled: " + info);
		} else {
			ReportManager.logInfo("Element Disabled: " + info);
			System.out.println("Element Disabled: " + info);
		}
		return isEnable;
	}

	public void clearAndSendKeys(By locator, String text) {
		WebElement elm = waitForVisible(locator);
		elm.clear();
		elm.sendKeys(text);
		ReportManager.logInfo("Successfully Entered text - " + text);
		System.out.println("Successfully Entered text - " + text);
	}

	public void clear(By locator) {
		WebElement elm = waitForVisible(locator);
		elm.clear();
		ReportManager.logInfo("Successfully Cleared text - ");
		System.out.println("Successfully Cleared text ");
	}

	public void verifyText(String actualText, String expectedText) {
		ReportManager.logInfo("Actual Text - " + actualText);
		ReportManager.logInfo("Expected Text - " + expectedText);
		System.out.println("Actual Text - " + actualText);
		System.out.println("Expected Text - " + expectedText);
		assertEquals(actualText, expectedText);
	}

	public void verifyIntValues(int actualValue, int expectedValue) {
		ReportManager.logInfo("Actual Value - " + actualValue);
		ReportManager.logInfo("Expected Value - " + expectedValue);
		System.out.println("Actual Value - " + actualValue);
		System.out.println("Expected Value - " + expectedValue);
		assertEquals(actualValue, expectedValue);
	}

	public String getAttributeValue(By locator, String name) {
		WebElement elm = waitForVisible(locator);
		String attributeText = elm.getAttribute(name);
		ReportManager.logInfo("Successfully get attribute text - " + attributeText);
		System.out.println("Successfully get attribute text - " + attributeText);
		return attributeText;
	}

	public boolean isScrollPresent() throws Exception {
		String execScript = "return document.documentElement.scrollHeight>document.documentElement.clientHeight;";
		Boolean isScroll_Present = (Boolean) (scrollBarPresent.executeScript(execScript));
		return isScroll_Present;

	}

	public void mouseHover(By locator) throws Exception {
		WebElement elm = waitForVisible_MilliSeconds(locator);
		action.moveToElement(elm).build().perform();
		String elmText = elm.getText();
		ReportManager.logInfo("Successfully mouse hover and get text - " + elmText);
		System.out.println("Successfully mouse hover and get text - " + elmText);
	}

	public void switchToFrame(By locator) throws Exception {
		WebElement elm = waitForVisible(locator);
		DriverFactory.getInstance().getWebDriver().switchTo().frame(elm);
		ReportManager.logInfo("Successfully switched to frame ");
		System.out.println("Successfully switched to frame ");
	}

	public List<WebElement> getListofElements(By locator, String name) {
		waitForVisible(locator);
		List<WebElement> lst_Elements = DriverFactory.getInstance().getWebDriver().findElements(locator);
		ReportManager.logInfo("Successfully get element size - " + lst_Elements.size());
		System.out.println("Successfully get element size - " + lst_Elements.size());
		return lst_Elements;
	}

//	public void verifyPaymentStatusOnUI(String Status) {
//
//		DriverFactory.getInstance().getWebDriver()
//				.findElement(By.xpath("//div[@class = 'content-container']//h5[contains(., '" + Status + "')]"))
//				.click();
//		boolean isStatus = DriverFactory.getInstance().getWebDriver()
//				.findElement(By.xpath("//div[@class = 'content-container']//h5[contains(., '" + Status + "')]"))
//				.isEnabled();
//		if (isStatus) {
//			ReportManager.logInfo("<b style=\"color:green;\"> Element is Enabled : " + Status + "</b>");
//			System.out.println("Enabled");
//		} else {
//			ReportManager.logInfo("<b style=\"color:red;\"> Element is Disabled : " + Status + "</b>");
//			System.out.println("Disabled");
//			Assert.assertEquals(true, isStatus);
//		}
//	}

	public void ClickJSE(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		scrollBarPresent.executeScript("arguments[0].click();", elm);
		ReportManager.logInfo("Successfully clicked on - " + info);
		System.out.println("Successfully clicked on - " + info);
	}

	public void staticWait() throws Throwable {
		Thread.sleep(15000);
		ReportManager.logInfo("Successfully pause the script for 15 secs");
		System.out.println("Successfully pause the script for 15 secs");
	}

	public void shortStaticWait() throws Throwable {
		Thread.sleep(5000);
		ReportManager.logInfo("Successfully pause the script for 5 secs");
		System.out.println("Successfully pause the script for 5 secs");
	}

	public void refreshPage() {
		DriverFactory.getInstance().getWebDriver().navigate().refresh();
		ReportManager.logInfo("Successfully pause the script for 5 secs");
		System.out.println("Successfully pause the script for 5 secs");
	}

	public void clearTextWithKeyboardKeys(By locator) {
		WebElement elm = waitForVisible(locator);
		elm.sendKeys(Keys.CONTROL + "a");
		elm.sendKeys(Keys.BACK_SPACE);
		ReportManager.logInfo("Successfully cleared the text using keyboard keys");
		System.out.println("Successfully cleared the text using keyboard keys");

	}

}
