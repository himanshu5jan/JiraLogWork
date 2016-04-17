package logHoursSrcPkg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LogEffortsInJiraMain {
	
	public static void Main(String args[]) {
		
		WebDriver driver=new FirefoxDriver();
		driver.get("https://himanshupandey.atlassian.net");
		driver.findElement(By.id("username")).sendKeys("himanshu.tech10@gmail.com");
		driver.findElement(By.id("password")).sendKeys("!nnocuous");
		driver.findElement(By.id("login")).click();
				
	}

}
