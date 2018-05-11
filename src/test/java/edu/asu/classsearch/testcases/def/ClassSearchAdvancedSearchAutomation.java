package edu.asu.classsearch.testcases.def;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.asu.classsearch.pages .ClassSearch_AdvancedSearch;
import edu.asu.classsearch.pages.classearch_HomePage_Methods;
import edu.asu.classsearch.driver.ClassSearchDriver;
import edu.asu.classsearch.input.ClassSearchInputs;
import edu.asu.classsearch.pages.ClassSearchResultsValidator;;

public class ClassSearchAdvancedSearchAutomation {

	private ClassSearchResultsValidator val;
	private WebDriver driver;
	private ClassSearch_AdvancedSearch adv;
	private classearch_HomePage_Methods home;
	private String validateString[];

	private WebDriver prodDriver;
	private ClassSearch_AdvancedSearch prodAdv;
	private classearch_HomePage_Methods prodHome;

	// Creates a connection
	@Given("^The user is on Class Search page Advanced Search$")
	public void getconnection() {
		String url1 = ClassSearchInputs.inputload("URL_1");
		String url2 = ClassSearchInputs.inputload("URL_2");

		driver = ClassSearchDriver.getDriver(url1);

		adv = new ClassSearch_AdvancedSearch(driver);
		val = new ClassSearchResultsValidator(driver);
		home = new classearch_HomePage_Methods(driver);

		prodDriver = ClassSearchDriver.getDriver(url2);
		prodAdv = new ClassSearch_AdvancedSearch(prodDriver);
		prodHome = new classearch_HomePage_Methods(prodDriver);
	}

	// 22: User Enters correct subject and instructor
	@When("^User performs a search using the correct Subject and Instructor$")
	public void positive_Subject_Instructor() {

		String[] values = ClassSearchInputs.inputload("TC_22").split(",");
		String subject = values[0];
		String instructor = values[1];
		validateString = new String[2];
		validateString[0] = subject;
		validateString[1] = instructor;

		home.subject(subject);
		adv.clickAdvancedSearch();
		adv.enterInstructorName(instructor);
		home.performsearch();

		prodHome.subject(subject);
		prodAdv.clickAdvancedSearch();
		prodAdv.enterInstructorName(instructor);
		prodHome.performsearch();

	}

	@Then("^The Results with correct subject info and Instructor should be Displayed$")
	public void validate_Subject_Instructor() {
		// Validate Subject Details and Instructor Details
		val.validateSubjectNumber(validateString[0]);
		// val.validateInstructorName(validateString[1]);
		val.verifyResultWithProd(prodDriver);

	}

	@When("^User performs a search using a college/school$")
	public void positive_CollegeSchool() {
		String values[] = ClassSearchInputs.inputload("TC_23").split(",");
		String college = values[0];
		validateString = new String[1];
		validateString[0] = college;

		adv.clickAdvancedSearch();
		adv.selectCollegeSchool(college);
		home.performsearch();

		// Verification URL
		prodAdv.clickAdvancedSearch();
		prodAdv.selectCollegeSchool(college);
		prodHome.performsearch();

	}

	@Then("^The results should contain records with the selected college/school$")
	public void validate_CollegeSchool() {

		val.verifyResultWithProd(prodDriver);

	}

	@When("^User selects # of Units and subject and performs search$")
	public void positive_NumofUnits() {
		String values[] = ClassSearchInputs.inputload("TC_24").split(",");
		String subject = values[0];
		String numUnits = values[1];
		validateString = new String[2];
		validateString[0] = subject;
		validateString[1] = numUnits;

		home.subject(subject);
		adv.clickAdvancedSearch();
		adv.selectNumberofUnits(numUnits);
		home.performsearch();

		prodHome.subject(subject);
		prodAdv.clickAdvancedSearch();
		prodAdv.selectNumberofUnits(numUnits);
		prodHome.performsearch();

	}

	@Then("^The results should contain only classes with the given number of units$")
	public void validate_NumofUnits() {
		// val.validateSubjectName(validateString[0]);
		// val.validateNumberOfUnits(validateString[1]);
		// MatcherAssert.assertThat(results,CoreMatchers.containsString("Showing"));

		val.verifyResultWithProd(prodDriver);

	}

	@When("^User selects Class # and subject and performs search$")
	public void positive_ClassNumber() {
		String values[] = ClassSearchInputs.inputload("TC_25").split(",");
		String subject = values[0];
		String classNum = values[1];
		validateString = new String[2];
		validateString[0] = subject;
		validateString[1] = classNum;

		home.subject(subject);
		adv.clickAdvancedSearch();
		adv.enterClassNumber(classNum);
		home.performsearch();

		prodHome.subject(subject);
		prodAdv.clickAdvancedSearch();
		prodAdv.enterClassNumber(classNum);
		prodHome.performsearch();

	}

	@Then("^The results should contain only classes with that class number$")
	public void validate_ClassNumber() {
		val.validateSubjectNumber(validateString[0]);
		val.validateClassNumber(validateString[1]);
		val.verifyResultWithProd(prodDriver);

	}

	@When("^User enters Start Date and subject and performs search$")
	public void positive_StartDate() {
		String values[] = ClassSearchInputs.inputload("TC_26").split(",");
		String subject = values[0];
		String startDate = values[1];
		validateString = new String[2];
		validateString[0] = subject;
		validateString[1] = startDate;

		home.subject(subject);
		adv.clickAdvancedSearch();
		adv.enterStartDate(startDate);
		home.performsearch();

		prodHome.subject(subject);
		prodAdv.clickAdvancedSearch();
		prodAdv.enterStartDate(startDate);
		prodHome.performsearch();

	}

