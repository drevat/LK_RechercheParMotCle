package classes;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchLinkedIn {

	
	 WebDriver driver;
	 
	 @FindBy(xpath= "//div[@class='nav-search-typeahead']//input[@role='combobox']")
	 WebElement searchFactory;
	 
	 @FindBy(xpath= "//div[@id='nav-search-controls-wormhole']//button[@class='search-typeahead-v2__button typeahead-icon']")
	 WebElement btnSearchFactory;

	 public SearchLinkedIn( WebDriver driver){
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }

	public void setKey(String key){
		 searchFactory.sendKeys(key);
	}  
	public void clickSearch(){
		btnSearchFactory.click();
	}
	
	public void setCountry(String country) throws InterruptedException{
		driver.findElement(By.xpath("//li[@class='search-facet search-facet--geo-region ember-view']//button[@class='search-facet__legend']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//ol[@class='search-facet__values  focused-easeOut-motion']//button[@id='sf-facetGeoRegion-add']")).click();
		driver.findElement(By.xpath("//ol[@class='search-facet__values  focused-easeOut-motion']//input[@class='ember-text-field ember-view']")).sendKeys(country);
		Thread.sleep(500);
		driver.findElement(By.xpath("//ol[@class='search-facet__values  focused-easeOut-motion']//input[@class='ember-text-field ember-view']")).sendKeys(Keys.ENTER);

	}
	public void searchKey(String key,String country) throws InterruptedException{
		   this.setKey(key);
		   this.clickSearch();
		   this.setCountry(country);
	} 
}