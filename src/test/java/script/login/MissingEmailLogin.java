package script.login;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import generic.BaseTest;
import generic.Excel;
import page.ErrorMessagePage;
import page.HomePage;
import page.LoginPage;

public class MissingEmailLogin extends BaseTest {

	@Test(priority=4)
	public void testMissingEmaildLogin() throws InterruptedException{
		
		//1. Verify missing Password
		
		String username="";
    	String password="";
    	String expectedurl="";
		
    	//getting data
    	try {
		username=Excel.getData("./data/AmazonInputSheet.xlsx","InvalidLogin", 3, 0);
		password=Excel.getData("./data/AmazonInputSheet.xlsx","InvalidLogin", 3, 1);
		expectedurl=Excel.getData("./data/AmazonInputSheet.xlsx","InvalidLogin", 3, 2);
    	}
    	catch(Exception e)
    	{
   
    		Reporter.log("Check the input for correctness",true);
    		e.printStackTrace();
    	}
		
    	HomePage home=new HomePage(driver);
    	home.clickOnSignin(driver,wait);
		LoginPage loginPage=new LoginPage(driver);
		loginPage.set_username(username);
		loginPage.continueButtonClick();
		
		Thread.sleep(2000);
		
		//verify error message
		ErrorMessagePage error=new ErrorMessagePage(driver);
		boolean result =error.verifyMissingEmailPhoneNumber();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(result, true);
		driver.quit();

	}

}
