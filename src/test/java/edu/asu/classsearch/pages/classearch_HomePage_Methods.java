package edu.asu.classsearch.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import edu.asu.classsearch.utils.CommonUtils;

public class classearch_HomePage_Methods {
	private WebDriver driver;
	private CommonUtils cu;

	public classearch_HomePage_Methods(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		cu = new CommonUtils();
	}

	// Property for handling search button
	private WebElement property_classsearch() {
		WebElement elem = driver.findElement(By.xpath("//a[@class='changeType' and  @rel='S']"));
		return elem;
	}

	private WebElement property_classcatalog() {
		WebElement elem = driver.findElement(By.xpath("//a[@class='changeType' and  @rel='C']"));
		return elem;
	}

	private WebElement property_search_button() {
		WebElement elem = driver.findElement(By.xpath("//button[@id='submitForm' or @id='go_and_search_courses']"));
		return elem;

	}

	// Property for getting classes with no open seats i.e. red crosses
	private List<WebElement> property_redx_search_results() {
		List<WebElement> elem = driver.findElements(By.xpath("//img[contains(@src,'red')]"));
		return elem;
	}

	// Property for getting classes with open seats i.e. Green crosses
	private List<WebElement> property_greencircle_search_results() {
		List<WebElement> elem = driver.findElements(By.xpath("//img[contains(@src,'circle')]"));
		return elem;
	}

	// Property for accessing Subject field
	private WebElement property_subject() {
		WebElement elem = driver.findElement(By.id("subjectEntry"));
		elem.clear();
		return elem;
	}

	// Property for accessing Number field
	private WebElement property_Number() {
		WebElement elem = driver.findElement(By.xpath("//*[@id='catalogNbr' or @id='catNbr']"));
		elem.clear();
		return elem;
	}

	// Property for accessing keyword field
	private WebElement property_keyword() {
		WebElement elem = driver.findElement(By.xpath("//*[@id='keyword' or @id='keywords']"));
		elem.clear();
		return elem;
	}

	private List<WebElement> list_location() {
		List<WebElement> elem = driver.findElements(By.xpath("//table[@id='campusFilter']//tr//label"));
		return elem;
	}

	// Property for accessing open radio button
	private WebElement property_radio_open() {
		WebElement elem = driver.findElement(By.id("searchTypeOpen"));
		return elem;
	}

	// Property for accessing all radio button
	private WebElement property_radio_All() {
		WebElement elem = driver.findElement(By.id("searchTypeAllClass"));
		return elem;
	}

	//// Property for accessing in person radio button
	private WebElement property_radio_inperson() {
		WebElement elem = driver.findElement(By.xpath("//*[@id='typeSelectionCampus' or @id='radio-campus']"));
		return elem;
	}

	// Property for accessing online radio button
	private WebElement property_radio_online() {
		WebElement elem = driver.findElement(By.xpath("//*[@id='typeSelectionOnline' or @id='radio-online']"));
		return elem;
	}

	// Property for accessing Term DropDowm list
	private Select property_Semester_list() {
		Select select = new Select(driver.findElement(By.xpath("//select[@id='term']")));
		return select;

	}

	// Property for accessing all the sessions in the page
	private List<WebElement> property_Filter_By_session() {
		List<WebElement> elem = driver.findElements(By.xpath("//table[@id='sessionFilter']//input"));
		return elem;

	}

	// Property for accessing the dates of list of classes
	private List<WebElement> property_Get_Session_name_list() {
		List<WebElement> session = driver.findElements(By.xpath("//table[@id='CatalogList']/tbody/tr[1]/td[11]//a"));
		return session;
	}

	// property select a class or select a first one among multiple ones
	private WebElement property_getclass() {
		WebElement elem = driver.findElement(By.id("informal"));
		return elem;

	}

	// property for adding a class
	private WebElement property_addclass(WebElement elem) {
		WebElement addelem = elem.findElement(By.id("Any_6"));
		return addelem;
	}

	private WebElement property_Registration() {
		WebElement elem = driver.findElement(By.xpath("//a[contains(., 'Registration')]"));
		return elem;
	}

	private WebElement property_signIn() {
		WebElement elem = driver.findElement(By.xpath("//a[contains(.,'Sign In')]"));
		return elem;
	}

	// table[@id="sessionFilter"]//input
	// -------------------------------------------------------------------------------------------
	public WebElement Pregistrationaction(String action) {
		if (action.equals("swap")) {
			WebElement elem = property_Registration();
			elem.click();
			WebElement elem1 = elem.findElement(By.xpath("//a[@id='Any_2']"));
			return elem1;
		} else if (action.equals("remove")) {
			WebElement elem = property_Registration();
			elem.click();
			WebElement elem1 = elem.findElement(By.xpath("//a[@id='Any_0_0']"));
			return elem1;
		} else
			return null;

	}

