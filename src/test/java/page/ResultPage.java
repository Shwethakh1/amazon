package page;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

import generic.Utility;

public class ResultPage {
	private ExtentTest test;
	@FindBy(id="productTitle")
	private WebElement productTitle;
	
	@FindBy(id="add-to-cart-button")
	private WebElement addToCartButton;
	
	@FindBy(id="attach-accessory-cart-subtotal")
	private WebElement productPrice;
	
	@FindBy(xpath="//span[contains(text(),'Proceed to checkout')]")
	private WebElement checkoutButton;
	
	@FindBy(id="attach-close_sideSheet-link")
	private WebElement closeSideSheet;
	
	@FindBy(id="quantity")
	private WebElement quantity;
	
	public ResultPage(WebDriver driver,ExtentTest test)
	{
		PageFactory.initElements(driver,this);
		this.test=test;
		
		Set<String> allWHS = driver.getWindowHandles();
		for(String wh:allWHS) {
		driver.switchTo().window(wh);
		}
		
		
	}
	
	public String getProductTitle() {
		test.info("returning Product Title");
		return productTitle.getText();
	}
	
	public void clickaddToCartButton() {
		test.info("Clicking Add To Cart Button");
		addToCartButton.click();
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) {
		
			e.printStackTrace();
		}
	}
	
	public String getCheckOutMsg() {
		test.info("returning CheckOut Message");
		return checkoutButton.getText();
	}
	
	public String getProductPrice() {
		test.info("returning product Price");
		return productPrice.getText();
	}
	
	public void closeSidePannel() {
		test.info("close Side Pannel");
		closeSideSheet.click();
	}
	
	public String getQuantity()
	{
		test.info("returning Product quantity");
		Select select=new Select(quantity);
		return select.getFirstSelectedOption().getText();
	}
	
	public void setQuantity(String qty)
	{
		test.info("Set Product quantity to:"+qty);
		Select select=new Select(quantity);
		select.selectByVisibleText(qty);
	}

	public boolean verifyProductTitle(String expectedProductTitle) {
		String actualProductTitle = getProductTitle();
		test.info("verify Product Title");
		test.info("actual Product Title:"+actualProductTitle);
		test.info("expected Product Title:"+expectedProductTitle);
		boolean result = Utility.compareText(actualProductTitle, expectedProductTitle);
		if(result)
		{
			test.pass("Product Title matching");
		}
		else
		{
			test.fail("Product Title Not matching");
		}
		
		return result;
	}
	
	public boolean verifyCheckOutMsg()
	{
		int quantity = Utility.convertStringToNumber(getQuantity());
		String checkOutMsgExpected="Proceed to checkout ("+quantity+" items)";
		String checkOutMsgActual=getCheckOutMsg();
		test.info("verify CheckOut msg");
		test.info("actual CheckOut msg:"+checkOutMsgActual);
		test.info("expected CheckOut msge:"+checkOutMsgExpected);
		boolean result=Utility.compareText(checkOutMsgActual, checkOutMsgExpected);
		
		if(result)
		{
			test.pass("CheckOut msg matching");
		}
		else
		{
			test.fail("CheckOut msg Not matching");
		}
		
		return result;
	}
	
	public boolean verifyProductTotalPrice(double productPrice)
	{
		test.info("verify Product Total Price");
		
		test.info("One Product Price:"+productPrice);
		
		int quantity = Utility.convertStringToNumber(getQuantity());
		test.info("quantity:"+quantity);
		
		double productPriceTotal= Utility.formatPrice(getProductPrice());
		test.info("Total Product Price:"+productPriceTotal);
		
		boolean result= Utility.comparePrice(productPriceTotal, productPrice*quantity);
		
		if(result)
		{
			test.pass("Product Price matching");
		}
		else
		{
			test.fail("Product Price Not matching");
		}
		
		return result;
		
	}
}