	@Then("^The results should contain only classes only classes starting on or after the date should return$")
	public void validate_StartDate() {

		val.validateSubjectNumber(validateString[0]);
		val.validateStartDate(validateString[1]);
		val.verifyResultWithProd(prodDriver);

	}

	// 27: User Enters correct subject and End Date
	@When("^User performs a search using the correct Subject and End Date$")
	public void Postive_EndDate() {
		String[] values = ClassSearchInputs.inputload("TC_27").split(",");
		String subject = values[0];
		String endDate = values[1];
		validateString = new String[2];
		validateString[0] = subject;
		validateString[1] = endDate;

		home.subject(subject);
		adv.clickAdvancedSearch();
		adv.enterEndDate(endDate);
		home.performsearch();

		prodHome.subject(subject);
		prodAdv.clickAdvancedSearch();
		prodAdv.enterEndDate(endDate);
		prodHome.performsearch();

	}

	@Then("^The Results must display only classes ending on or after the date$")
	public void validate_EndDate() {
		// Validate Subject Details and Instructor Details
		val.validateSubjectNumber(validateString[0]);
		val.validateEndDate(validateString[1]);
		val.verifyResultWithProd(prodDriver);

	}

	// 28: User Enters correct subject and checks Honors
	@When("^User performs a search using the correct Subject and checks Honors check box$")
	public void positive_Honors() throws InterruptedException {
		String[] values = ClassSearchInputs.inputload("TC_28").split(",");
		String subject = values[0];
		validateString = new String[1];
		validateString[0] = subject;
		home.subject(subject);
		adv.clickAdvancedSearch();
		Thread.sleep(5000);
		adv.clickHonors();
		adv.selectHonors();
		home.performsearch();
		//prodHome.subject(subject);
		//prodAdv.clickAdvancedSearch();
		//prodAdv.clickHonors();
		//prodHome.performsearch();
	}

	@Then("^The Results must display only honors classes or classes that offer honors enrichment contracts$")
	public void validate_Honors() {
		// Validate Subject Details and Instructor Details
		val.validateSubjectNumber(validateString[0]);
		val.validateHonorsTitle();

		val.verifyResultWithProd(prodDriver);

	}

	// 29: User Enters correct subject and checks Promod
	@When("^User performs a search using the correct Subject and checks Project-based check box$")
	public void positive_Promod() {
		String[] values = ClassSearchInputs.inputload("TC_29").split(",");
		String subject = values[0];
		validateString = new String[1];
		validateString[0] = subject;

		home.subject(subject);
		adv.clickAdvancedSearch();
		adv.clickPromod();
		home.performsearch();

		prodHome.subject(subject);
		prodAdv.clickAdvancedSearch();
		prodAdv.clickPromod();
		prodHome.performsearch();

	}

	@Then("^The Results must display only Promod block classes or individual classes designated as project based with a special note 0018$")
	public void validate_promod() {
		// Validate Subject Details and Instructor Details
		val.validateSubjectNumber(validateString[0]);
		val.validatePromod();

		val.verifyResultWithProd(prodDriver);

	}

	// 30: User Searches using General studies
	@When("^User performs a search using General Studies option$")
	public void positive_GeneralStudies() throws InterruptedException {

		String[] values = ClassSearchInputs.inputload("TC_30").split("&");
		String gs = values[0];
		validateString = values;
		adv.clickAdvancedSearch();
		Thread.sleep(5000);
		adv.selectGeneralStudies(gs);
		// If GS option 1 is provided
		if (values.length > 1) {
			validateString[1] = values[1];
			adv.selectGeneralStudiesOption1(values[1]);
			// If GS option 1 is provided
			if (values.length > 2) {
				validateString[2] = values[2];
				adv.selectGeneralStudiesOption2(values[2]);
			}
		}

		home.performsearch();

		prodAdv.clickAdvancedSearch();
		prodAdv.selectGeneralStudies(gs);
		// If GS option 1 is provided
		if (values.length > 1) {
			validateString[1] = values[1];
			prodAdv.selectGeneralStudiesOption1(values[1]);
			// If GS option 1 is provided
			if (values.length > 2) {
				validateString[2] = values[2];
				prodAdv.selectGeneralStudiesOption2(values[2]);
			}
		}

		prodHome.performsearch();

	}

	@Then("^The Results must display only classes with the chosen GS category$")
	public void validate_GeneralStudies() {
		// Validate Subject Details and Instructor Details
		val.validateGeneralStudies(validateString);

		val.verifyResultWithProd(prodDriver);

	}

	// 31: User Enters correct subject and level
	@When("^User performs a search using Subject and Level$")
	public void positive_Level() {
		String[] values = ClassSearchInputs.inputload("TC_31").split(",");
		String subject = values[0];
		String level = values[1];
		validateString = new String[2];
		validateString[0] = subject;
		validateString[1] = level;

		home.subject(subject);
		adv.clickAdvancedSearch();
		adv.selectLevel(level);
		home.performsearch();

		prodHome.subject(subject);
		prodAdv.clickAdvancedSearch();
		prodAdv.selectLevel(level);
		prodHome.performsearch();

	}

	@Then("^The Results must display only classes in the level or division chosen$")
	public void validate_Level() {
		// Validate Subject Details and Instructor Details
		val.validateSubjectNumber(validateString[0]);
		// TODO: Validate Level

		val.verifyResultWithProd(prodDriver);

	}

}
