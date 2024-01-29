package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ErrorMessagePage {
	
	WebDriver driver;
	@FindBy(xpath="//h4[text()='Incorrect phone number']")
	private WebElement invalidPhoneNumber;
	
	@FindBy(xpath="//span[contains(text(),'cannot find an account with that email address')]")
	private WebElement invalidEmail;
	
	@FindBy(xpath="//div[contains(text(),'  Enter your password')]")
	private WebElement missingPassword;
	
	@FindBy(xpath="//div[contains(text(),'Enter your email ')]")
	private WebElement missingEmailPhoneNumber;
	
	
	public ErrorMessagePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyPhoneErrorMessage()
	{
		return  invalidPhoneNumber.isDisplayed();
			
	}
	
	public boolean verifyEmailErrorMessage()
	{
		return invalidEmail.isDisplayed();
	}
	
	public boolean verifyMissingPassword()
	{
		return missingPassword.isDisplayed();
	}
	public boolean verifyMissingEmailPhoneNumber()
	{
		return missingEmailPhoneNumber.isDisplayed();
	}
	
}
