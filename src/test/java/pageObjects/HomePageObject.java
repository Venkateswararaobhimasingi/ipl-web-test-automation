package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePageObject;
import utilities.WaitUtilities;

public class HomePageObject extends BasePageObject {

	public HomePageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='site-navbar-wrap']//a[@data-element_text='TEAMS']") WebElement navBarTeamsLink;
	
	@FindBy(xpath = "//div[@class='site-navbar-wrap']//a[@data-element_text='POINTS TABLE']") List<WebElement> navBarPointsTableLink;
	
	
	@FindBy(xpath = "//div[@class='site-navbar-wrap']//a[@data-element_text='MATCHES']") WebElement navBarMatchesLink;
	
	@FindBy(xpath = "//div[contains(@class,'matches-tabs')]//a[contains(.,'POINTS TABLE')]") WebElement matchesNavBarPointsTableLink;
	
	

	public void navigateToPointsTable() {
		
		boolean isPointsTablePresent=navBarPointsTableLink.isEmpty();
		
		if(!isPointsTablePresent) {
			
			navBarPointsTableLink.get(0).click();
		}
		else {
			
			navBarMatchesLink.click();
			
			matchesNavBarPointsTableLink.click();
			
		}
		
	}

	public void scrollToBottom() {

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}

	public List<String> getSectionLinks(String sectionName) {
		By sectionTitleLocator = By.xpath("//h2[normalize-space()='" + sectionName + "']");

		WebElement sectionTitle = WaitUtilities.waitForElementPresence(wait, sectionTitleLocator);

		//js.executeScript("arguments[0].scrollIntoView(true);", sectionTitle);

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

	

}
