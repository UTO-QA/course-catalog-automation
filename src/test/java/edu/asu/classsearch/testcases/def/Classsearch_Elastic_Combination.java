package edu.asu.classsearch.testcases.def;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.asu.classsearch.input.ClassSearchInputs;
import edu.asu.classsearch.pages.ClassSearchResultsValidator;
import edu.asu.classsearch.pages.ClassSearch_AdvancedSearch;
import edu.asu.classsearch.pages.ClassSearch_Filters;
import edu.asu.classsearch.pages.classearch_HomePage_Methods;
import edu.asu.classsearch.driver.ClassSearchDriver;

public class Classsearch_Elastic_Combination {

	WebDriver driver;
	WebDriver prodDriver;

	ClassSearch_AdvancedSearch adv;
	ClassSearch_AdvancedSearch prodAdv;

	classearch_HomePage_Methods home;
	classearch_HomePage_Methods prodHome;

	ClassSearchResultsValidator val;

	ClassSearch_Filters filters;
	ClassSearch_Filters prodFilters;

	String testCase = "";

	enum Fields {
		SEM, SUB, NUM, KEY, UNT, CNUM, INS, SDT, EDT, CLG, LVL, GS, HON, PRO, FOPENC, FALLC, FSES, FLOC, FDAY, FTIME, SESS, LOC, DAY, TIME
	}

	@Before
	public void before(Scenario scenario) {
		String scenarioName = scenario.getName();
		this.testCase = scenarioName.substring(0, scenarioName.indexOf(" "));
	}

	@Given("The User is on Class Search Page in Combination Scenario")
	public void elastic_Combination() {
		driver = ClassSearchDriver.getDriver("https://webapp4-qa.asu.edu/elastic-catalog");
		prodDriver = ClassSearchDriver.getDriver("https://webapp4-qa.asu.edu/catalog");

		home = new classearch_HomePage_Methods(driver);
		prodHome = new classearch_HomePage_Methods(prodDriver);

		adv = new ClassSearch_AdvancedSearch(driver);
		prodAdv = new ClassSearch_AdvancedSearch(prodDriver);

		val = new ClassSearchResultsValidator(driver);

		filters = new ClassSearch_Filters(driver);
		prodFilters = new ClassSearch_Filters(prodDriver);
	}

	@When("^User performs a search using any combination of fields$")
	public void elastic_Combination_When() throws Exception {

		String[] inputs = ClassSearchInputs.inputload(testCase).split(",");
		adv.clickAdvancedSearch();
		prodAdv.clickAdvancedSearch();
		for (int i = 0; i < inputs.length; i++) {

			String values[] = inputs[i].split("_");

			switch (Fields.valueOf(values[0])) {
			case SUB:
				home.subject(values[1]);
				prodHome.subject(values[1]);
				break;

			case NUM:
				home.Number(values[1]);
				prodHome.Number(values[1]);
				break;

			case KEY:
				home.keyword(values[1]);
				prodHome.keyword(values[1]);
				break;

			case UNT:
				adv.selectNumberofUnits(values[1]);
				prodAdv.selectNumberofUnits(values[1]);
				break;

			case CNUM:
				adv.enterClassNumber(values[1]);
				prodAdv.enterClassNumber(values[1]);
				break;

			case INS:
				adv.enterInstructorName(values[1]);
				prodAdv.enterInstructorName(values[1]);
				break;

			case SDT:
				adv.enterStartDate(values[1]);
				prodAdv.enterStartDate(values[1]);
				break;

			case EDT:
				adv.enterEndDate(values[1]);
				prodAdv.enterEndDate(values[1]);
				break;

			case CLG:
				adv.selectCollegeSchool(values[1]);
				prodAdv.selectCollegeSchool(values[1]);
				break;

			case LVL:
				adv.selectLevel(values[1]);
				prodAdv.selectLevel(values[1]);
				break;

			case GS:
				String gs[] = values[1].split("&");
				adv.selectGeneralStudies(gs[0]);
				prodAdv.selectGeneralStudies(gs[0]);
				// If GS option 1 is provided
				if (values.length > 1) {
					adv.selectGeneralStudiesOption1(gs[1]);
					prodAdv.selectGeneralStudiesOption1(gs[1]);
					// If GS option 1 is provided
					if (values.length > 2) {
						adv.selectGeneralStudiesOption2(gs[2]);
						prodAdv.selectGeneralStudiesOption2(gs[2]);
					}
				}
				break;

			case SESS:
				String sessions[] = Arrays.copyOfRange(values, 1, values.length);
				filters.filterBySession(sessions);
				prodFilters.filterBySession(sessions);
				break;

			case LOC:
				String locations[] = Arrays.copyOfRange(values, 1, values.length);
				filters.filterByLocation(locations);
				prodFilters.filterByLocation(locations);
				break;

			case DAY:
				String days[] = Arrays.copyOfRange(values, 1, values.length);
				List<String> dayss = Arrays.asList(days);

				// To click the days button so that the days filter options becomes visible
				List<WebElement> elasticSessionWrapper = this.driver.findElements(By.xpath("//*[@id='days-button']"));
				if (!elasticSessionWrapper.isEmpty()) {
					elasticSessionWrapper.get(0).click();
				}
				// filters.expandDaysFilter();
				prodFilters.expandDaysFilter();

				filters.filterByDaysOfWeek(dayss);
				prodFilters.filterByDaysOfWeek(days);
				break;

			case TIME:
				String startTime = values[1];
				String endTime = "";
				if (values.length > 2)
					endTime = values[2];

				// filters.expandTimeFilter();
				prodFilters.expandTimeFilter();

				filters.enterStartTime(startTime);
				filters.enterEndTime(endTime);

				prodFilters.enterStartTime(startTime);
				prodFilters.enterEndTime(endTime);
				prodFilters.enterEndTime(endTime);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			case PRO:
				adv.clickPromod();
				prodAdv.clickPromod();
				break;

			case HON:
				adv.clickHonors();
				prodAdv.clickHonors();
				break;

			default:
				break;
			}

		}
		home.performsearch();
		prodHome.performsearch();
	}

	@Then("The results must be same across the environments")
	public void elastic_Validate_Combination() {

		val.verifyResultWithProd(prodDriver);
		driver.quit();
		prodDriver.quit();

	}

}
