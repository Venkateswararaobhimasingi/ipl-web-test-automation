package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.PointsTablePageObject;

public class TC003_PointTableValidation extends base.BaseTest {

	@Test(groups = {"regression"})
	public void testTeamNameAtRank1() {
		
		logger.info( "========Test Poinsts Table Validation Started=========");
		
		
		hpo.navigateToPointsTable();
		
		hpo.handleCookies();
		logger.info("Handled cookies popup");
		
		logger.info("navigated to poinsts table");
		PointsTablePageObject pto = new PointsTablePageObject(driver);
		
		logger.info("created object of Points table PageObject and redirected");
		//System.out.println(pto.getTeamRank1());
		String teamCode=pto.getTeamRank1();
		
		logger.info("got the team ar rank1"+teamCode);
		int played = pto.getMatchPlayed(teamCode);
		logger.info("got the matched played by team "+played);
		
		int win = pto.getMatchWin(teamCode);
		logger.info("got the matched won by team "+win);
		
		int lost = pto.getMatchLost(teamCode);
		logger.info("got the matched lost by team "+lost);

		int noResult = pto.getMatchNoResult(teamCode);
		logger.info("got the matched tied by team "+noResult);

		int points = pto.getMatchPoints(teamCode);
		logger.info("got the matched points by team "+points);
		logger.info("Calculated the actual points to a team and the present points to the team ");

		Assert.assertEquals((2 * win) + noResult == points, win + lost + noResult == played);
		logger.info("==Points table validation is compleated===");
	}

}
