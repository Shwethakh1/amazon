package script.userflow;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import generic.BaseTest;
import generic.Utility;
import page.HomePage;
import page.ProductDetailPage;
import page.ResultPage;

public class UserFlow extends BaseTest {
	@DataProvider(name="getData")
	public String[][] getData()
	{
		String[][] data= {{"keyboard","1"},{"mouse","3"}};
		return data;
	}
	
	@Test(dataProvider = "getData")
	public void testSearchProduct(String product,String qty) 
	{
			SoftAssert softAssert=new SoftAssert();
			
			//Search for given product
			ProductDetailPage productDetailPage=new ProductDetailPage(driver,test);
			productDetailPage.searchTheProduct(product);
			
			//get the product name and unit price
			String productTitle = productDetailPage.getProductTitle();
			double oneProductPrice = Utility.formatPrice(productDetailPage.getProductPrice());
			
			//Goto Detail product page
			productDetailPage.clickProduct();
			ResultPage resultPage=new ResultPage(driver,test);
			
			//Verify the Product name is same or not
			boolean result=resultPage.verifyProductTitle(productTitle);
			softAssert.assertTrue(result);
			
			//Set the product quantity & add to cart
			resultPage.setQuantity(qty);
			resultPage.clickaddToCartButton();
			
			//verify the checkout message
			result=resultPage.verifyCheckOutMsg();
			softAssert.assertTrue(result);
			
			//verify the Total price = unit price*quantity
			result=resultPage.verifyProductTotalPrice(oneProductPrice);
			softAssert.assertTrue(result);
			
			//close the side Pannel
			resultPage.closeSidePannel();
			
			//Report
			softAssert.assertAll();
			
	}
}
