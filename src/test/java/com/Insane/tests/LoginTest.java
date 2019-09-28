package com.Insane.tests;

import java.io.IOException;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.Insane.base.TestBase;
import com.Insane.pages.LoginPage;

public class LoginTest extends TestBase {

	public LoginTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeSuite
	public void openBrowser() throws IOException {
		TestBase.initialization();
	}

	@Test(description = "Verify correct title is displayed.", priority = 1)
	public void verifyTitle() throws IOException, InterruptedException {
		// assertTrue(driver.getTitle().contains("TEGRIS Fire"), "Url is correct", "Url
		// does not containt desired word");

	}

	@Test(description = "Verify login button is disabled.", priority = 2)
	public void verifyLoginButtonIsDisabled() throws IOException, InterruptedException {
		LoginPage loginPage = new LoginPage();
		loginPage.verifyButtonDisabled();
	}

	@Test(description = "Verify Reset button is present", priority = 3)
	public void verifyResetButtonPresent() throws IOException {
		LoginPage loginPage = new LoginPage();
		loginPage.verifyResetButtonPresence();
	}

	// @Test(description = "Verify user logs in", priority = 4)
	public void verifyUserLogin() throws IOException {
		LoginPage loginPage = new LoginPage();
		loginPage.verifyUserLogin();
	}

}
