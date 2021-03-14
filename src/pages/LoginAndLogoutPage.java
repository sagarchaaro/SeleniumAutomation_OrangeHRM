package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utilities.Constants;
import utilities.ExcelConfig;
import utilities.Log;

public class LoginAndLogoutPage extends BaseClass{
	

	public LoginAndLogoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static void login(int rowNumber) throws Exception{
		//Login functionality
		
		String userName=ExcelConfig.getCellData(rowNumber, Constants.col_UserName, Constants.sh_AddEmployee);
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(userName);
		Log.info("Admin is entered as a Username");
				
		String password=ExcelConfig.getCellData(rowNumber, Constants.col_Password,Constants.sh_AddEmployee);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		Log.info("Admin@123 is entered as a Password");
				
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		Log.info("Login button is clicked");
				
				//Verifying the Dashboard
		WebElement element_pageDisplay=driver.findElement(By.xpath("//li[@class='page-title']"));
		boolean pageDisplay=element_pageDisplay.isDisplayed();
		if(pageDisplay){
			Log.info("Login is successful and Home page is loaded");
		}else{
			Log.info("Login is not successful and test case is failed");
			Assert.assertEquals(pageDisplay, true);
		}
				
	}
	
	public static void logout(){

		WebElement ele_dd_logout=driver.findElement(By.xpath("//i[text()='keyboard_arrow_down']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele_dd_logout);
		Log.info("Logout dropwn is clicked");
		
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		Log.info("Logout button is clicked");
	}
	
}
