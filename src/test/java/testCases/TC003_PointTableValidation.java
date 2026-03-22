package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.PointsTablePageObject;

public class TC003_PointTableValidation extends base.BaseTest {

	@Test
	public void testTeamNameAtRank1() {
		
		hpo.navigateToPointsTable();

		PointsTablePageObject pto = new PointsTablePageObject(driver);

		System.out.println(pto.getTeamRank1());

		int played = pto.getMatchPlayed();
		int win = pto.getMatchWin();
		int lost = pto.getMatchLost();
		int tied = pto.getMatchTied();

		int points = pto.getMatchPoints();

		Assert.assertEquals(2 * win + tied == points, win + lost + tied == played);
	}

}
