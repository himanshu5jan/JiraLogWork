package logHours;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import library.ReadData;
//import library.XLDataConfig;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.Wait;

public class LogEfforts {
	int rowCount;
	int colCount;
	int sheetCount;
	int rowNum;
	ReadData excel;
	WebDriver driver;
	WebDriverWait wait;
	ExtentReports report;
	ExtentTest logger;
	
	static String driverPath="C:\\Users\\hpandey\\workspace\\Luna\\JiraLogin\\Drivers";
	@BeforeClass
	public void readXLTest() {
		//driver=new FirefoxDriver();
		report=new ExtentReports("C:\\Users\\hpandey\\workspace\\Luna\\JiraLogin\\Reports\\JiraReport.html");
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		//wait=new WebDriverWait(driver,10);
		wait=new WebDriverWait(driver,30);
		excel=new ReadData("C:\\Users\\hpandey\\workspace\\Luna\\JiraLogin\\TestData\\TestData.xlsx");
		
		rowCount=excel.getRowCount(0);
		rowNum=0;
		sheetCount=excel.getSheetCount();
		System.out.println("Total Sheets= "+sheetCount);
		System.out.println("Total Rows= "+rowCount);
	}
	 
	@DataProvider(name="JiraTasks")
	public Object[][] passData() {
		System.out.println("Total Sheets= "+sheetCount);
	    Object[][] data=new Object[rowCount][4];
	 
	    	//int sheetno=0;
	 		//rowCount=excel.getRowCount(sheetno);
	 		for(int i=0;i<rowCount;i++) 
	 		{
	 			data[i][0]=excel.getData(0,i,0);
	 			data[i][1]=excel.getData(0,i,1);
	 			data[i][2]=excel.getData(0,i,2);
	 			data[i][3]=excel.getDate(0,i,3);
	 		}
	 	
		return data;
	}
	
	@Test(priority=1)
	public void jiraLoginTest() {
		//driver.get("https://himanshupandey.atlassian.net");
		//driver.findElement(By.id("username")).sendKeys("himanshu.tech@gmail.com");
		//driver.findElement(By.id("password")).sendKeys("");
		logger=report.startTest("JIRALOGIN");
		driver.get("https://jira.inside.1-stop.biz/");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    driver.switchTo().frame("gadget-0");	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-username")));
		//driver.findElement(By.id("login-form-username")).sendKeys("username");
		driver.findElement(By.id("login-form-username")).sendKeys("hpandey");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-password")));
		//driver.findElement(By.id("login-form-password")).sendKeys("password");
		driver.findElement(By.id("login-form-password")).sendKeys("");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("login"))).click();
		driver.switchTo().defaultContent();
		logger.log(LogStatus.INFO, "Login done");
		Assert.assertTrue(driver.getTitle().contains("Dashboard"));
		logger.log(LogStatus.PASS, "Login Passed");
	}

	/*@Test(priority=2)
	public void searchTestTask() {
		driver.findElement(By.id("quickSearchInput")).sendKeys("TT-15");
		driver.findElement(By.id("quickSearchInput")).sendKeys(Keys.ENTER);
	}*/
	
	@Test(priority=2,dataProvider="JiraTasks")
	public void logWorkTest(String tDesc, String tNum, String tHours, Date tDate) throws Exception {
		
		System.out.println("tDesc = "+tDesc);
		System.out.println("tNum = "+tNum);
		System.out.println("tHours = "+tHours);
		System.out.println("tDate = "+tDate);
		
		DateFormat df=new SimpleDateFormat("dd/MMM/yy hh:mm");
		String tDateStrT=df.format(tDate);
		String tDateStr=tDateStrT+" AM";
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("quickSearchInput")).sendKeys(tNum);
		driver.findElement(By.id("quickSearchInput")).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//wait.until(ExpectedConditions.elementToBeClickable(By.id("opsbar-operations_more"))).click();
		driver.findElement(By.id("opsbar-operations_more")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("log-work")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("log-work-time-logged")).sendKeys(tHours);
		driver.findElement(By.id("log-work-date-logged-date-picker")).clear();
		driver.findElement(By.id("log-work-date-logged-date-picker")).sendKeys(tDateStr);
		/*driver.findElement(By.id("log-work-date-logged-date-picker")).sendKeys(Keys.TAB);
		driver.findElement(By.id("log-work-date-logged-icon")).sendKeys(Keys.TAB);
		driver.findElement(By.id("log-work-adjust-estimate-auto")).sendKeys(Keys.TAB);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("comment")));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("comment")).clear();
		driver.findElement(By.id("comment")).sendKeys("adfadfadf");*/
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//WebElement comm=driver.findElement(By.id("comment"));
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", comm);
		//driver.findElement(By.id("comment")).sendKeys("these are the comments");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("log-work-submit")).click();
		Assert.assertTrue(driver.getTitle().contains(tNum));
			
		
	}

	@AfterMethod
	public void afterTest(ITestResult result) {
		String tName=result.getName();
		String tCaseId=result.toString();
	System.out.println(" What is the tCaseId "+tCaseId);
		if(result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Test Case "+tName+" Failed !!");
		} else {
			System.out.println("Test Case "+tName+" Passed !!");
		}
	}
}
