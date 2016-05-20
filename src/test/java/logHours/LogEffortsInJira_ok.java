package logHours;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import library.ReadData;
//import library.XLDataConfig;


import library.genReport;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.Wait;

public class LogEffortsInJira_ok {
	int rowCount;
	int colCount;
	int sheetCount;
	int rowNum=0;
	int sheetNum=0;
	ReadData excel;
	WebDriver driver;
	String fileName;
	String fileName2;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	int logworkflag=0;
	ExtentReports report;
	ExtentTest logger;
	//genReport extreport;
	
	@BeforeClass
	public void readXLTest() {
		//String reportPath="C:\\Users\\hpandey\\workspace\\Luna\\JiraLogin\\Reports\\JiraReport.html";
		//report=new ExtentReports(reportPath);
		report = new ExtentReports("Reports//LogEffortsinJira.html");
		//extreport=new genReport("Reports//LogEffortsinJira2.html");
		driver=new FirefoxDriver();
		//wait=new WebDriverWait(driver,10);
		fileName="C:\\Users\\hpandey\\workspace\\Luna\\JiraLogin\\TestData\\TestData_jiralocal.xlsx";
		fileName2="C:\\Users\\hpandey\\workspace\\Luna\\JiraLogin\\TestData\\TestData2.xlsx";
		wb=new XSSFWorkbook();
		//sheet=wb.createSheet(fileName);
		excel=new ReadData(fileName);
		rowCount=excel.getRowCount(0);
		rowNum=0;
		sheetCount=excel.getSheetCount();
		//chk value in 5th column
		/*String col4=excel.getData(0,0,4);
		System.out.println("Column 4 = "+col4);
		if(col4.contains("LOG")) {
			System.out.println("Effort Logging is attempted with the current data. If it had failed, To reattempt clear the results column and retry ");
			System.exit(0);
		}*/
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
	
	@Test(priority=1,description="starting it here")
	public void jiraLoginTest() {
		// version 2
		
		logger=report.startTest("JiraLoginTest");
		
		
		driver.get("https://jira.inside.1-stop.biz/");
		logger.log(LogStatus.INFO,"Browser started");
		driver.findElement(By.id("username")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("");
		//driver.findElement(By.id("username")).sendKeys("");
		//driver.findElement(By.id("password")).sendKeys("");
				
		driver.findElement(By.id("login")).click();
		
		logger.log(LogStatus.INFO,"Username Password Entered");
		Assert.assertTrue(driver.getTitle().contains("Dashboard"));
		logger.log(LogStatus.PASS,"Login is Passed");
		
		
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
		//Assert.assertTrue(driver.getTitle().contains(tNum));
		if(driver.getTitle().contains(tNum) & verifyText(driver)) {
			//pass
			writeData(fileName,0, rowNum,4,"LOG OK");
		} else {
			//fail
			writeData(fileName,0, rowNum,4,"LOG FAILED");
		}
		rowNum++;
	}

	public boolean verifyText(WebDriver driver) {
		boolean returnVal=true;
		if (driver.getPageSource().contains("Adjust automatically"))
		{
			returnVal=false;
		}
		return returnVal;
	}
	
	public void writeData(String fileName, int sheetno, int rowno, int colno, String valueStr) throws Exception {
		FileInputStream file= new FileInputStream(new File(fileName));
		XSSFWorkbook wb=new XSSFWorkbook(file);
		XSSFSheet sheet=wb.getSheetAt(sheetno);
		
		sheet.getRow(rowno).createCell(colno).setCellValue(valueStr);
		file.close();
		
		FileOutputStream fout=new FileOutputStream(new File(fileName));
		wb.write(fout);
		fout.close();
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
	
	@AfterClass
	public void teardown() {
		report.endTest(logger);
		report.flush();
		
	}
}
