package edu.asu.classsearch.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class classearch_page2 {
	
	private  WebDriver driver;
	public classearch_page2(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	//property
	
	private WebElement step_2of3(){
		WebElement elem =driver.findElement(By.xpath("//a[@id='DERIVED_REGFRM1_LINK_ADD_ENRL$82$']"));
		return elem;
	}
	private WebElement finishstep(){
		WebElement elem =driver.findElement(By.xpath("//a[@id='DERIVED_REGFRM1_SSR_PB_SUBMIT']"));
		return elem;
	}
	public void select_frame(){
	driver.switchTo().defaultContent();
	WebElement s=	driver.switchTo().frame(0).switchTo().frame(0).switchTo().frame(0).findElement(By.id("SubFrame"));
	driver.switchTo().frame(s.findElement(By.name("TargetContent")));
	}
	//property

	
	public  void navigate_proceed_step_2of3(){
		WebElement elem=step_2of3();
		elem.click();
	}
	public  void action_finishstep(){
		WebElement elem=finishstep();
		elem.click();
	}

}
