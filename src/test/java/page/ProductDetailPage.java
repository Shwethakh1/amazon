package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class ProductDetailPage {
private ExtentTest test;
	
	@FindBy(id="twotabsearchtextbox")
	private WebElement searchBox;
	
	@FindBy(id="nav-search-submit-button")
	private WebElement searchButton;
	
	@FindBy(xpath = "//h2/a")
	private WebElement productTitle;
	
	
	@FindBy(xpath="//h2/../..//span[@class='a-price']")
	private WebElement productPrice;
	
	public ProductDetailPage(WebDriver driver,ExtentTest test)
	{
		PageFactory.initElements(driver,this);
		this.test=test;
	}
	
	public void searchTheProduct(String product)
	{
		test.info("Searching:"+product);
		searchBox.sendKeys(product);
		searchButton.click();
	}
	
	public String getProductTitle() {
		test.info("returning product Title");
		return productTitle.getText();
	}
	
	public String getProductPrice() {
		test.info("returning product price");
		return productPrice.getText();
	}
	
	public void clickProduct()
	{
		test.info("Clicking on product title");
		productTitle.click();
	}
	
}
