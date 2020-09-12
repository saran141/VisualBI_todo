package reusable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import businesscomponents.ToDos;
import utilities.CoreUtils;

public class BaseDriver extends CoreUtils {
	public static Properties myprop;
	public static InputStream in;
	private static ThreadLocal<WebDriver>driver=new ThreadLocal<WebDriver>();
	public static void loadproperties()
	{
		myprop=new Properties();
	    in=ToDos.class.getClassLoader().getResourceAsStream("globalsettings.properties");
		  try {
			myprop.load(in);
			
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	public void setDriver(String browser)
	{
		
		if(browser.contentEquals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", myprop.getProperty("chromeDriver"));
		driver.set(new ChromeDriver());
		}
		else if(browser.contentEquals("firefox"))
		{
		System.setProperty("webdriver.gecko.driver", myprop.getProperty("firefoxDriver"));
		driver.set(new FirefoxDriver());
		}
			
	}
	public WebDriver getDriver()
	{
		return driver.get();
	}
	
}
