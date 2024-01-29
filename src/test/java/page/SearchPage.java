package page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class SearchPage {

	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchBox;

	@FindBy(xpath = "//div[@class='left-pane-results-container']/div")
	private List<WebElement> searchResultsContainer;

	@FindBy(id = "nav-search-submit-button")
	private WebElement searchBtn;

	@FindBy(xpath = "//span[contains(text(),'results for')]")
	private WebElement searchResult;

	@FindBy(xpath = "//span[contains(text(),'No result')]")
	private WebElement noSearchResult;

	public SearchPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickSearchBox() {
		searchBox.click();
	}

	public boolean getSearchResults(String searchKeyword) {
		boolean isFound = false;
		searchBox.sendKeys(searchKeyword);
		//Check for search container not empty
		if (!searchResultsContainer.isEmpty()) 
		{
			for (WebElement s : searchResultsContainer)
			{
				String result = s.getText();
				//exact macth result
				if (result.equalsIgnoreCase(searchKeyword)) {
					s.click();
					isFound = searchResult.isDisplayed();
					break;
				}
			}
			for (WebElement s : searchResultsContainer)
			{
				String result = s.getText();
				//partail match result
				if (result.contains(searchKeyword)) 
				{
					s.click();
					isFound = searchResult.isDisplayed();
					break;
				}
			}
		}
		else {
			//search container is empty
			searchBtn.click();
			isFound = searchResult.isDisplayed();
		}
		return isFound;

	}

}
