package com.Insane.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestBase {

	public static WebDriver driver;
	static Properties prop;
	public static ExtentReports extent;
	public static ExtentHtmlReporter reporter;
	static int PAGE_LOAD_TIMEOUT;

	public TestBase() throws IOException {
		reporter = new ExtentHtmlReporter(".\\Reports\\ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		prop = new Properties();
		try {
			FileInputStream objFile = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\Insane\\config\\config.properties");
			prop.load(objFile);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() throws IOException {
		// ---Browser level condition for Chrome, Firefox and IE---
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			Map<String, Object> prefs = new HashMap<String, Object>();
			// ---Handling of browser notification----
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\libs\\chromedriver.exe");
			driver = new ChromeDriver(options);
		}

		else if (browserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir") + "\\libs\\geckodriver.exe");
			FirefoxOptions option = new FirefoxOptions();
			option.addPreference("dom.webnotifications.enabled", false);
			option.addPreference("app.update.enabled", false);
			option.addPreference("geo.enabled", false);
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\libs\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		else if (browserName.equalsIgnoreCase("headless")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\libs\\chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("window-size=1200x600");
			driver = new ChromeDriver(chromeOptions);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		PAGE_LOAD_TIMEOUT = Integer.valueOf(prop.getProperty("waitTime"));
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		// ---url is passed---
		driver.get(prop.getProperty("devUrl"));
		waitForPageToLoad();
	}

	public static boolean waitForPageToLoad() {
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
				.equalsIgnoreCase("complete");
	}

	public void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
