package com.Insane.listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.maven.surefire.shade.common.org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Insane.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

public class Listener extends TestBase implements ITestListener {
	String folderPath = "errorScreenshots\\";
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public Listener() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void onTestStart(ITestResult result) {
		/*
		 * try { TestBase.initialization(); System.out.println("I am in"); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	public void onTestSuccess(ITestResult result) {
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {

		// draw a border around the found element if (driver instanceof

		try {
			logger.addScreenCaptureFromPath(capture(driver));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("src/../ErrImages/" + System.currentTimeMillis() + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
	}

	public void cleanFolder() throws IOException {
		File currentFolder = new File(System.getProperty("user.dir") + "/CurrentTestResult/");
		File destination = new File(System.getProperty("user.dir") + "/PreviousTestResults/" + currentFolder.getName());
		File OutputFolder = new File("./PreviousTestResults");
		if (!OutputFolder.exists()) {
			if (OutputFolder.mkdir()) {
				Files.move(currentFolder, OutputFolder);
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create Directory");
			}
		} else {
			System.out.println("Diretory already exists");
			Files.move(currentFolder, OutputFolder);

		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		try {
			new TestBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * try { //cleanFolder(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/CurrentTestResult/" + timestamp() + "ExtentReport.html");// specify
		// location
		extent = new ExtentReports();

		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Automation Test Report");

		htmlReporter.config().setTheme(Theme.STANDARD);
		extent.attachReporter(htmlReporter); // of
		logger = extent.createTest(context.getName());
	}

	public static String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		driver.quit();
	}
}
