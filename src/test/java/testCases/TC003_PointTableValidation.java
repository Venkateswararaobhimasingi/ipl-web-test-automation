package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.PointsTablePageObject;

public class TC003_PointTableValidation extends base.BaseTest {

	@Test
	public void testTeamNameAtRank1() {
		
		logger.info( "========Test Poinsts Table Validation Started=========");
		hpo.navigateToPointsTable();
		
		logger.info("navigated to poinsts table");
		PointsTablePageObject pto = new PointsTablePageObject(driver);
		
		logger.info("created object of Points table PageObject and redirected");
		System.out.println(pto.getTeamRank1());
		
		logger.info("got the team ar rank1");
		int played = pto.getMatchPlayed();
		logger.info("got the matched played by team");
		
		int win = pto.getMatchWin();
		logger.info("got the matched won by team");
		
		int lost = pto.getMatchLost();
		logger.info("got the matched lost by team");

		int tied = pto.getMatchTied();
		logger.info("got the matched tied by team");

		int points = pto.getMatchPoints();
		logger.info("Calculated the actual points to a team and the present points to the team");

		Assert.assertEquals(2 * win + tied == points, win + lost + tied == played);
		logger.info("==Points table validation is compleated===");
	}

}
