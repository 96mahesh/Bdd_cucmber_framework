package com.testperform.web.bdd.common.stepdefinations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.testperform.web.bdd.common.pages.Loginpage;

import io.cucumber.java.en.*;

public class LoginSteps {

	WebDriver driver;
	Loginpage login;
	@Given("user need to on Tiwtter Login Page")
	public void user_need_to_on_tiwtter_login_page() {

		driver = new ChromeDriver();
		driver.get("httsp//www.facebook.com");
	}

	@When("user should able to enter username {string}")
	public void user_should_able_to_enter_username(String name) {
	    
		login = new Loginpage(driver);
		login.userEmail(name);
	}

	@When("user should able to enter password {string}")
	public void user_should_able_to_enter_password(String pass) {
	    login.userPass(pass);
	}

	@Then("user click on login button")
	public void user_click_on_login_button() {
	    login.clickOnLogin();
	}

	@Then("close browser")
	public void close_browser() {
	    driver.close();
	}

}
