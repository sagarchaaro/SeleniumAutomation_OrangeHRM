package testcases;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.AddEmployeePage;
import pages.BaseClass;
import pages.EmployeeListPage;

import pages.LoginAndLogoutPage;

import pages.PimPage;
import utilities.Constants;
import utilities.ExcelConfig;
import utilities.Log;
import utilities.Utils;


public class TC_01_AddEmployee {
	
	public static int rowNum_TestCase, rowNum_AddEmployee;
	public static WebDriver driver;
	
	@BeforeMethod
	public void beforeTestCase() throws Exception{
		String projPath= System.getProperty("user.dir");
		DOMConfigurator.configure("C:\\Eclipse\\SeleniumLectures\\SeleniumAutomation_OrangeHRM\\test-resources\\Log4j.xml");
		
		Log.startTestCase("AddEmployee");
		Log.info("The Project Path is:"+projPath);
		
		ExcelConfig.setExcelFile("C:\\Eclipse\\SeleniumLectures\\SeleniumAutomation_OrangeHRM\\test-resources\\TestData.xlsx");
		rowNum_TestCase=ExcelConfig.getRowContains(Constants.TestCase_ID, 2, "TestCases");
		System.out.println("Row Number from the TestCases sheet is :"+rowNum_TestCase);
		rowNum_AddEmployee=ExcelConfig.getRowContains(Constants.TestCase_ID, 2, Constants.sh_AddEmployee);
		System.out.println("Row Number from the Add Employee sheet is :"+rowNum_AddEmployee);
	}
	
	@Test
	public void addEmployee() throws Exception{
		
		driver=Utils.launchBrowser();
		new BaseClass(driver);
		
		//Login functionality
		LoginAndLogoutPage.login(rowNum_AddEmployee);
		
		//PIM and AddEmployee Click
		PimPage.clickPIM_AddEmployee();
		
		//AddEmployee
		AddEmployeePage.firstpage_AddEmployee(rowNum_AddEmployee);
		AddEmployeePage.second_AddEmployee(rowNum_AddEmployee);
		AddEmployeePage.third_AddEmployee(rowNum_AddEmployee);
		
		//VerfiyEmployee
		EmployeeListPage.verfiyEmployee();	
		
		//Logout
		LoginAndLogoutPage.logout();
		
	}
	
	@AfterMethod
	public static void tearDown() throws Exception{
		
		Thread.sleep(2000);
		
		driver.quit();
		
		Log.endTestCase();	
	}
}
