package reusable;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.ToDoPage;

public class Operations {

		public void performAdd(WebDriver driver,String item_value,ExtentTest ext)
		{
			 ToDoPage tpg=new ToDoPage();	
			 int item_count;
			  item_count=tpg.getItemCount(driver);
			  if(tpg.addItem(driver, item_value))
			  {
				  assertEquals(tpg.getItemCount(driver), item_count+1, "Add ToDo is working");
				  if(tpg.getItemCount(driver)==item_count+1)
				  {
					  ext.log(LogStatus.PASS, "Add ToDo is working::Item "+item_value+" is added successfully");
				  }
				  else
				  {
					  ext.log(LogStatus.FAIL, "Add ToDo is NOT working::Item "+item_value+" is NOT added successfully");
				  }
			  }
			  else
			  {
				  ext.log(LogStatus.FAIL, "Add ToDo is NOT working");
			  }
		}
		
		public void performComplete(WebDriver driver,String item_value,ExtentTest ext)
		{
			ToDoPage tpg=new ToDoPage();
			int item_count; 
			item_count=tpg.getItemCount(driver);
			  if(item_count>0)
			  {
				  if(tpg.markComplete(driver, item_value))
				  { 
					  assertEquals(tpg.getItemCount(driver), item_count-1, "Complete ToDo is working");
					  if(tpg.getItemCount(driver)==item_count-1)
						  ext.log(LogStatus.PASS, "Complete ToDo is working::Item "+ item_value +" is marked as Complete successfully");
					  else
						  ext.log(LogStatus.FAIL, "Complete ToDo is working::Item "+ item_value +" is NOT marked as Complete successfully");
				  }
				  else
				  {
					  ext.log(LogStatus.FAIL, "Complete ToDo is NOT working");
				  }
			  }
			  else
			  {
				  assertTrue(item_count>0, "No item available to mark as Complete");
				  ext.log(LogStatus.FAIL, "No item available to mark as Complete");
			  }
		}
		
		public void performDelete(WebDriver driver,String item_value,ExtentTest ext)
		{
			  ToDoPage tpg=new ToDoPage();
			  By LiItem=By.xpath("//label[text()='"+ item_value +"']/preceding-sibling::input/../parent::li");
			  try
				  {
				  String getClass=driver.findElement(LiItem).getAttribute("class");
				  int exp_deletecount;
				  if(getClass.contentEquals("ng-scope completed"))
					{
					  exp_deletecount=tpg.getItemCount(driver);
					}
				  else
				  {
					  exp_deletecount=tpg.getItemCount(driver)-1;
				  }
				  if(tpg.deleteItem(driver, item_value))
				  { 
					  assertEquals(tpg.getItemCount(driver), exp_deletecount, "Delete ToDo is working");
					  if(tpg.getItemCount(driver)==0)
						  ext.log(LogStatus.PASS, "Delete ToDo is working::Item "+ item_value +" is Deleted successfully");
					  else
						  ext.log(LogStatus.FAIL, "Delete ToDo is working::Item "+ item_value +" is NOT Deleted successfully");
				  }
				  else
				  {
					  ext.log(LogStatus.FAIL, "Delete ToDo is NOT working");
				  }
				  }
			  catch(NoSuchElementException e)
				{

					e.printStackTrace();
					
				}
				catch(ElementNotVisibleException e)
				{
					e.printStackTrace();
					
				}
			  
		}
		
		public void performFilter(WebDriver driver,String filtertype,ExtentTest ext,int expcount)
		{
			ToDoPage tpg=new ToDoPage();
			if(tpg.filterItem(driver, filtertype))
			{
				assertEquals(tpg.getItemCount(driver), expcount, "Filter ToDo is working");
				  if(tpg.getItemCount(driver)==expcount)
					  ext.log(LogStatus.PASS, "Filter ToDo is working for filter type::"+filtertype);
				  else
					  ext.log(LogStatus.FAIL, "Filter ToDo is NOT working for filter type::"+filtertype);
			}
			else
			  {
				  ext.log(LogStatus.FAIL, "Filter ToDo is NOT working");
			  }
		}
}
