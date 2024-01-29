package script.search;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import generic.BaseTest;
import page.SearchPage;

public class Search extends BaseTest {
	
	@Test(dataProvider="provideSearchKeywords")
	public void testSearchProduct(String val)
	{
		SearchPage search=new SearchPage(driver);
		search.clickSearchBox();
		boolean result=search.getSearchResults(val);
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(result, true);
		
		driver.navigate().back();
		
		soft.assertAll();
		
	}
	
	@DataProvider(name="provideSearchKeywords")
	public String[] provideSearchKeywords()
	{
		String[] searchKey= {"iphone",
				   "123",
				   "1 + mobile",
				   "---",
				   "uuououoi"};
		return searchKey;
	}
	
	

}
