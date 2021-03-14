package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utilities.Log;

public class EmployeeListPage extends BaseClass{
	
	public EmployeeListPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static void verfiyEmployee() throws Exception{
		WebElement element_EmployeeList=driver.findElement(By.xpath("//span[text()='Employee List']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",element_EmployeeList);
		Log.info("Employee list is clicked");
		
		driver.findElement(By.xpath("//input[@id='employee_name_quick_filter_employee_list_value']")).sendKeys(AddEmployeePage.completename);
		Log.info(AddEmployeePage.completename+" is entered as Employee Name Quick filter");
		driver.findElement(By.xpath("//input[@id='employee_name_quick_filter_employee_list_value']")).sendKeys(Keys.ENTER);
		Log.info("Enter button action is performed");
		
		driver.findElement(By.xpath("//span[@class='angucomplete-title']")).click();
		Log.info("Search Result found and click action is performed");
		Thread.sleep(3000);
		String txt_employeeName=driver.findElement(By.xpath("//table[@id='employeeListTable']/tbody/tr[1]/td[3]")).getText();
		Assert.assertEquals(txt_employeeName, AddEmployeePage.completename);
		Log.info("Complete Name is found and it is matched");
		
		
		String txt_location=driver.findElement(By.xpath("//table[@id='employeeListTable']/tbody/tr[1]/td[8]")).getText();
		Assert.assertEquals(txt_location, AddEmployeePage.location);
		Log.info("location is found and it is matched");
	}
}
