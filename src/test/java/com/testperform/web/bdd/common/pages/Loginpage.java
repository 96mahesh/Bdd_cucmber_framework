package com.testperform.web.bdd.common.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {

	WebDriver driver;
	public Loginpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='email']") WebElement email;
	@FindBy(xpath="//input[@name='pass']") WebElement pass;
	@FindBy(xpath="//button[text()='Log in']") WebElement login;
	
	public void userEmail(String username) {
		email.sendKeys(username);
	}
	
	public void userPass(String Password) {
		pass.sendKeys(Password);
	}
	
	public void clickOnLogin() {
		login.click();
	}
	

}
