package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePageObject;
import utilities.WaitUtilities;

public class PointsTablePageObject extends BasePageObject {

	public PointsTablePageObject(WebDriver driver) {
		super(driver);

	}
	
	By txt_teamRank1=By.xpath("(//tbody[@id='pointsdata']/tr)[1]//h2");

	public String getTeamRank1() {
		WebElement txt_teamRank1Element=WaitUtilities.waitForElementVisible(wait, txt_teamRank1);
		return txt_teamRank1Element.getText();
	}
	
	private String getTeamColumnXpath(String teamCode,int colIndex) {
	    String path= "//tbody[@id='pointsdata']//h2[contains(text(),'" + teamCode + "')]/ancestor::td/following-sibling::td["+colIndex+"]";
	    return path;
	}	

	public int getMatchPlayed(String teamCode) {
		By matchPlayedLocator=By.xpath(getTeamColumnXpath(teamCode, 1));
		WebElement matchPlayedElement=driver.findElement(matchPlayedLocator);
		
		return Integer.parseInt(matchPlayedElement.getText());
	}

	public int getMatchWin(String teamCode) {
		By matchWinLocator=By.xpath(getTeamColumnXpath(teamCode, 2));
		WebElement matchWinElement=driver.findElement(matchWinLocator);
		
		return Integer.parseInt(matchWinElement.getText());
	}

	public int getMatchLost(String teamCode) {
		By matchLostLocator=By.xpath(getTeamColumnXpath(teamCode, 3));
		WebElement matchLostElement=driver.findElement(matchLostLocator);
		
		
		return Integer.parseInt(matchLostElement.getText());
	}

	public int getMatchNoResult(String teamCode) {
		By matchNoResultLocator=By.xpath(getTeamColumnXpath(teamCode, 4));
		WebElement matchNoResultElement=driver.findElement(matchNoResultLocator);
		
		return Integer.parseInt(matchNoResultElement.getText());
	}

	public int getMatchPoints(String teamCode) {
		By matchPointsLocator=By.xpath(getTeamColumnXpath(teamCode, 8));
		WebElement matchPointsElement=driver.findElement(matchPointsLocator);
		
		return Integer.parseInt(matchPointsElement.getText());
	}
}
