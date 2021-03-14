package pages;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import utilities.Log;

public class PimPage extends BaseClass{
	
	public PimPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	public static void clickPIM_AddEmployee(){
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		Log.info("PIM Menu is clicked");
		driver.findElement(By.xpath("//span[text()='Add Employee']")).click();
		Log.info("Add Employee is clicked");
		
		//To Wait Add Employee Loading
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).
				pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
	
		wait.until(new Function<WebDriver, Boolean>() {
		
		public Boolean apply(WebDriver driver){
				
			WebElement ele=driver.findElement(By.xpath("//div[@class='helpvalue schema-form-helpvalue ']"));
					
			Log.info("Text message is:"+ele.getText());
			if(ele.getText().equalsIgnoreCase("* Required field")){
				Log.info("Add Employee page is loaded");
				return true;
			}else{
				Log.info("Add Employee page is not loaded");
				return false;
			}
		}
		
		});
	}
}
