package classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInLinkedIn {

	 WebDriver driver;
	 //Page Factory: Définition des element HTML utilisé
	 @FindBy(id="login-email")
	 WebElement emailFactory;
	 @FindBy(id="login-password")
	 WebElement passwordFactory;
	 @FindBy(id="login-submit")
	 WebElement btnLoginFactory;
	 
 
	 public SignInLinkedIn( WebDriver driver){
	 this.driver = driver;
	 }
	 
	public void SetWEBAdress(WebDriver webDriver, String webAdress){
		driver.get(webAdress);
		PageFactory.initElements(driver, this);
	}
	public void setEmail(String email) {
		emailFactory.sendKeys(email);
	}
	public void setPassword(String password) {
		passwordFactory.sendKeys(password);
	}	
	public void clickLogin(){
		 btnLoginFactory.click();
	} 
	 public void loginTo(String emailP,String passwordP){
		   this.setEmail(emailP);
		   this.setPassword(passwordP);
		   this.clickLogin();        
	} 
}
