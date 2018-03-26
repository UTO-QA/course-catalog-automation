package edu.asu.classsearch.driver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    private WebDriver driver;

    public WebDriver getDriver(String url, String type) {

        switch (type.toUpperCase()) {
            case "CHROME":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                options.addArguments("window-size=1366x768");
                driver = new ChromeDriver(options);
                break;
            default:
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get(url);
                break;
        }
        return driver;
    }

    public void quit() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    public byte[] getScreenShots() {
        byte[] screenshot = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }

}
