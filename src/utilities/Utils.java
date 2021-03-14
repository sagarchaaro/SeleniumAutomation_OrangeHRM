package utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.BaseClass;

public class Utils {
	public static String timeStamp(String requiredFormat) {
		DateFormat df = new SimpleDateFormat(requiredFormat);
		Date date1 = new Date();
		String timestamp = df.format(date1);
		Log.info("The value of TIMESTAMP is : " + timestamp);
		return timestamp;
	}
	
	public static void createDir(String dirPath) {
		File file = new File(dirPath);
		boolean bool = file.mkdir();
		if (bool) {
			Log.info("The Directory created successfully");
		} else {
			Log.info("The specified directory couldn't created");
		}
	}
	
	public static WebDriver launchBrowser(){
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://webautomationte-trials70.orangehrmlive.com");
		Log.info("OrangeHRM website is loaded");
		
		return driver;
	}
	
}
