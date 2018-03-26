package edu.asu.classsearch.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class classearch_page1 {
	
	private  WebDriver driver;
	public classearch_page1(WebDriver driver) {
		this.driver=driver;
	}
	//property
	public void select_frame(){
		
	WebElement s=	driver.switchTo().frame(0).switchTo().frame("frame2").switchTo().frame(0).findElement(By.id("SubFrame"));
	driver.switchTo().frame(s.findElement(By.name("TargetContent")));
	}
	//property
	private  Select  instructor(){
		
		Select elem=new Select(driver.findElement(By.xpath("//select[@id='DERIVED_CLS_DTL_INSTRUCTOR_ID$128$']")));
		return elem;
	}
	private Select course_from_schedule(){
		Select elem=new Select(driver.findElement(By.xpath("//select[@id='DERIVED_REGFRM1_DESCR50$225$']")));
		return elem;
	}
	private WebElement next(){
		WebElement elem =driver.findElement(By.xpath("//a[@id='DERIVED_CLS_DTL_NEXT_PB']"));
		return elem;
	}
	private WebElement Enter_class_Number(){
		WebElement elem=driver.findElement(By.xpath("//input[@id='DERIVED_REGFRM1_CLASS_NBR']"));
		return elem;
	}
	private WebElement Enter(){
		WebElement elem=driver.findElement(By.xpath("//a[@id='DERIVED_REGFRM1_SSR_PB_ADDTOLIST2$106$']"));
		return elem;
	}
	private List<WebElement> Selectfrom_list(){
		List<WebElement> elem=driver.findElements(By.xpath("//table[@id='STDNT_ENRL_SSV1$scroll$0']//tr"));
		return elem;
	}
	private WebElement drop_course(){
		WebElement elem=driver.findElement(By.xpath("//a[@id='DERIVED_REGFRM1_LINK_DROP_ENRL']"));
		return elem;
	}
	public void selct_course_to_drop(int num){
		List<WebElement> elem=Selectfrom_list();
		WebElement course=elem.get(num).findElement(By.xpath("//input[@id='DERIVED_REGFRM1_SSR_SELECT$"+num+"']"));
		course.click();
	}
	public  void select_an_instructor(){
		Select elem=instructor();
		elem.selectByIndex(1);
	}
	public  void navigate_next(){
		WebElement elem=next();
		elem.click();
	}
	public void select_a_course(){
		Select sel=course_from_schedule();
		sel.selectByIndex(1);
	}
	public void class_number(String number){
		WebElement elem=Enter_class_Number();
		elem.sendKeys(number);
	}
	public void action_enter(){
		WebElement elem=Enter();
		elem.click();
	}
	public void drop_a_course(){
		WebElement elem=drop_course();
		elem.click();
	}
	
}
