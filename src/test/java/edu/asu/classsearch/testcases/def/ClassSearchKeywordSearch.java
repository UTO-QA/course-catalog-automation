package edu.asu.classsearch.testcases.def;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.asu.classsearch.driver.ClassSearchDriver;
import edu.asu.classsearch.input.ClassSearchInputs;
import edu.asu.classsearch.pages.ClassSearchResultsValidator;
import edu.asu.classsearch.pages.classearch_HomePage_Methods;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ClassSearchKeywordSearch {

    WebDriver driver, prodDriver;
    classearch_HomePage_Methods home, prodHome;
    ClassSearchResultsValidator val;
    String validateString[];
    String results;
    String testCase;

    @Before
    public void before(Scenario scenario) {
        String scenarioName = scenario.getName();
        this.testCase = scenarioName.substring(0, scenarioName.indexOf(" "));
    }

    @Given("^The user is on Class Search page to search using Keyword$")
    public void getconnection() {
        String url1 = ClassSearchInputs.inputload("URL_1");
        String url2 = ClassSearchInputs.inputload("URL_2");
        driver = ClassSearchDriver.getDriver(url1);
        prodDriver = ClassSearchDriver.getDriver(url2);

        val = new ClassSearchResultsValidator(driver);
        home = new classearch_HomePage_Methods(driver);
        prodHome = new classearch_HomePage_Methods(prodDriver);
    }

    // Search By keyword testcases
    @When("^User performs a search using Search by Keyword$")
    public void positive_Keyword_3CharSearch_Subject() {

        String[] values = ClassSearchInputs.inputload(testCase).split(",");
        String subject = values[0];
        this.validateString = new String[1];
        this.validateString[0] = subject;

        home.keyword(subject);
        home.performsearch();

        prodHome.keyword(subject);
        prodHome.performsearch();

    }

    @Then("^The Results must contain records with matching subject$")
    public void validate_3CharSearch_Subject() {
        // Validate Subject Details and Instructor Details
        val.validateSubjectNumber(validateString[0]);
        val.verifyResultWithProd(prodDriver);
        // MatcherAssert.assertThat(results,CoreMatchers.containsString("Showing"));
        // TODO: how to check subject is correct.

    }

    @Then("^The Results must contain records with matching class title/topic title or instructor$")
    public void validate_3CharSearch_ClassTitle() {
        // Validate Subject Details and Instructor Details
        // val.validateClassTitleAndIns(validateString[0]);
        // MatcherAssert.assertThat(results,CoreMatchers.containsString("Showing"));
        val.verifyResultWithProd(prodDriver);

    }

    @Then("^The Results must contain records with correct subject and category number$")
    public void validate_SubjectNumber() {
        // Validate Subject Details and Instructor Details
        // val.validateSubjectName(validateString[0]);
        // MatcherAssert.assertThat(results,CoreMatchers.containsString("Showing"));
        val.verifyResultWithProd(prodDriver);
        // MatcherAssert.assertThat(results,CoreMatchers.containsString("Showing"));
        // TODO: how to check subject is correct.

    }

    @Then("^The Results must contain records that match the class number$")
    public void validate_ClassNumber() {
        // Validate Subject Details and Instructor Details
        // val.validateClassNumber(validateString[0]);
        // MatcherAssert.assertThat(results,CoreMatchers.containsString("Showing"));
        val.verifyResultWithProd(prodDriver);
        // MatcherAssert.assertThat(results,CoreMatchers.containsString("Showing"));
        // TODO: how to check subject is correct.

    }

    @Then("^The Results must contain records that match the class number and ignores all other keywords$")
    public void validate_ClassNumberIgnore() {
        // Validate Subject Details and Instructor Details
        // val.validateClassNumber(validateString[0].split(" ")[1]);
        // MatcherAssert.assertThat(results,CoreMatchers.containsString("Showing"));
        val.verifyResultWithProd(prodDriver);
        // MatcherAssert.assertThat(results,CoreMatchers.containsString("Showing"));
        // TODO: how to check subject is correct.

    }

    @Then("^The Results must contain subjects that match the formal description$")
    public void validate_SubjectDescription() {
        // Validate Subject Details and Instructor Details

        // Compare the results with a new search that matches the Subject formal
        // description
        WebDriver driver2 = ClassSearchDriver.getDriver("https://webapp4-qa.asu.edu/elastic-catalog");
        String[] values = ClassSearchInputs.inputload(testCase).split(",");
        String subject = values[1];
        home = new classearch_HomePage_Methods(driver2);

        home.subject(subject);
        home.performsearch();

        val.verifyResultWithProd(driver2);

        val.verifyResultWithProd(prodDriver);
        // MatcherAssert.assertThat(results,CoreMatchers.containsString("Showing"));
        // TODO: how to check subject is correct.

    }

    //TC_52
    @Then("^The search returns classes with subject name matching the keyword and searches class title/topic title/instructor name with \"like\" logic.$")
    public void classSearch_search_logic_increase() {

        //Inputs

        String[] values = ClassSearchInputs.inputload(testCase).split(",");
        String keyword = values[0];

        List<WebElement> catalogRows = driver.findElements(By.cssSelector("#CatalogList tr"));
        for (int i = 1; i < catalogRows.size(); i++) {
            List<WebElement> subjectTitleInstructor = catalogRows.get(i).findElements(By.cssSelector(".subjectNumberColumnValue,.titleColumnValue,.instructorListColumnValue"));
            String subjectTitle = subjectTitleInstructor.get(0).getText();
            String courseTitle = subjectTitleInstructor.get(1).getText();
            String instructorTitle = subjectTitleInstructor.get(2).getText();

            //Check for Exact course Match first
            boolean matchFlag = false;

            for (int j = 1; j < values.length; j++) {
                if (subjectTitle.contains(values[j])) {
                    matchFlag = true;
                    System.out.println("Subject title matched " + subjectTitle + " and " + values[j]);
                    break;
                }
            }
            //Exact Match so continue;
            if (matchFlag) {
                continue;
            }

            //Partial Matcher
            MatcherAssert.assertThat("Course Title/Instructor is " + courseTitle + "/" + instructorTitle + " and the keyword is " + keyword, StringUtils.containsIgnoreCase(courseTitle, keyword) || StringUtils.containsIgnoreCase(instructorTitle, keyword));
            System.out.println("Course Title/Instructor is " + courseTitle + "/" + instructorTitle + " and the keyword is " + keyword);

        }


    }

    @Then("^The keyword search results are case insensitive$")
    public void keyword_results_case_insensitive() {
        String[] values = ClassSearchInputs.inputload(testCase).split(",");
        String caseInsensitiveKeyWord = values[1];

        prodHome.keyword(caseInsensitiveKeyWord);
        prodHome.performsearch();

        val.verifyResultWithProd(prodDriver);

    }
}
