package page;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage 
{
	
	@FindBy(xpath="//a[@aria-label='Amazon.in']")
	private WebElement amazonHomeLogo;
	
	@FindBy(id="nav-link-accountList-nav-line-1")
	private WebElement welcomeMessage;
	
	@FindBy(id="twotabsearchtextbox")
	private WebElement searchBox;
	
	@FindBy(id="nav-link-accountList")
	private WebElement signInMenu;
	
	
//	@FindBy(xpath="(//span[text()='Sign in'])[1]")
//	private WebElement signInBtn;

	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}
	
	
	public boolean isHomePageDisplayed(WebDriverWait wait) {	
		try {
		wait.until(ExpectedConditions.visibilityOf(amazonHomeLogo));
		return welcomeMessage.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clickOnSignin(WebDriver driver,WebDriverWait wait)
	{
		try {
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		Actions action=new Actions(driver);
		action.moveToElement(signInMenu).click().perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}


	public String getActualUrl(WebDriver driver) {
		// TODO Auto-generated method stub
		return driver.getCurrentUrl();
	}

}