	public List<WebElement> location() {
		return list_location();
	}

	public void signIn() {
		WebElement elem = property_signIn();
		elem.click();
	}

	public void subject(String Searchterm) {
		WebElement elem = property_subject();
		elem.sendKeys(Searchterm);
	}

	public void subjectandnumber(String Searchterm, String number) {
		WebElement elem = property_subject();
		WebElement elemnum = property_Number();
		elem.clear();
		elemnum.clear();
		elem.sendKeys(Searchterm);
		elemnum.sendKeys(number);
	}

	public void Number(String number) {
		WebElement elem = property_Number();
		elem.sendKeys(number);
	}

	public void keyword(String keyword) {
		WebElement elem = property_keyword();
		elem.sendKeys(keyword);
	}

	public void keywordanddsubject(String Searchterm, String keyword) {
		WebElement elem = property_keyword();
		WebElement elem_subject = property_subject();
		elem_subject.sendKeys(Searchterm);
		elem.sendKeys(keyword);

	}

	public void selectaterm(String term) {
		Select select = property_Semester_list();
		select.selectByVisibleText(term);
	}

	public void check_ifopenorall(String radio) {
		if (radio.equals("open")) {
			WebElement elem = property_radio_open();
			elem.click();
		}
		if (radio.equals("all")) {
			WebElement elem = property_radio_All();
			elem.click();
		}
	}

	public void check_campusoronline(String radio) {
		if (radio.equals("campus")) {
			WebElement elem = property_radio_inperson();
			elem.click();
		}
		if (radio.equals("online")) {
			WebElement elem = property_radio_online();
			elem.click();
		}
	}

	public void select_session(String session) {
		List<WebElement> elem = property_Filter_By_session();

		if (session.equals("A")) {
			elem.get(0).click();
		} else if (session.equals("B")) {
			elem.get(1).click();
		} else if (session.equals("C")) {
			elem.get(2).click();
		} else {
			elem.get(3).click();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public String verify_results(String verfiy) {
		if (verfiy.equals("checkopenorall")) {
			List<WebElement> open = property_greencircle_search_results();
			List<WebElement> closed = property_redx_search_results();
			if (closed.isEmpty() && !open.isEmpty())
				return "true";
			else
				return "false";
		} else if (verfiy.equals("location")) {
			List<WebElement> location = driver.findElements(
					By.xpath("//td[contains(@class, 'locationBuildingColumnValue') and not(contains(. ,'Online'))]"));
			if (!location.isEmpty())
				return "true";
			else
				return "false";
		} else
			return "none";
	}

	public boolean verify_session(String sessionvalue) {

		List<WebElement> session = property_Get_Session_name_list();
		for (WebElement elem : session) {
			String value = elem.getAttribute("title");
			if (!value.contains("Session" + " " + sessionvalue))
				return false;
		}
		return true;
	}

	// ---------------------------------------------------------------------------------
	public void performsearch() {
		WebElement elem = property_search_button();
		WebDriverWait wait = new WebDriverWait(driver, 30); // this is explicit
															// wait
		wait.until(ExpectedConditions.elementToBeClickable(elem));
		Long start = System.nanoTime();
		elem.click();
		Long mid = System.nanoTime();

		String url = driver.getCurrentUrl();
		Long elapsedTime = TimeUnit.NANOSECONDS.toMillis(mid - start);
		updateSearchTime(elapsedTime, url);
		System.out.println("SEARCH TIME:" + url + " : " + elapsedTime + "ms");
	}
	
	public void updateSearchTime(Long elapsedTime, String url) {
		
		if (url.contains("elastic-catalog")) {
			cu.addElasticSearchTime(elapsedTime);
		} else {
			cu.addProdSearchTime(elapsedTime);
		}
	}

	public void addcourse() {
		WebElement elem = property_getclass();
		property_addclass(elem).click();
	}

	public String assertresultsOLD() {
		String Text = "";
		try {
			WebElement elem = driver.findElement(By.id("CatalogList"));
			// WebElement elem1=driver.findElement(By.id("CatalogList_info"));
			// Text=elem1.getText();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			WebElement elem = driver.findElement(By.className("error_msg"));
			Text = elem.getText();
		}
		return Text;
	}

	public WebElement classearch() {
		return property_classsearch();

	}

	public WebElement classcatalog() {
		return property_classcatalog();
	}

}
