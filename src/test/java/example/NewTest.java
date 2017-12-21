package example;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import classes.ResultLinkedIn;
import classes.SearchLinkedIn;
import classes.SignInLinkedIn;


public class NewTest {
	private WebDriver driver;
	private SignInLinkedIn SI ;
	private SearchLinkedIn SE;
	private ResultLinkedIn RE;

	
	@Test
	@Parameters({"url","email","password","key"})
	public void Connexion_HomePage(String url, String email, String password, String key) throws InterruptedException{
        SI = new SignInLinkedIn(driver);
        SI.SetWEBAdress(driver, url);
        SI.loginTo(email, password);   
	 }
	
	@Test
	@Parameters({"url","email","password","key", "country"})
	public void DoSearch(String url, String email, String password, String key, String country) throws InterruptedException{
	    SE = new SearchLinkedIn(driver);
	    SE.searchKey(key,country);
	    Thread.sleep(4000);
	}
	  
	@Test
	@Parameters({"url","email","password","key", "country"})
	public void ListResult(String url, String email, String password, String key, String country) throws InterruptedException{
	    RE = new ResultLinkedIn(driver);
	    RE.ResultList(driver,key);
	 }

 
	@SuppressWarnings("deprecation")
	@BeforeTest
	public void beforeTest(){
		System.setProperty("webdriver.gecko.driver","C:\\geckodriver-v0.19.0-win64\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", false);
		driver = new FirefoxDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}	
}