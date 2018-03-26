package edu.asu.classsearch.testcases.def;

import java.util.List;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.When;
import edu.asu.classsearch.driver.ClassSearchDriver;
import edu.asu.classsearch.utils.CommonUtils;

public class ClassSearchCommon {
	@After
	public void screenshotAndkillBrowser(Scenario scenario) {
		List<byte[]> screenshots = ClassSearchDriver.getAllScreenShots();
		screenshots.forEach((screenshot) -> {
			scenario.embed(screenshot, "image/png");
		});

		ClassSearchDriver.quitAllDrivers();

	}

	@When("^Compare and Print search times$")
	public void compareAndPrintTimes() {
		CommonUtils commonUtils = new CommonUtils();
		System.out.println(commonUtils.getSearchStatistics());
	}
}
