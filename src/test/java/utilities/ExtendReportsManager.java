package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;


public class ExtendReportsManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter; //UI of the report
	public ExtentReports extent; //populating some common info on the report
	public ExtentTest test;// creating test case entries in the report and update the status of the methods
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		//here mention that which format date time stamp
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentDatetimeStamp=df.format(dt);*/
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		
		repName="'Test-Report-"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter("./reports/"+repName);
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		sparkReporter.config().setReportName("Opencart Fucntion Testing");
	    sparkReporter.config().setTheme(Theme.DARK);	
	    
	    extent=new ExtentReports();
	    extent.attachReporter(sparkReporter);
	    extent.setSystemInfo("Application Name", "OpenCart");
	    extent.setSystemInfo("Module", "Admin");
	    extent.setSystemInfo("Sub Module", "Customer");
	    extent.setSystemInfo("User Name", System.getProperty("user.name"));
	    extent.setSystemInfo("Environment", "QA");
	    //getCurrentXmlTest--> here we are getting the xml file data 
	    String os=testContext.getCurrentXmlTest().getParameter("os");
	    extent.setSystemInfo("Operating System",os);
	    
	    
	    String browser=testContext.getCurrentXmlTest().getParameter("browser");
	    extent.setSystemInfo("Browser",browser);
	    
	
		List<String>includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
	    if(!includedGroups.isEmpty()) {
	    	extent.setSystemInfo("Groups",includedGroups.toString());
	    }

	    }
		
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName()); //create new entry in the report
		test.assignCategory(result.getMethod().getGroups());//to display the group in report
		test.log(Status.PASS,result.getName()+" got successfully executed"); //update status pass/fail/skip
	  }
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName()); //create new entry in the report
		test.assignCategory(result.getMethod().getGroups());//to display the group in report
		
		test.log(Status.FAIL,result.getName()+" got failed"); //update status pass/fail/skip
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try
		{
			String imgPath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(Exception e1)
		{
			 //it will display in console window
			e1.printStackTrace();
		}
	  }
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName()); //create new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+" got skipped"); //update status pass/fail/skip
		test.log(Status.INFO,result.getThrowable().getMessage());
	  }
	
	public void onFinish(ITestContext context) {
		extent.flush();
		//Once finish the execution report open automatically
		String pathOfExtentReport=System.getProperty("user.dir")+"/reports/"+repName;
		File extentReort=new File(pathOfExtentReport);
		
		try
		{
			Desktop.getDesktop().browse(extentReort.toURI());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
 
}
