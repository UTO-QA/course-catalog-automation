package edu.asu.classsearch.pages;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClassSearch_Filters {
	
	WebDriver driver;

	//Location and corresponding xPaths
	static Map<String,String> locationxPath=new HashMap<String,String>()
	{{
		put("Downtown", 		 "//*[@id='DTPHX']");
		put("Polytechnic",		 "//*[@id='POLY']");
		put("Tempe",			 "//*[@id='TEMPE']");
		put("Thunderbird",		 "//*[@id='TBIRD']");
		put("West",				 "//*[@id='WEST']");
		put("AZ Western College","//*[@id='AWC']");
		put("Eastern AZ College","//*[@id='EAC']");
		put("Tucson",			 "//*[@id='TUCSON']");
		put("iCourse (online)",  "//*[@id='ICOURSE']");
		put("Off-Campus",		 "//*[@id='OFFCAMP']");
		put("Cochise College",		 "//*[@id='COCHISE']");
		put("Central AZ College",		 "//*[@id='CAC']");
		put("Lake Havasu",		 "//*[@id='CALHC']");
//		put("All locations",	 "//*[@id='ALL']");
	}};
	
	//Location and corresponding xPaths
	//	String []days={"M","T","W","Th","F","S","Su"};
	static final Map<String,String> elasticDays=new HashMap<String,String>()
	{{
		put("M", 		 "MON");
		put("T",		 "TUES");
		put("W",			 "WED");
		put("Th",		 "THURS");
		put("F",				 "FRI");
		put("S","SAT");
		put("Su","SUN");
	}};
	
	
	public ClassSearch_Filters(WebDriver driver){
		this.driver=driver;
	}
	
	private WebElement inPerson(){
		WebElement elem=this.driver.findElement(By.xpath("//*[@id='typeSelectionCampus' or @id='radio-campus']"));
		return elem;
	}

	private WebElement online(){
		WebElement elem=this.driver.findElement(By.xpath("//*[@id='typeSelectionOnline' or @id='radio-online']"));
		return elem;
	}
	
	private WebElement openClasses(){
		WebElement elem=this.driver.findElement(By.xpath("//*[@id='searchTypeOpen']"));
		return elem;
	}
	
	private WebElement allClasses(){
		WebElement elem=this.driver.findElement(By.xpath("//*[@id='searchTypeAllClass']"));
		return elem;
	}
	
	//*[@id="sessionA"]	//*[@id="sessionB"]	//*[@id="sessionC"]	//*[@id="sessionDYN"]
	
	private WebElement sessionFilter(String session){
		WebElement elem=this.driver.findElement(By.xpath("//*[@id='session"+session+"']"));
		return elem;
	}
	
	private WebElement locationFilter(String location){
		WebElement elem=this.driver.findElement(By.xpath(location));
		return elem;
	}
	
	private WebElement dayOfWeek(String day,String elasticDay){
		WebElement elem=this.driver.findElement(By.xpath("//*[@id='day"+day+"' or @id='"+elasticDay+"']"));
		return elem;
	}
	
	private WebElement dayOfWeek(String day){
		WebElement elem=this.driver.findElement(By.xpath("//*[@id='day"+day+"']"));
		return elem;
	}
	
	private WebElement daysFilterPlus(){
		WebElement elem=this.driver.findElement(By.xpath("//*[@id='filter-by-day']/i"));
		return elem;
	}
	private WebElement timeFilterPlus(){
		WebElement elem=this.driver.findElement(By.xpath("//*[@id='filter-by-time']/i"));
		return elem;
	}
	
	private WebElement startTime(){
		WebElement elem=this.driver.findElement(By.xpath("//*[@id='startTime']"));
		return elem;
	}
	
	private WebElement endTime(){
		WebElement elem=this.driver.findElement(By.xpath("//*[@id='endTime']"));
		return elem;
	}
	
	public void toggleInPerson(){
		WebElement elem=inPerson();
		elem.click();
	}
	
	public void toggleASUOnline(){
		WebElement elem=online();
		elem.click();
	}
	
	public void toggleAllClasses(){
		WebElement elem=allClasses();
		elem.click();
	}
	
	public void toggleOpenClasses(){
		WebElement elem=openClasses();
		elem.click();
	}

	public void unCheckSessions(){
		//Uncheck Sessions
		String []sessions={"A","B","C","DYN"};
		
		for(String s:sessions){
			WebElement elem=sessionFilter(s);
			if(elem.isSelected()) elem.click();
		}
	}
	
	public void filterBySession(List<String> sessions) throws Exception{
		//Handle new Elastic Search changes.
		/*List<WebElement> elasticSessionWrapper = this.driver.findElements(By.xpath("//*[@id='session-button']"));
		if(!elasticSessionWrapper.isEmpty()) {
			elasticSessionWrapper.get(0).click();
		}*/
		unCheckSessions();
		for(String s:sessions){
			Thread.sleep(1000);
			WebElement elem=sessionFilter(s);
			elem.click();	
		}
	}
	
	public void filterBySession(String[] sessions) throws Exception{
		filterBySession(Arrays.asList(sessions));
	}
	
	/*
	 * 	put("Downtown", 		 "//*[@id='DTPHX']");
		put("Polytechnic",		 "//*[@id='POLY']");
		put("Tempe",			 "//*[@id='TEMPE']");
		put("Thunderbird",		 "//*[@id='TBIRD']");
		put("West",				 "//*[@id='WEST']");
		put("AZ Western College","//*[@id='AWC']");
		put("Eastern AZ College","//*[@id='EAC']");
		put("Tucson",			 "//*[@id='TUCSON']");
		put("iCourse (online)",  "//*[@id='ICOURSE']");
		put("Off-campus",		 "//*[@id='OFFCAMP']");
		put("All locations",	 "//*[@id='ALL']");
	 * 
	 * */
	
	private void unCheckLocations(){
		for(Map.Entry<String, String> entry:locationxPath.entrySet()){
			WebElement elem=locationFilter(entry.getValue());
			if(elem.isSelected()){
				elem.click();
			}
		}
	}
	public void filterByLocation(List<String> locations){
		
		unCheckLocations();
		for(String loc:locations){
			WebElement elem=locationFilter(locationxPath.get(loc));
			elem.click();
			
		}
	}
	
	public void filterByLocation(String[] locations){
		
		unCheckLocations();
		for(String loc:locations){
			WebElement elem=locationFilter(locationxPath.get(loc));
			elem.click();
			
		}
	}
	
	
	private void uncheckDays(){
		String []days={"M","T","W","Th","F","S","Su"};
		for(String d:days){
			WebElement elem=dayOfWeek(d,elasticDays.get(d));
			if(elem.isSelected()){
				elem.click();
			}
		}
	}
	
	public void filterByDaysOfWeek(List<String> days){
		
		uncheckDays();
		for(String d:days){
			WebElement elem=dayOfWeek(d,elasticDays.get(d));
			elem.click();
		}
	}
	public void filterByDaysOfWeek(String[] days){
		
		uncheckDays();
		for(String d:days){
			WebElement elem=dayOfWeek(d);
			elem.click();
		}
	}
	
	public void expandDaysFilter(){
		WebElement elem=daysFilterPlus();
		elem.click();
	}
	
	public void expandTimeFilter(){
		WebElement elem=timeFilterPlus();
		elem.click();
	}
	
	public void enterStartTime(String time){
		WebElement elem=startTime();
		elem.sendKeys(time);
	}
	
	public void enterEndTime(String time){
		WebElement elem=endTime();
		elem.sendKeys(time);
	}
	

}
