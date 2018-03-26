package edu.asu.classsearch.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class classearch_finalpage {
	
	private  WebDriver driver;
	public classearch_finalpage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	//property
	public void select_frame(){
	driver.switchTo().defaultContent();	
	WebElement s=	driver.switchTo().frame(0).switchTo().frame("frame2").switchTo().frame(0).findElement(By.id("SubFrame"));
	driver.switchTo().frame(s.findElement(By.name("TargetContent")));
	}
	//property
	private WebElement enrollementsuccess(){
		WebElement elem=driver.findElement(By.xpath("//div[@id='win0divDERIVED_REGFRM1_SS_MESSAGE_LONG$0']"));
	return elem;
	}
	
	private WebElement finishenrolling(){
		WebElement elem =driver.findElement(By.xpath("//a[@id='DERIVED_REGFRM1_SSR_PB_SUBMIT']"));
		return elem;
	}
	
	public  void navigate_finishenrolling(){
		WebElement elem=finishenrolling();
		elem.click();
	}
	public String verify_added(){
		WebElement elem=enrollementsuccess();
		return elem.getText();
	}
}
