package com.Insane.Util;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import com.Insane.base.TestBase;

public class CommonFunctions {

	public static String getScreenShot(String filename) {

		TakesScreenshot t = (TakesScreenshot) TestBase.driver;
		File fin = t.getScreenshotAs(OutputType.FILE);
		File fout = new File(".\\Screenshots\\" + filename + ".png");
		try {
			FileHandler.copy(fin, fout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fout.getAbsolutePath();
	}

	public static String getDateTime() {
		Date date = new Date();

		long time = date.getTime();

		Timestamp ts = new Timestamp(time);
		return ts.toString();
	}
}
