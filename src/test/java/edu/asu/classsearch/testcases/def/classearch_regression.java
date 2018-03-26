package edu.asu.classsearch.testcases.def;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import edu.asu.classsearch.pages.classearch_HomePage_Methods;
import edu.asu.classsearch.pages.classearch_commons;
import org.junit.Assert;

public class classearch_regression {
private classearch_HomePage_Methods cl;
private WebDriver driver;
private String result,result1;

public void connection(){
	driver=classearch_commons.getconn();
}
	//TC_1:
		public void classorclasscatalog(){
			WebElement elem=cl.classearch();
			WebElement elem1=cl.classcatalog();
			result=elem.getText();
			result1=elem1.getText();
		}
		public void verifyresult(){
			Assert.assertEquals("Class Search",result);
			Assert.assertEquals("Class Catalog",result1);
		}
		
	//TC_2:
		public void onlineandcampus(){
			cl.check_campusoronline("campus");
			result=String.valueOf(cl.location());
			cl.check_campusoronline("online");
			
		}
		
}
