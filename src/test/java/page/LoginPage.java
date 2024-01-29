package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
	  
	//declaration
    @FindBy(id = "ap_email") 
    WebElement username; 
    @FindBy(id = "ap_password") 
    WebElement password; 
    @FindBy(id = "signInSubmit") 
    WebElement button; 
    
    @FindBy(id = "continue") 
    WebElement continueButton; 
    
  
    public LoginPage(WebDriver driver) { 
        // initialize elements 
        PageFactory.initElements(driver, this); 
    } 
  
    //utilization
    public void set_username(String usern) { 
        username.clear(); 
        username.sendKeys(usern); 
    } 
  
    public void set_password(String userp) { 
        password.clear(); 
        password.sendKeys(userp); 
    } 
    
    public void click_button() { 
        button.submit(); 
    } 
  
    public void continueButtonClick() { 
        continueButton.submit(); 
    } 
} 

