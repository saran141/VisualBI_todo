package businesscomponents;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.ToDoPage;
import reusable.BaseDriver;
import reusable.Operations;

public class ToDos extends BaseDriver{
	
	ExtentReports reports;
	@BeforeClass
	public void initializereport()
	{
	  reports=new ExtentReports(System.getProperty("user.dir")+"\\ToDos.html",true);
	  BaseDriver.loadproperties();
	}
	
	@BeforeMethod
	@Parameters({"browser"})	  
	  public void beforeMethod(@Optional("")String browser) {
		  setDriver(browser);
	  }
	
  @Test
  @Parameters({"url"})
  public void TC_Add_Complete_Delete_Filter_ToDo(@Optional("")String url) {
	  	  
		  ExtentTest ext=reports.startTest("TC_Add_Complete_Delete_Filter_ToDo");
		  ext.log(LogStatus.INFO, "Test Execution Started");
		  WebDriver driver=getDriver(); 
		 	  
		  ToDoPage tpg=new ToDoPage();	
		  if(tpg.isApplaunched(driver, url))
		  {
			  assertTrue(1==1, "Application Launched Successfully");
			  ext.log(LogStatus.PASS, "Application Launched Successfully");
			  
			  //Verify if the user is able to perform Add operation
			  String item_value="Goto Library";
			  Operations op=new Operations();
			  op.performAdd(driver, item_value, ext);
			  
			  //Verify if the user is able to perform Complete operation
			  op.performComplete(driver, item_value, ext);
			  
			  //Verify if the user is able to perform Delete operation
			  op.performDelete(driver, item_value, ext);
			  
			  
			  //Verify if the user is able to perform Filter operation
			  item_value="Attend Meeting";
			  op.performAdd(driver, item_value, ext);
			  item_value="Pay Bills";
			  op.performAdd(driver, item_value, ext);
			  op.performComplete(driver, item_value, ext);
			  //verify Active ToDo
			  op.performFilter(driver, "Active", ext, 1);
			  //verify Complete ToDo
			  op.performFilter(driver, "Completed", ext, 1);
		  }
		  else
		  {
			  ext.log(LogStatus.FAIL, "Application NOT Launched Successfully");
		  }
		  
		  ext.log(LogStatus.INFO, "Test Execution Completed");
		  closereports(ext);
		  
  }
  
  
  
  public void closereports(ExtentTest ext)
  {
	  reports.endTest(ext);
	  reports.flush();
  }
  
  @AfterMethod	  
	  public void afterMethod() {
	  	WebDriver driver=getDriver(); 
	  	driver.close();
	  	driver.quit();
	  }
}
