package edu.asu.classsearch.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ClassSearchDriver {
	private final static Map<String, WebDriver> DRIVER_MAP = new HashMap<>();

	private ClassSearchDriver() {

	}

	public static WebDriver getDriver(String url) {
		WebDriver driver = DRIVER_MAP.get(url);
		if (driver == null) {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(url);
			DRIVER_MAP.put(url, driver);


/*			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1366x768");
			driver = new ChromeDriver(options);
			driver.get(url);
			DRIVER_MAP.put(url, driver);*/
		}

		return driver;
	}

	public static void quitDriver(String url) {
		WebDriver driver = DRIVER_MAP.get(url);
		if(driver != null) {
			driver.quit();
			DRIVER_MAP.remove(url);
		}
	}
	
	public static void quitAllDrivers() {
		DRIVER_MAP.forEach((url, driver) -> {
			driver.quit();
		});
		DRIVER_MAP.clear();
	}
	
	public static List<byte[]> getAllScreenShots() {
		List<byte[]> screenshots = new ArrayList<>();
		DRIVER_MAP.forEach((url, driver) -> {
			screenshots.add(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
		});
		
		return screenshots;
	}

}
