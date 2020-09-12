package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.CoreUtils;

public class ToDoPage extends CoreUtils {
	private static final By TxtInputTodo=By.xpath("//input[contains(@class,'new-todo')]");
	private static final By SpanItemCount=By.xpath("//span[@class='todo-count']/strong");
	private static final By LnkActive=By.linkText("Active");
	private static final By LnkCompleted=By.linkText("Completed");
		
	public boolean isApplaunched(WebDriver driver,String url)
	{
		launchapp(driver, url);
		if(elementexists(driver,TxtInputTodo))
		{
			return true;
		}
		{
			return false;
		}		
	}
	
	public int getItemCount(WebDriver driver)
	{
		int count=0;
		if(elementexists(driver,SpanItemCount))
		{
			count=Integer.parseInt(driver.findElement(SpanItemCount).getText());
			
		}
		
		return count;
			
	}
	
	public boolean addItem(WebDriver driver,String item)
	{
		if(enter_text(driver,TxtInputTodo,item))
		{
			return true;
			
		}
		else		
		{
			return false;		
		}
	}
	public boolean markComplete(WebDriver driver,String item)
	{
		By LabelTodo=By.xpath("//label[text()='"+ item +"']");
		
		if(elementexists(driver, LabelTodo))
		{
			
			//By ChkboxItem=By.xpath("//label[text()='"+ item +"']/preceding-sibling::input");
			By ChkboxItem=By.xpath("//label[text()='"+ item +"']/../input");
			if(click_Checkbox(driver,ChkboxItem))
			{
				
				By LiItem=By.xpath("//label[text()='"+ item +"']/preceding-sibling::input/../parent::li");
				String getClass=driver.findElement(LiItem).getAttribute("class");
				
				if(getClass.contentEquals("ng-scope completed"))
				{
					return true;
				}
				else
				{
					return false;
				}
				
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		
	}
	public boolean deleteItem(WebDriver driver,String item)
	{
		By LabelTodo=By.xpath("//label[text()='"+ item +"']");
		
		if(hover_Element(driver, LabelTodo))
		{
			
			By DeleteItem=By.xpath("//label[text()='"+ item +"']/following-sibling::button[@class='destroy']");			
			if(elementexists(driver, DeleteItem))
			{
				click_Checkbox(driver, DeleteItem);
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		
	}
	public boolean filterItem(WebDriver driver,String filtertype)
	{
		if(filtertype.contentEquals("Active"))
		{
			if(click_Checkbox(driver,LnkActive))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
				
		else
		{
			if(click_Checkbox(driver,LnkCompleted))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
	}
}
