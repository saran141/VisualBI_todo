package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;

public class CoreUtils {
	
	public void launchapp(WebDriver driver,String url)
	{
		
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().window().maximize();
		 
	}
	
	
	
	public boolean enter_text(WebDriver driver,By locator, String text)
	{
		
			if(elementexists(driver, locator))
			{
				driver.findElement(locator).sendKeys(text);
				driver.findElement(locator).sendKeys(Keys.ENTER);
				By LabelTodo=By.xpath("//label[text()='"+ text +"']");
				if(elementexists(driver, LabelTodo))
				{return true;}
				else
				{
					return false;
				}
					
			}
			else
			{
				System.out.println("Element Not displayed");
				return false;
			}
		
	}
	
	public boolean click_Checkbox(WebDriver driver,By locator)
	{
		try
		{
			driver.findElement(locator).click();
			return true;
		}
		catch(NoSuchElementException e)
		{

			e.printStackTrace();
			return false;
		}
		catch(ElementNotVisibleException e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean hover_Element(WebDriver driver,By locator)
	{
		try
		{
			WebElement ele=driver.findElement(locator);
			Actions act=new Actions(driver);
			act.moveToElement(ele).perform();
			return true;
		}
		catch(NoSuchElementException e)
		{

			e.printStackTrace();
			return false;
		}
		catch(ElementNotVisibleException e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean elementexists(WebDriver driver,By locator)
	{
		try
		{
		if(driver.findElement(locator).isDisplayed())
			return true;
		else
			return false;
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean PageLoadCondition(WebDriver driver)
	{
		
			JavascriptExecutor js=(JavascriptExecutor) driver;
			return js.executeScript("return document.readyState").equals("complete");
		
	}
}
