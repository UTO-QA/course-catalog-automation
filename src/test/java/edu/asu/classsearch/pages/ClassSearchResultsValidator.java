package edu.asu.classsearch.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.junit.Assert;

public class ClassSearchResultsValidator {
	private WebDriver driver;

	public ClassSearchResultsValidator(WebDriver driver) {
		this.driver = driver;
	}

	// Validates the subject name and Number(Optional) in the search results.
	public void validateSubjectNumber(String subjectNumber) {
		try {
			List<WebElement> subjectList = driver.findElements(By.className("subjectNumberColumnValue"));
			for (WebElement w : subjectList) {
				MatcherAssert.assertThat(w.getText(), CoreMatchers.containsString(subjectNumber));
				// System.out.println(w.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Validated Against " + subjectNumber);
	}

	public void validateInstructorName(String instructor) {
		try {
			List<WebElement> instructorList = driver.findElements(By.className("instructorListColumnValue"));
			for (WebElement w : instructorList) {
				MatcherAssert.assertThat(instructor, CoreMatchers.containsString(w.getText()));
				System.out.println("Validated Against " + w.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateClassNumber(String classNumber) {
		try {
			List<WebElement> classNumberList = driver.findElements(By.className("classNbrColumnValue"));
			for (WebElement w : classNumberList) {
				MatcherAssert.assertThat(w.getText(), CoreMatchers.containsString(classNumber));
				System.out.println("Validated Against " + w.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void validateStartDate(String startDate) {
		try {

			String startDateSplit[] = startDate.split("/");
			Integer month = Integer.parseInt(startDateSplit[0]);
			Integer day = Integer.parseInt(startDateSplit[1]);
			List<WebElement> classNumberList = driver.findElements(By.className("startDateColumnValue"));
			for (WebElement w : classNumberList) {

				String columnValue = w.getText();
				String listStartDate[] = columnValue.split("-")[0].trim().split("/");

				// Convert list date to month and day
				int listMonth = Integer.parseInt(listStartDate[0]);
				int listDay = Integer.parseInt(listStartDate[1]);
				MatcherAssert.assertThat(startDate + " must fall on or after " + columnValue,
						listMonth > month || (month == listMonth && listDay >= day));
				System.out.println("Validated Against " + w.getText());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void validateNumberOfUnits(String units) {
		try {

			// Case when units does not contain or more

			if (units.contains("or more")) {
				// Get the number displayed
				units = units.substring(0, 1);
			}

			Integer unitsinInt = Integer.parseInt(units);

			List<WebElement> hoursList = driver.findElements(By.className("hoursColumnValue"));
			for (WebElement w : hoursList) {
				String columnValue = w.getText();
				if (columnValue.contains("-")) {
					String unitRange[] = columnValue.split("-");
					MatcherAssert.assertThat(columnValue + " Range must contain the selected units: " + units,
							(unitsinInt >= Integer.parseInt(unitRange[0]))
									&& unitsinInt <= Integer.parseInt(unitRange[1]));
				} else {
					MatcherAssert.assertThat(w.getText(), CoreMatchers.containsString(units));
				}

				System.out.println("Validated Against " + w.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Goes Into the validator class
	public void validateEndDate(String endDate) {
		try {
			String endDateSplit[] = endDate.split("/");
			int month = Integer.parseInt(endDateSplit[0]);
			int day = Integer.parseInt(endDateSplit[1]);

			List<WebElement> classNumberList = driver.findElements(By.className("startDateColumnValue"));
			for (WebElement w : classNumberList) {
				String columnValue = w.getText();
				String columnEndDate[] = columnValue.split("-")[1].trim().split("/");
				int listMonth = Integer.parseInt(columnEndDate[0]);
				int listDay = Integer.parseInt(columnEndDate[1].substring(0, 2));
				MatcherAssert.assertThat(endDate + " must fall in the date range " + columnValue,
						listMonth < month || (listMonth == month && listDay < day));
				System.out.println("Validated Against " + w.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void validateHonorsTitle() {
		try {
			List<WebElement> honorsList = driver.findElements(By.className("honorshead"));
			List<String> honorTextList = new ArrayList<String>();
			for (WebElement w : honorsList) {
				honorTextList.add(w.getText());
			}

			MatcherAssert.assertThat(honorTextList, contains("Honors-only Class Sections"));
			// "The classes listed below offer Honors Enrichment Contracts; to
			// inquire about contracts for classes not identified below, please
			// contact the instructor."));
			System.out.println("Validated against Honors");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validatePromod() {
		try {
			List<WebElement> promodImgList = driver.findElements(By.className("classNbrColumnValue"));
			for (WebElement w : promodImgList) {
				// Check if the promod icon is displayed
				MatcherAssert
						.assertThat(w.findElement(By.xpath("//span[@class='icontip']/img[@alt='Project-based course']"))
								.getAttribute("src"), CoreMatchers.containsString("icon_pb.png"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateGeneralStudies(String[] values) {
		List<WebElement> gsColumnList = driver.findElements(By.className("tooltipRqDesDescrColumnValue"));

		// Set value[0];
		values[0] = values[0].split(":")[0];
		for (WebElement w : gsColumnList) {
			String[] gsColumnSplit = w.getText().split("&");

			// Trim the values to remove the trailing space
			for (int i = 0; i < gsColumnSplit.length; i++) {
				gsColumnSplit[i] = gsColumnSplit[i].trim();
			}

			if (gsColumnSplit[0].contains(values[0]))
				gsColumnSplit[0] = values[0];
			MatcherAssert.assertThat(Arrays.asList(gsColumnSplit), hasItems(values));

			System.out.println("Validated against " + w.getText());
		}

	}

	public void verifyWithProd(WebDriver prodDriver) {
		// Verify the result count
		String countInProd = prodDriver.findElement(By.xpath("//*[@id='CatalogList_info']")).getText();
		String count = driver.findElement(By.xpath("//*[@id='CatalogList_info']")).getText();
		MatcherAssert.assertThat(count, CoreMatchers.equalTo(countInProd));
	}

	public String resultMessage(WebDriver driver) {
		String Text;
		try {
			WebElement elem1 = driver.findElement(By.id("CatalogList_info"));
			Text = elem1.getText();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			WebElement elem = driver.findElement(By.xpath("//*[@id='classResults']/div[2]/div"));
			Text = elem.getText();
		}
		return Text;
	}

	public void verifyResultWithProd(WebDriver prodDriver) {

/*	TODO: Till Temp Dev messages disappear.	
		// Verify the result count
		String countInProd = resultMessage(prodDriver);
		String count = resultMessage(driver);
		MatcherAssert.assertThat(count, CoreMatchers.equalTo(countInProd));

*/		List<String> tableColumnIDList = new ArrayList<String>();

		// Complete Table Verification.
		/*
		 * tableColumnIDList.addAll(Arrays.asList("subjectNumberColumnValue",
		 * "titleColumnValue","classNbrColumnValue",
		 * "instructorListColumnValue","dayListColumnValue",
		 * "startTimeDateColumnValue",
		 * "endTimeDateColumnValue","locationBuildingColumnValue",
		 * "startDateColumnValue","hoursColumnValue",
		 * "availableSeatsColumnValue","tooltipRqDesDescrColumnValue"));
		 */

		// Verify just the subject column
		tableColumnIDList.addAll(Arrays.asList("subjectNumberColumnValue"));
		System.out.println("Starting to compare the results");
		for (String td : tableColumnIDList) {
			
			List<WebElement> wList1 = this.driver.findElements(By.className(td));
			List<WebElement> wList2 = prodDriver.findElements(By.className(td));

			MatcherAssert.assertThat(wList1, hasSize(wList2.size()));

			for (int i = 0; i < wList1.size(); i++) {
				// System.out.println(wList1.get(i).getText()+"-"+wList2.get(i).getText());
				MatcherAssert.assertThat(wList2.get(i).getText().trim(),
						CoreMatchers.containsString((wList1.get(i).getText()).trim()));
			}

		}

		System.out.println("Compared both the result tables");

	}

	public void verifyResultFromLocations(WebDriver prodDriver) {

	List<String> tableColumnIDList = new ArrayList<String>();

		// Verify just the subject column
		tableColumnIDList.addAll(Arrays.asList("subjectNumberColumnValue"));
		System.out.println("Starting to check the result size");
		for (String td : tableColumnIDList) {

			List<WebElement> wList2 = prodDriver.findElements(By.className(td));
			MatcherAssert.assertThat(wList2, hasSize(0));

		}

		System.out.println("College present");

	}
	public void validateClassTitleAndIns(String title) {
		List<WebElement> classTitleList = this.driver.findElements(By.className("titleColumnValue"));
		List<WebElement> instructorList = this.driver.findElements(By.className("instructorListColumnValue"));

		for (int i = 0; i < classTitleList.size(); i++) {

			String classTitle = classTitleList.get(i).getText().toLowerCase();
			String instructor = instructorList.get(i).getText().toLowerCase();
			title = title.toLowerCase();
			MatcherAssert.assertThat(title + " must match " + classTitle + " or " + instructor,
					classTitle.contains((title)) || instructor.contains(title));
			System.out.println("Validated using " + title);
		}

	}

	public void validateSearchTermError(String expectedErrorMessage) {
		try {
			validateSearchError("Please update your search criteria and try again.");
		} catch(NoSuchElementException e) {
			System.out.println("Search Error not present...Verifying elastic errors");
			WebElement errorElement = this.driver.findElement(By.xpath("//*[@class='validation-error']"));
			String errorString = errorElement.getText();
			errorString = ("" + errorString).trim();
			Assert.assertEquals(expectedErrorMessage, errorString);
		}

	}
	
	public void validateSearchError(String expectedErrorMessage) {
		WebElement errorElement = this.driver.findElement(By.xpath("//*[@class='error_msg']"));
		String errorString = errorElement.getText();
		Assert.assertEquals(expectedErrorMessage, errorString);
	}

	private boolean fetchOpenSeatStatus() {
		List<WebElement> seatsOpenList = this.driver.findElements(By.className("availableSeatsColumnValue"));
		Pattern openPattern = Pattern.compile("^0[\\n]of[\\n][0-9]+");
		Matcher matcher;
		for (WebElement element : seatsOpenList) {
			String openSeats = element.getText();
			matcher = openPattern.matcher(openSeats);
			if(matcher.find()) {
				System.out.println(matcher.group(0));
				return false;
			}
		}
		
		return true;
	}
	
	public void validateSeatStatus(String status) {
		
		if("all".equalsIgnoreCase(status)) Assert.assertTrue(true);
		boolean openAssert = fetchOpenSeatStatus();
		if("open".equalsIgnoreCase(status)) {
			Assert.assertEquals("Expected all Open classes but found Closed classes", true, openAssert); 
			
		}
	}

}
