package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePageObject;
import utilities.WaitUtilities;

public class FooterLinksPageObject extends BasePageObject {
	
	WebDriver driver;
	
	public FooterLinksPageObject(WebDriver driver) {
		super(driver);
		
	}
	
	//By bottom=By.xpath("//p[contains(normalize-space(),'Copyright © IPL 2026 All Rights Reserved.')]");
	
	public void scrollToBottom() {
		
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
	}
	
	public List<String> getSectionLinks(String sectionName) {
		By sectionTitleLocator=By.xpath("//h2[normalize-space()='"+sectionName+"']");
		
		WebElement sectionTitle=WaitUtilities.waitForElementPresence(wait, sectionTitleLocator);
		
		js.executeScript("arguments[0].scrollIntoView(true);", sectionTitle);
		
		sectionTitle=WaitUtilities.waitForElementVisible(wait, sectionTitleLocator);
		
		By sectionLinksLocator=By.xpath("//h2[normalize-space()='"+sectionName+"']/following-sibling::*//a[@href]");
		
		List<WebElement> sectionLinks=WaitUtilities.WaitForAllElementsVisible(wait, sectionLinksLocator);
		
		List<String> sectionLinksText=new ArrayList<>();
		
		for(WebElement e:sectionLinks) {
			String val=e.getText();
			if(!val.isEmpty()) {
				sectionLinksText.add(val);
			}
		}
		
		
		
		return sectionLinksText;
		
	}
	
	

}
