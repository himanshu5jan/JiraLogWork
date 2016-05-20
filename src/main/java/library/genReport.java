package library;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class genReport {

	ExtentReports report;
	ExtentTest logger;
	
	public genReport(String htmPath) {
		report = new ExtentReports(htmPath);
	}

	public void startTest(String msg) {
		logger=report.startTest(msg);
		//return logger;
	}
	public void logData(LogStatus logst,String msg) {
		logger.log(logst,msg);
	}
	
//logger=report.startTest("JiraLoginTest");
//logger.log(LogStatus.INFO,"Browser started");
//logger.log(LogStatus.INFO,"Username Password Entered");
//logger.log(LogStatus.PASS,"Login is Passed");


}
