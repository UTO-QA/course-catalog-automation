package edu.asu.classsearch.testcases.def;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.asu.classsearch.driver.ClassSearchDriver;
import edu.asu.classsearch.input.ClassSearchInputs;
import edu.asu.classsearch.pages.ClassSearchResultsValidator;
import edu.asu.classsearch.pages.ClassSearch_Filters;
import edu.asu.classsearch.pages.classearch_HomePage_Methods;
import edu.asu.classsearch.pages.classearch_commons;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ClassSearchHomepageAutomation {

    private classearch_HomePage_Methods classSearchHomePageMethods;
    private WebDriver driver;
    private String result = "";
    private String result2 = "";

    // New Class to Validate Search Results
    private ClassSearchResultsValidator val;
    private String validateString = "";
    private String testCase = "";

    private ClassSearch_Filters filters;

    @Before
    public void before(Scenario scenario) {
        String scenarioName = scenario.getName();
        this.testCase = scenarioName.substring(0, scenarioName.indexOf(" "));
    }

    // creates a connection
    @Given("^The user is on Class Search page$")
    public void getconnection() {
        String url1 = ClassSearchInputs.inputload("URL_1");
        this.driver = classearch_commons.getconn(url1);
        this.classSearchHomePageMethods = new classearch_HomePage_Methods(driver);
        this.val = new ClassSearchResultsValidator(driver);
        this.filters = new ClassSearch_Filters(this.driver);
    }

    // delete a connection
    public void closeconnection() {
        classearch_commons.closeconn();
    }

    // TEST1: CHECK if course accepts Positive Subject
    @When("^User enters Correct Subject$")
    public void Postive1_subject() {
        String[] values = ClassSearchInputs.inputload("TC_1").split(",");
        String searchterm = values[0];
        validateString = searchterm;
        classSearchHomePageMethods.subject(searchterm);
        classSearchHomePageMethods.performsearch();
    }

    @Then("^The Results with correct subject info should be Displayed$")
    public void test_Positive1_subject() {
        val.validateSubjectNumber(validateString);
        closeconnection();
    }

    // TEST2: CHECK if course rejects Negative Subject
    @When("^User enters Incorrect Subject$")
    public void incorrect_subject1() {
        String[] values = ClassSearchInputs.inputload("TC_2").split(",");
        String searchterm = values[0];
        classSearchHomePageMethods.subject(searchterm);
        classSearchHomePageMethods.performsearch();
    }

    @Then("^A Warning should be Displayed for incorrect subject$")
    public void test_subject_incorrect() {
        val.validateSearchError("Please update your search criteria and try again.");
        closeconnection();
    }

    // TEST3: CHECK if course accepts Positive Subject and Number
    @When("^User enters correct Subject and Number$")
    public void subject_number() {
        String[] values = ClassSearchInputs.inputload(testCase).split(",");
        String searchterm = values[0];
        String number = values[1];
        validateString = searchterm + " " + number;
        classSearchHomePageMethods.subjectandnumber(searchterm, number);
        classSearchHomePageMethods.performsearch();
    }

    @Then("^The Results should be Displayed for Correct Subject and Number scenario$")
    public void test_subject_number() {
        val.validateSubjectNumber(validateString);
        closeconnection();
    }

    // TEST4: CHECK if course rejects Incorrect Subject and Incorrect Number
    @When("^User enters incorrect Subject and incorrect Number$")
    public void subject1_number() {
        String[] values = ClassSearchInputs.inputload("TC_4").split(",");
        String searchterm = values[0];
        String number = values[1];
        classSearchHomePageMethods.subjectandnumber(searchterm, number);
        classSearchHomePageMethods.performsearch();

    }

    @Then("^A Warning should be Displayed for incorrect subject and number scenario$")
    public void test_subject1_number() {
        val.validateSearchError("Please update your search criteria and try again.");
        closeconnection();
    }

    // TEST5: CHECK if course rejects Negative Subject and Number
    @When("^User enters swapped values for Subject and Number$")
    public void subject_2_number() {
        String[] values = ClassSearchInputs.inputload("TC_5").split(",");
        String searchterm = values[0];
        String number = values[1];
        classSearchHomePageMethods.subjectandnumber(searchterm, number);
        classSearchHomePageMethods.performsearch();

    }

    @Then("^A Warning should be Displayed for swapped scenario$")
    public void test_subject_2_number() {
        val.validateSearchTermError("Please provide numbers only");
        closeconnection();
    }

    // TEST 6:CHECK if search by only Number works
    @When("^User enters correct Number$")
    public void test_number() {
        String[] values = ClassSearchInputs.inputload("TC_6").split(",");
        String number = values[0];

        classSearchHomePageMethods.Number(number);
        classSearchHomePageMethods.performsearch();
    }

    @Then("^A Warning should be Displayed for correct Number Scenario$")
    public void validation_number() {
        val.validateSearchTermError("Please narrow your search by entering a subject, general studies or college.");
        closeconnection();
    }

    // TEST 7: CHECK if search by incorrect number displays a warning message
    @When("^User enters incorrect Number$")
    public void check_incorrect_number() {
        String[] values = ClassSearchInputs.inputload("TC_7").split(",");
        String number = values[0];
        classSearchHomePageMethods.Number(number);
        classSearchHomePageMethods.performsearch();
    }

    @Then("^A Warning should be Displayed for incorrect number scenario$")
    public void validation_incorrect_number() {
        val.validateSearchTermError("Please narrow your search by entering a subject, general studies or college.");
        closeconnection();
    }

    // TEST 8: Verify that search with only keyword produces results
    @When("^User performs a search using only the correct keyword$")
    public void keyword() {
        String[] values = ClassSearchInputs.inputload("TC_8").split(",");
        String keyword = values[0];
        this.result = keyword;
        classSearchHomePageMethods.keyword(keyword);
        classSearchHomePageMethods.performsearch();

    }

    @Then("^The search results page is displayed for correct keyword scenario$")
    public void test_keyword() {
        val.validateSubjectNumber(this.result);
        closeconnection();
    }

    // TEST 9: Verify that search with only incorrect keyword produces proper
    // warning message
    @When("^User performs a search using only the incorrect keyword or keyword with less than 3 letters$")
    public void incorrect_keyword() {
        String[] values = ClassSearchInputs.inputload("TC_9").split(",");
        String keyword = values[0];
        String keyword_lim = values[1];
        this.result2 = keyword_lim;
        classSearchHomePageMethods.keyword(keyword);
        classSearchHomePageMethods.performsearch();
    }

    @Then("^An Appropriate warning message is displayed for incorrect keyword$")
    public void test_incorrect_keyword() {

        val.validateSearchError("Please update your search criteria and try again.");

        classSearchHomePageMethods.keyword(result2);
        classSearchHomePageMethods.performsearch();
        val.validateSearchTermError("Keyword must be longer");

        closeconnection();
    }

    // TEST 10: Verify that search with Correct Subject and keyword produces
    // Search results
    @When("^User performs a search using the correct keyword and correct Subject$")
    public void correct_keyword_Subject() {
        String[] values = ClassSearchInputs.inputload("TC_10").split(",");
        String Subject = values[0];
        String keyword = values[1];
        validateString = Subject;
        classSearchHomePageMethods.keywordanddsubject(Subject, keyword);
        classSearchHomePageMethods.performsearch();

    }

    @Then("^The Search Results page is Displayed for correct keyword and subject scenario$")
    public void test_correct_keyword_Subject() {
        val.validateSubjectNumber(validateString);
        closeconnection();
    }

    // TEST 11: Verify that search with Correct Subject and keyword produces
    // Search results
    @When("^User performs a search using the incorrect keyword or Subject$")
    public void incorrect_keyword_Subject() {
        String[] values = ClassSearchInputs.inputload("TC_11").split(",");
        String Subject = values[0];
        String keyword = values[1];
        classSearchHomePageMethods.keywordanddsubject(Subject, keyword);
        classSearchHomePageMethods.performsearch();

    }

    @Then("^The Warning is Displayed for incorrect keyword or subject scenario$")
    public void test_incorrect_keyword_Subject() {
        val.validateSearchError("Please update your search criteria and try again.");
        closeconnection();

    }

    // TEST 12: Verify that toggle the radio button changes the results
    @When("^User selects for open classes and performs a search$")
    public void toggleradio_open() {
        String[] values = ClassSearchInputs.inputload("TC_12").split(",");
        String term = values[0];
        String subject = values[1];
        String open = values[2];
        classSearchHomePageMethods.selectaterm(term);
        classSearchHomePageMethods.check_ifopenorall(open);
        classSearchHomePageMethods.subject(subject);
        classSearchHomePageMethods.performsearch();

    }

    @Then("^The Results should only contain open values$")
    public void verifytoggleradio_open() {
        val.validateSeatStatus("open");
        closeconnection();
    }

    // TEST 13: Verify that toggle the radio button changes the results
    @When("^User selects for all classes and performs a search$")
    public void toggleradio_all() {
        String[] values = ClassSearchInputs.inputload("TC_13").split(",");
        String term = values[0];
        String subject = values[1];
        String all = values[2];
        classSearchHomePageMethods.check_ifopenorall(all);
        classSearchHomePageMethods.selectaterm(term);
        classSearchHomePageMethods.subject(subject);
        classSearchHomePageMethods.performsearch();

    }

    @Then("^The Results should contain open and closed values$")
    public void verifytoggleradio_all() {
        val.validateSeatStatus("all");
        closeconnection();
    }

    // TEST 14: Verify that the results displayed contain only campus classes when
    // in-Person is selected
    @When("^User selects for in-person and performs a search$")
    public void toggleradio_campus() {
        String[] values = ClassSearchInputs.inputload("TC_14").split(",");
        String campus = values[0];
        String sem = values[1];
        String subject = values[2];
        classSearchHomePageMethods.check_campusoronline(campus);
        classSearchHomePageMethods.selectaterm(sem);
        classSearchHomePageMethods.subject(subject);
        classSearchHomePageMethods.performsearch();

    }

    @Then("^The Results should contain only On-Campus Classes$")
    public void verifytoggleradio_campus() {
        String value = classSearchHomePageMethods.verify_results("location");
        Assert.assertEquals("true", value);
        closeconnection();
    }

    // TEST 15: Verify that the results displayed contain only ASUONLINE classes
    // when online is selected
    @When("^User selects for online and performs a search$")
    public void toggleradio_online() {
        String[] values = ClassSearchInputs.inputload("TC_15").split(",");
        String campus = values[0];
        String sem = values[1];
        String subject = values[2];
        classSearchHomePageMethods.check_campusoronline(campus);
        classSearchHomePageMethods.selectaterm(sem);
        classSearchHomePageMethods.subject(subject);
        classSearchHomePageMethods.performsearch();

    }

    @Then("^The Results should contain only online Classes$")
    public void verifytoggleradio_online() {
        String value = classSearchHomePageMethods.verify_results("location");
        Assert.assertEquals("false", value);
        closeconnection();
    }

    // TEST 16:
    @When("^User selects Session A and performs a search$")
    public void Session_A() throws Exception {
        String[] values = ClassSearchInputs.inputload("TC_16").split(",");
        String campus = values[0];
        String sem = values[1];
        String subject = values[2];
        String session = values[3];
        String openorall = values[4];
        classSearchHomePageMethods.check_campusoronline(campus);
        classSearchHomePageMethods.selectaterm(sem);
        classSearchHomePageMethods.subject(subject);
        classSearchHomePageMethods.check_ifopenorall(openorall);
        classSearchHomePageMethods.performsearch();
        this.filters.filterBySession(new String[]{session});
        this.result = session;
    }

    @Then("^The Results should contain only Session A classes$")
    public void verifySession_A() {
        boolean session_verify = classSearchHomePageMethods.verify_session(result);
        Assert.assertEquals(session_verify, true);
        closeconnection();
    }

    @When("^User selects Session B and performs a search$")
    public void Session_B() throws Exception {
        String[] values = ClassSearchInputs.inputload("TC_16").split(",");
        String campus = values[0];
        String sem = values[1];
        String subject = values[2];
        String session = values[3];
        String openorall = values[4];
        classSearchHomePageMethods.check_campusoronline(campus);
        classSearchHomePageMethods.selectaterm(sem);
        classSearchHomePageMethods.subject(subject);
        classSearchHomePageMethods.check_ifopenorall(openorall);
        classSearchHomePageMethods.performsearch();
        this.filters.filterBySession(new String[]{session});
        this.result = session;

    }

    @Then("^The Results should contain only Session B classes$")
    public void verifySession_B() {
        boolean session_verify = classSearchHomePageMethods.verify_session(result);
        Assert.assertEquals(session_verify, true);
        closeconnection();
    }

    @When("^User selects Session C and performs a search$")
    public void Session_C() throws Exception {
        String[] values = ClassSearchInputs.inputload("TC_16").split(",");
        String campus = values[0];
        String sem = values[1];
        String subject = values[2];
        String session = values[3];
        String openorall = values[4];
        classSearchHomePageMethods.check_campusoronline(campus);
        classSearchHomePageMethods.selectaterm(sem);
        classSearchHomePageMethods.subject(subject);
        classSearchHomePageMethods.check_ifopenorall(openorall);
        classSearchHomePageMethods.performsearch();
        this.filters.filterBySession(new String[]{session});
        this.result = session;

    }

    @Then("^The Results should contain only Session C classes$")
    public void verifySession_C() {
        boolean session_verify = classSearchHomePageMethods.verify_session(result);
        Assert.assertEquals(session_verify, true);
        closeconnection();
    }

    @Then("^User is able to search using a wildcard in number field$")
    public void validate_wildcard_number_search() {
        //Make sure the "Please provide numbers only" validation does not occur
        String[] values = ClassSearchInputs.inputload(testCase).split(",");
        for (int i = 0; i < values.length - 1; i += 2) {
            classSearchHomePageMethods.subjectandnumber(values[i], values[i + 1]);
            classSearchHomePageMethods.performsearch();
            List<WebElement> numberValidations = driver.findElements(By.xpath("//*[@id=\"catNbr\"]/following-sibling::*[1]"));
            MatcherAssert.assertThat("Number Validation message is present", numberValidations.isEmpty());
        }
    }

    //*[@id="course-details"]
    @Then("^Clicking on Class record must return the drawer or details page experiences$")
    public void validate_class_record_drawer() {
        //Click on course details
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(By.xpath("//*[@id='Any_13']"))).build().perform();

        List<WebElement> courseDetails = driver.findElements(By.xpath("//*[@id='course-details']"));
        MatcherAssert.assertThat("Course details not found for " + driver.getCurrentUrl(), !courseDetails.isEmpty());
    }

}
