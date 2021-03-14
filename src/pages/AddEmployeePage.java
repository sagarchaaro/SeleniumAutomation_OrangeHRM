package pages;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Constants;
import utilities.ExcelConfig;
import utilities.Log;

public class AddEmployeePage extends BaseClass{
	
	public static String completename, location;
	
	public AddEmployeePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static void firstpage_AddEmployee(int rowNumber) throws Exception{
		String firstname=ExcelConfig.getCellData(rowNumber, Constants.col_FirstName, Constants.sh_AddEmployee);
		String lastname=ExcelConfig.getCellData(rowNumber, Constants.col_LastName, Constants.sh_AddEmployee);
		completename=firstname+" "+lastname;
		location=ExcelConfig.getCellData(rowNumber, Constants.col_Location, Constants.sh_AddEmployee);
		
		//Entering the Add Employee Info - Page1
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(firstname);
		Log.info("Irfan is entered as a first name");
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(lastname);
		Log.info("Anwar is entered as a last name");
		driver.findElement(By.xpath("//div[@id='location_inputfileddiv']/div/input[@class='select-dropdown']")).click();
		List<WebElement> elements_location=driver.findElements(By.xpath("//div[@id='location_inputfileddiv']/div/ul/li/span"));
		for(WebElement ele_location:elements_location){
			String txt_location=ele_location.getText();
			
			if(txt_location.contains(location)){
				ele_location.click();
				Log.info("Indian Development Cener location is found and it is clicked");
				break;
			}
		}
		driver.findElement(By.xpath("//a[@id='systemUserSaveBtn']")).click();
		Log.info("Next button is clicked");
	}
	
	public static void second_AddEmployee(int rowNumber) throws Exception{
		//To Wait Add Employee _page2 Loading
		Wait<WebDriver> wait_Page2 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).
				pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
					
		wait_Page2.until(new Function<WebDriver, Boolean>() {
				
		public Boolean apply(WebDriver driver){
							
			WebElement ele=driver.findElement(By.xpath("//label[text()='First Name']"));
						
			Log.info("Text message is:"+ele.getText());
			if(ele.getText().equalsIgnoreCase("First Name*")){
				Log.info("Add Employee 2d page is loaded");
				return true;
			}else{
				Log.info("Add Employee 2nd page is not loaded");
				return false;
			}
		}
						
		});		
				
		//Entering the Add Employee Info - Page2
		String bloodGroup=ExcelConfig.getCellData(rowNumber, Constants.col_BloodGroup, Constants.sh_AddEmployee);
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Log.info("Scrolled Down till the bottom of the page");
		WebDriverWait wait_bloodGroup=new WebDriverWait(driver, 20);
		wait_bloodGroup.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Blood Group']/../div/input")));
		driver.findElement(By.xpath("//label[text()='Blood Group']/../div/input")).click();
		Log.info("Blood Group is clicked");
		List<WebElement> element_bloodgroups=driver.findElements(By.xpath("//label[text()='Blood Group']/../div/ul/li/span"));
		
		for(WebElement ele_bloodGroup:element_bloodgroups){
			String txt_bloodGroup=ele_bloodGroup.getText();
			if(txt_bloodGroup.equalsIgnoreCase(bloodGroup)){
				ele_bloodGroup.click();
				Log.info("Blood Group is selected "+bloodGroup);
				break;
			}
		}
				
		String hobbies=ExcelConfig.getCellData(rowNumber, Constants.col_Hobbies, Constants.sh_AddEmployee);
		driver.findElement(By.xpath("//label[text()='Hobbies']/../input")).sendKeys(hobbies);
		Log.info("Playing Games is entered as Hobbies");
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		Log.info("Clicked on Next button");
		
	}
	
	public static void third_AddEmployee(int rowNumber) throws Exception{
		//To Wait Add Employee _page3 Loading
		Wait<WebDriver> wait_Page3 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).
				pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
						
		wait_Page3.until(new Function<WebDriver, Boolean>() {
					
		public Boolean apply(WebDriver driver){
								
			WebElement ele=driver.findElement(By.xpath("//label[text()='Job Title']"));
							
			Log.info("Text message is:"+ele.getText());
			if(ele.getText().equalsIgnoreCase("Job Title")){
				Log.info("Add Employee 3rd page is loaded");
				return true;
			}else{
				Log.info("Add Employee 3rd page is not loaded");
				return false;
			}
		}
								
		});
				
				//Entering the Add Employee Info - Page3
		String region=ExcelConfig.getCellData(rowNumber, Constants.col_Region, Constants.sh_AddEmployee);
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Log.info("Scrolled Down till the bottom of the page");
		WebDriverWait wait_Region=new WebDriverWait(driver, 20);
		wait_Region.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Region']/../div/input")));
		driver.findElement(By.xpath("//label[text()='Region']/../div/input")).click();
		Log.info("Region drop down is clicked");
		List<WebElement> element_regions=driver.findElements(By.xpath("//label[text()='Region']/../div/ul/li/span[text()]"));
		
		for(WebElement ele_region:element_regions){
			String txt_region=ele_region.getText();
			System.out.println("txt_region:"+txt_region);
			if(txt_region.equalsIgnoreCase(region)){
				ele_region.click();
				Log.info("Region is selected as "+region);
				break;
			}
		}
				
		String fte=ExcelConfig.getCellData(rowNumber, Constants.col_FTE, Constants.sh_AddEmployee);
		driver.findElement(By.xpath("//label[text()='FTE']/../div/input")).click();
		Log.info("FTE drop down is clicked");
		List<WebElement> element_ftes=driver.findElements(By.xpath("//label[text()='FTE']/../div/ul/li/span"));
		
		for(WebElement ele_fte:element_ftes){
			String txt_fte=ele_fte.getText();
			if(txt_fte.equalsIgnoreCase(fte)){
				ele_fte.click();
				Log.info("FTE is selected as "+fte);
				break;
			}
		}
				
		String tempdepart=ExcelConfig.getCellData(rowNumber, Constants.col_TemporaryDepartment, Constants.sh_AddEmployee);
		driver.findElement(By.xpath("//label[text()='Temporary Department']/../div/input")).click();
		Log.info("Temporary Department drop down is clicked");
		List<WebElement> element_temporarydepartments=driver.findElements(By.xpath("//label[text()='Temporary Department']/../div/ul/li/span[text()]"));
		
		for(WebElement element_temporarydepart:element_temporarydepartments){
			String txt_department=element_temporarydepart.getText();
			if(txt_department.equalsIgnoreCase(tempdepart)){
				element_temporarydepart.click();
				Log.info("Temporary Department is selected as "+tempdepart);
				break;
			}
		}
				
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Log.info("Save button is clicked");
				
		Thread.sleep(4000);
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		Thread.sleep(1000);
		//To Wait Add Employee _page3 Loading
		Wait<WebDriver> wait_completeAddEmployee = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).
					pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
									
		wait_completeAddEmployee.until(new Function<WebDriver, Boolean>() {
								
		public Boolean apply(WebDriver driver){
									
				WebElement ele=driver.findElement(By.xpath("//label[text()='Employee Id']"));
										
				Log.info("Text message is:"+ele.getText());
				if(ele.getText().equalsIgnoreCase("Employee Id")){
					Log.info("Complete Add Employee page is loaded");
					return true;
				}else{
					Log.info("Complete Add Employee page is not loaded");
					return false;
				}
		}
										
		});
				
	}
}
