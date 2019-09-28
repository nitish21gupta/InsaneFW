package com.Insane.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Insane.base.TestBase;

public class LoginPage extends TestBase {

	public LoginPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@formcontrolname='username']")
	WebElement userName;

	@FindBy(xpath = "//input[@formcontrolname='password']")
	WebElement userPswd;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginButton;

	@FindBy(xpath = "//button[@type='submit'][@disabled]")
	WebElement disabledLoginButton;

	@FindBy(xpath = "//*[@class='app-button']")
	WebElement resetButton;

	@FindBy(xpath = "//*[@class='user-name']")
	WebElement welcomeUser;

	@FindBy(xpath = "//div[@class='logo']")
	WebElement loginLogo;

	public void verifyUserLogin() {
		userName.sendKeys("needtochange@gmail.com");
		userPswd.sendKeys("removethispassword");
		loginButton.click();
		waitForElement(welcomeUser);
		// TestBase.assertTrue(welcomeUser.isDisplayed(), "Login is successful.",
		// "Invalid login");
	}

	public void verifyResetButtonPresence() {
		// TestBase.assertTrue(resetButton.isDisplayed(), "Reset Button is present.",
		// "Reset button not present");
	}

	public void verifyButtonDisabled() {
		// TestBase.assertTrue(disabledLoginButton.isDisplayed() == false, "Button is
		// disabled", "Button is not disabled");
	}

	public void loginLogoPresent() {
		// TestBase.assertTrue(loginLogo.isDisplayed(), "Logo is present.", "Logo is
		// missing.");
	}
}
