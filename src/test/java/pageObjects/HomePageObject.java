package pageObjects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import base.BasePageObject;
import utilities.WaitUtilities;

public class HomePageObject extends BasePageObject {

	public HomePageObject(WebDriver driver) {
		super(driver);
	}

	By navBarTeamsLink = By.xpath("//div[@class='site-navbar-wrap']//a[@data-element_text='TEAMS']");

	By navBarPointsTableLink = By.xpath("//div[@class='site-navbar-wrap']//a[@data-element_text='POINTS TABLE']");

	By navBarMatchesLink = By.xpath("//div[@class='site-navbar-wrap']//a[@data-element_text='MATCHES']");

	By matchesNavBarPointsTableLink = By
			.xpath("//div[contains(@class,'matches-tabs')]//a[contains(text(),'POINTS TABLE')]");

	By cookieBtn = By.xpath("//div[@class='cookie']//button");

	By btn_searchBar = By.xpath("//button[@class='search-icon-header-menu bg-transparent border-0']");

	public void clickSearchBar() {

		WebElement btn_searchBarElement = WaitUtilities.waitForElementToBeClickable(wait, btn_searchBar);
		btn_searchBarElement.click();
	}

	By select_SearchType = By.xpath("//select[@id='search_type']");

	public void searchTypeNews() {

		WebElement select_SearchTypeElement = WaitUtilities.waitForElementVisible(wait, select_SearchType);

		Select select = new Select(select_SearchTypeElement);
		select.selectByVisibleText("News");
	}

	By txt_search = By.xpath("//input[@id='searchInputForHeader']");

	public void search(String text) {

		WebElement txt_searchElement = WaitUtilities.waitForElementVisible(wait, txt_search);
		txt_searchElement.sendKeys(text);
	}

	By btn_search = By.xpath("//b[@id='searchText']");

	public void click_search() {
		WebElement btn_searchElement = WaitUtilities.waitForElementToBeClickable(wait, btn_search);
		btn_searchElement.click();
	}

	By txt_searchResults = By.xpath("//li[@class='textTwoLine']");

	public List<String> searchResults() {

	    By resultsLocator = By.xpath("//li[@class='textTwoLine']");
	    List<WebElement> elements = WaitUtilities.waitForAllElementsVisible(wait, resultsLocator);

	    List<String> results = new ArrayList<>();

	    for (WebElement ele : elements) {
	        String text = ele.getText();
	        if (!text.isEmpty()) {
	            results.add(text);
	        }
	    }

	    return results;
	}

	public void navigateToPointsTable() {

		WebElement navBarPointsTableLinkElement = WaitUtilities.waitForElementToBeClickable(wait,
				navBarPointsTableLink);

		try {
			navBarPointsTableLinkElement.click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void navigateToTeams() {

		List<WebElement> elements = WaitUtilities.waitForAllElementsVisible(wait, navBarTeamsLink);

		for (WebElement ele : elements) {
			if (ele.isDisplayed() && ele.isEnabled()) {
				try {
					ele.click();
					return;
				} catch (Exception e) {
					// fallback to JS click
					js.executeScript("arguments[0].click();", ele);
					return;
				}
			}
		}

		throw new RuntimeException("No clickable Teams link found");
	}

	public void scrollToBottom() {

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}

	public List<String> getSectionLinks(String sectionName) {
		By sectionTitleLocator = By.xpath("//h2[normalize-space()='" + sectionName + "']");

		WebElement sectionTitle = WaitUtilities.waitForElementPresence(wait, sectionTitleLocator);

		// js.executeScript("arguments[0].scrollIntoView(true);", sectionTitle);

		sectionTitle = WaitUtilities.waitForElementVisible(wait, sectionTitleLocator);

		By sectionLinksLocator = By
				.xpath("//h2[normalize-space()='" + sectionName + "']/following-sibling::*//a[@href]");

		List<WebElement> sectionLinks = WaitUtilities.waitForAllElementsVisible(wait, sectionLinksLocator);

		List<String> sectionLinksText = new ArrayList<>();

		for (WebElement e : sectionLinks) {
			String val = e.getText();
			if (!val.isEmpty()) {
				sectionLinksText.add(val);
			}
		}

		return sectionLinksText;

	}

	By btn_loadMore = By.xpath("//button[contains(text(),'Load More')]");

	public void clickLoadMoreBtn() {
		WebElement btn_loadMoreElement = WaitUtilities.waitForElementToBeClickable(wait, btn_loadMore);
		js.executeScript("arguments[0].click();", btn_loadMoreElement);
	}
	
	public void handleCookiesBtn() {

	    By cookieAcceptBtn = By.xpath("//button[contains(text(),'Accept')]");

	    try {
	        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(cookieAcceptBtn));

	        wait.until(ExpectedConditions.visibilityOf(btn));

	        try {
	            btn.click();
	        } catch (Exception e) {
	            js.executeScript("arguments[0].click();", btn);
	        }

	        wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieAcceptBtn));

	    } catch (Exception e) {
	        // popup not present → ignore
	    }
	}
	
	public void handleCookies() {

		List<WebElement> btns = driver.findElements(cookieBtn);

		if (!btns.isEmpty() && btns.get(0).isDisplayed()) {
			btns.get(0).click();

		}
	}

}
