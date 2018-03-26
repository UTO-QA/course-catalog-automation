package edu.asu.classsearch.pages;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.internal.runners.statements.ExpectException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.ImplicitlyWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import edu.asu.classsearch.input.ClassSearchInputs;

public class Loginpage {
	//property username
	private  WebDriver driver;
	public Loginpage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	private  WebElement property_username(){
		WebElement elem=driver.findElement(By.id("username"));
		return elem;
		
	}
	private WebElement property_password(){
		WebElement elem=driver.findElement(By.id("password"));
		return elem;
		
	}
	private WebElement property_submit(){
		WebElement elem=driver.findElement(By.className("submit"));
		return elem;
		
	}
	
	public void get_login(){
		Scanner sc=new Scanner(System.in);
		WebElement username=property_username();
		final WebElement password=property_password();
		ClassSearchInputs login=new ClassSearchInputs();
		String cred[]=login.login().split(",");
		username.sendKeys(cred[0]);
		password.sendKeys(cred[1]);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		WebElement submit=property_submit();
		submit.click();
	}
}
