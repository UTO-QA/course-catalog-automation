package edu.asu.classsearch.testcases.def;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.asu.classsearch.input.ClassSearchInputs;
import edu.asu.classsearch.pages.*;

public class classearch_crosspage_functionalities {
	private Loginpage page;
	private classearch_page1 peoplesoft_1;
	private classearch_page2 peoplesoft_2;
	private classearch_finalpage peoplesoft_3;
	private classearch_HomePage_Methods cl;
	private WebDriver driver;

	@Given("^User is on ClassSearch Homepage$")
	public void accesspage() {
		driver = classearch_commons.getconn();
	}
	// TC_19 Verify that the results displayed contain only Session C classes
	// when session C is selected

	@When("^A user adds a class for testing (.*) from class catalog$")
	public void addcourse(String value) {
		if (value.equals("addition"))
			add("TC_19");
		else if (value.equals("drop"))
			add("TC_21");
		else
			return;
	}

	public void add(String Testcase) {
		String[] values = ClassSearchInputs.inputload(Testcase).split(",");
		String openorall = values[0];
		String course = values[1];
		String number = values[2];

		add(openorall, course, number);

	}

	public void add(String openorall, String course, String number) {
		cl = new classearch_HomePage_Methods(driver);
		cl.check_ifopenorall(openorall);
		cl.subjectandnumber(course, number);
		cl.performsearch();
		cl.addcourse();
		page = new Loginpage(driver);
		page.get_login();
		peoplesoft_1 = new classearch_page1(driver);
		peoplesoft_1.select_frame();
		// peoplesoft_1.select_an_instructor();
		peoplesoft_1.navigate_next();
		peoplesoft_2 = new classearch_page2(driver);
		peoplesoft_2.select_frame();
		peoplesoft_2.navigate_proceed_step_2of3();
		peoplesoft_3 = new classearch_finalpage(driver);
		peoplesoft_3.select_frame();
		peoplesoft_3.navigate_finishenrolling();
		System.out.println("Reached before");
	}

	@Then("^The class should be added succesfully to their schedule$")
	public void verify_addedclass() {
		String actual = peoplesoft_3.verify_added();
		Assert.assertEquals("Success: This class has been added to your schedule.".trim(), actual.trim());
		System.out.println("Reached End");
		classearch_commons.closeconn();
	}

	// TC_20
	@When("^A user swaps a class from class catalog$")
	public void swap() {
		String[] values = ClassSearchInputs.inputload("TC_20").split(",");
		String action = values[0];
		String coursenumber = values[1];
		cl = new classearch_HomePage_Methods(driver);
		cl.signIn();
		page = new Loginpage(driver);
		page.get_login();
		cl.Pregistrationaction(action).click();
		peoplesoft_1 = new classearch_page1(driver);
		peoplesoft_1.select_frame();
		peoplesoft_1.select_a_course();
		peoplesoft_1.class_number(coursenumber);
		peoplesoft_1.action_enter();
		peoplesoft_1.navigate_next();
		peoplesoft_2 = new classearch_page2(driver);
		peoplesoft_2.select_frame();
		peoplesoft_2.action_finishstep();
	}

	@Then("^The class should be swapped succesfully to their schedule$")
	public void verify_swapclass() {
		peoplesoft_3 = new classearch_finalpage(driver);
		peoplesoft_3.select_frame();
		String actual = peoplesoft_3.verify_added();
		Assert.assertEquals("Success: This class has been replaced.".trim(), actual.trim());
		classearch_commons.closeconn();
	}

	// TC_21
	@When("^A user drops a class from class catalog$")
	public void drop() {
		addcourse("drop");
		// cl=new classearch_HomePage_Methods(driver);
		// cl.signIn();
		// page=new Loginpage(driver);
		// page.get_login();
		driver.get(classearch_commons.getUrl());
		cl.Pregistrationaction("remove").click();
		peoplesoft_1 = new classearch_page1(driver);
		peoplesoft_1.select_frame();
		peoplesoft_1.selct_course_to_drop(1);
		peoplesoft_1.drop_a_course();
		peoplesoft_2 = new classearch_page2(driver);
		peoplesoft_2.action_finishstep();
	}

	@Then("^The class drop should be succesful$")
	public void verify_drop() {
		peoplesoft_3 = new classearch_finalpage(driver);
		Assert.assertNotEquals("", peoplesoft_3.verify_added().trim());
		classearch_commons.closeconn();
	}

}
