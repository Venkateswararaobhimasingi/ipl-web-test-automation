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
		// TODO Auto-generated constructor stub
	}

	// By bottom=By.xpath("//p[contains(normalize-space(),'Copyright © IPL 2026 All
	// Rights Reserved.')]");

	public void scrollToBottom() {

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}

	public List<String> getSectionLinks(String sectionName) {
		By sectionTitleLocator = By.xpath("//h2[normalize-space()='" + sectionName + "']");

		WebElement sectionTitle = WaitUtilities.waitForElementPresence(wait, sectionTitleLocator);

		js.executeScript("arguments[0].scrollIntoView(true);", sectionTitle);

		sectionTitle = WaitUtilities.waitForElementVisible(wait, sectionTitleLocator);

		By sectionLinksLocator = By
				.xpath("//h2[normalize-space()='" + sectionName + "']/following-sibling::*//a[@href]");

		List<WebElement> sectionLinks = WaitUtilities.WaitForAllElementsVisible(wait, sectionLinksLocator);

		List<String> sectionLinksText = new ArrayList<>();

		for (WebElement e : sectionLinks) {
			String val = e.getText();
			if (!val.isEmpty()) {
				sectionLinksText.add(val);
			}
		}

		return sectionLinksText;

	}

	@FindBy(xpath = "//ul[@class='site-menu main-menu js-clone-nav d-none d-lg-block textCenter']/li/a[@data-element_text='POINTS TABLE']")
	WebElement lnk_pointTable;

	public void clickPointsTable() {
		lnk_pointTable.click();
	}

}
