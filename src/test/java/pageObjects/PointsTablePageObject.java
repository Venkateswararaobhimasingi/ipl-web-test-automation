package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePageObject;

public class PointsTablePageObject extends BasePageObject {

	public PointsTablePageObject(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "(//tbody[@id='pointsdata']/tr)[1]//h2")
	WebElement txt_teamRank1;

	public String getTeamRank1() {
		return txt_teamRank1.getText();
	}

	@FindBy(xpath = "((//tbody[@id='pointsdata']/tr)[1]//td)[2]")
	WebElement txt_matchPlayed;

	public int getMatchPlayed() {
		return Integer.parseInt(txt_matchPlayed.getText());
	}

	@FindBy(xpath = "((//tbody[@id='pointsdata']/tr)[1]//td)[3]")
	WebElement txt_win;

	public int getMatchWin() {
		return Integer.parseInt(txt_win.getText());
	}

	@FindBy(xpath = "((//tbody[@id='pointsdata']/tr)[1]//td)[4]")
	WebElement txt_lost;

	public int getMatchLost() {
		return Integer.parseInt(txt_lost.getText());
	}

	@FindBy(xpath = "((//tbody[@id='pointsdata']/tr)[1]//td)[5]")
	WebElement txt_tied;

	public int getMatchTied() {
		return Integer.parseInt(txt_tied.getText());
	}

	@FindBy(xpath = "((//tbody[@id='pointsdata']/tr)[1]//td)[9]")
	WebElement txt_points;

	public int getMatchPoints() {
		return Integer.parseInt(txt_points.getText());
	}
}
