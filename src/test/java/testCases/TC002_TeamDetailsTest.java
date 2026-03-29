package testCases;



import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.TeamDetailsPageObject;
import utilities.DataProviderUtilities;

public class TC002_TeamDetailsTest extends BaseTest{
	
	
	TeamDetailsPageObject tpo;
	
	
	@Test( groups = {"regression"},dataProvider = "iplTeamData",dataProviderClass=DataProviderUtilities.class)
	public void verifyTeamDetails(String teamName,String teamCode,String expectedLogo,String expectedYears) {
		
		    logger.info("===== Test Case: Verify Team Details Started =====");
		    logger.info("Executing for Team: " + teamName );

		  
		    
			hpo.navigateToTeams();
			logger.info("Navigated to Teams page");
			
			 hpo.handleCookies();
			 logger.info("Handled cookies popup");
			
			tpo= new TeamDetailsPageObject(driver);
			
			logger.info("Checking if team logo is displayed for: " + teamName);
			boolean isTeamLogoDisplayed= tpo.teamLogoIsDisplayed(teamCode);
			
			
			logger.debug("Expected Logo: " + expectedLogo + " Actual: " + isTeamLogoDisplayed);
			
			Assert.assertEquals(Boolean.valueOf(expectedLogo), isTeamLogoDisplayed,"Logo of the is not displayed"+teamName);
			logger.info("Logo validation passed for: " + teamName);
			
			logger.info("Hovering on team logo: " + teamName);
			tpo.hoverOnTeamLogo(teamCode);
			
			
			String actualWinningYears=tpo.getWinningYears(teamCode);
			logger.debug("Actual Winning Years: " + actualWinningYears);
	        logger.debug("Expected Winning Years: " + expectedYears);
	        
			if(actualWinningYears.equals("NA")) {
				
				logger.info("No titles found (NA) for team: " + teamName);
				Assert.assertEquals(actualWinningYears, expectedYears);
				
				
			}
			else {
			
				String[] winningYearsList=actualWinningYears.split("\\s*\\|\\s*");
				
				String[] expectedYearsList=expectedYears.replaceAll(" ","").split(",");
				
				logger.debug("Actual Years List: " + Arrays.toString(winningYearsList));
	            logger.debug("Expected Years List: " + Arrays.toString(expectedYearsList));
				
				Assert.assertEquals(winningYearsList, expectedYearsList,"Winning years mismatch for team: " + teamName);
				logger.info("Winning years validation passed for: " + teamName);
			
			}
			
			logger.info("===== Test Case Completed for Team: " + teamName + " =====");
	
		
	}

}
