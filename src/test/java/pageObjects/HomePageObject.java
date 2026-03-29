package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import base.BasePageObject;
import utilities.WaitUtilities;

public class HomePageObject extends BasePageObject {

	public HomePageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='site-navbar-wrap']//a[@data-element_text='TEAMS']")
	WebElement navBarTeamsLink;

	@FindBy(xpath = "//div[@class='site-navbar-wrap']//a[@data-element_text='POINTS TABLE']")
	List<WebElement> navBarPointsTableLink;

	@FindBy(xpath = "//div[@class='site-navbar-wrap']//a[@data-element_text='MATCHES']")
	WebElement navBarMatchesLink;

	@FindBy(xpath = "//div[contains(@class,'matches-tabs')]//a[contains(text(),'POINTS TABLE')]")
	WebElement matchesNavBarPointsTableLink;

	By cookieBtn = By.xpath("//div[@class='cookie']//button");

	@FindBy(xpath = "//button[@class='search-icon-header-menu bg-transparent border-0']")
	WebElement btn_searchBar;

	public void clickSearchBar() {
		btn_searchBar.click();
	}

	@FindBy(xpath = "//select[@id='search_type']")
	WebElement select_SearchType;

	public void searchTypeNews() {
		Select select = new Select(select_SearchType);
		select.selectByVisibleText("News");
	}

	@FindBy(xpath = "//input[@id='searchInputForHeader']")
	WebElement txt_search;

	public void search(String text) {
		txt_search.sendKeys(text);
	}

	@FindBy(xpath = "//b[@id='searchText']")
	WebElement btn_search;

	public void click_search() {
		btn_search.click();
	}

	@FindBy(xpath = "//li[@class='textTwoLine']")
	WebElement txt_searchResults;

	public String searchResult() {
		return txt_searchResults.getText();
	}

	public void navigateToPointsTable() {

		boolean isPointsTablePresent = navBarPointsTableLink.isEmpty();

		if (!isPointsTablePresent) {

			navBarPointsTableLink.get(0).click();
		} else {

			navBarMatchesLink.click();

			matchesNavBarPointsTableLink.click();

		}

	}

	public void navigateToTeams() {

		navBarTeamsLink.click();
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

	public void handleCookies() {

		List<WebElement> btns = driver.findElements(cookieBtn);

		if (!btns.isEmpty() && btns.get(0).isDisplayed()) {
			btns.get(0).click();

		} 
	}

}
