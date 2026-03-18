package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import base.BasePageObject;
import utilities.WaitUtilities;

public class TeamDetailsPageObject extends BasePageObject {
	

	
	public TeamDetailsPageObject(WebDriver driver) {
		super(driver);
	}
	
	public boolean teamLogoIsDisplayed(String teamCode) {
		By teamlogoLocator= By.xpath("//li[@class='TL_"+teamCode+"']//div[@class='vn-team-logo']/img");
		
		WebElement teamLogo= WaitUtilities.waitForElementVisible(wait, teamlogoLocator);
		
		return teamLogo.isDisplayed();
		
	}
	
	
	public void hoverOnTeamLogo(String teamCode ,String teamName) {
		By teamTitleLocator= By.xpath("//li[@class='TL_"+teamCode+"']//h3[contains(text(),'"+teamName+"')]");
		
		WebElement teamTitle=WaitUtilities.waitForElementVisible(wait, teamTitleLocator);
		
		actions.moveToElement(teamTitle).build().perform();
		
	}
	
	public String getWinningYears(String teamCode) {
		
		By winningYearsLocator= By.xpath("//li[@class='TL_"+teamCode+"']//div[@class='trophy-text-align']");
		
		List<WebElement> winningYearsList= driver.findElements(winningYearsLocator);
		
		String winningYears="NA";
		
		if(!winningYearsList.isEmpty()) {
			
			winningYears=winningYearsList.get(0).getText().toString();
		}
		return winningYears;
		
	}
	
	
	
	

}
