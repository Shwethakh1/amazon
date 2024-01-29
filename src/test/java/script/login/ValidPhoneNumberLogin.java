package script.login;

import java.io.IOException;


import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import generic.BaseTest;
import generic.Excel;
import page.HomePage;
import page.LoginPage;


public class ValidPhoneNumberLogin extends generic.BaseTest
{
     
    @Test(priority =1)
	public  void testValidLogin() throws InterruptedException
    {
    	String username="";
    	String password="";
    	String expectedurl="";
		
    	//getting data
    	try {
		username=Excel.getData("./data/AmazonInputSheet.xlsx","ValidLogin", 1, 0);
		password=Excel.getData("./data/AmazonInputSheet.xlsx","ValidLogin", 1, 1);
		expectedurl=Excel.getData("./data/AmazonInputSheet.xlsx","ValidLogin", 1, 2);
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
		loginPage.set_password(password);
		loginPage.click_button();
		Thread.sleep(2000);
		
		//verify homepage
		
		boolean result=home.isHomePageDisplayed(wait);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(result, true, "Fail: Home page is not displayed");
		
		softAssert.assertAll();

	}

}
