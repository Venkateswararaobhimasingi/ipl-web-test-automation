package testCases;



import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.TeamDetailsPageObject;
import utilities.DataProviderUtilities;

public class TC002_TeamDetailsTest extends BaseTest{
	
	HomePageObject hpo;
	TeamDetailsPageObject tpo;
	
	
	@Test(dataProvider = "iplTeamData",dataProviderClass=DataProviderUtilities.class)
	public void verifyTeamDetails(String teamName,String teamCode,String expectedLogo,String expectedYears) {
		
		
		    
			hpo= new HomePageObject(driver);
			
			hpo.navigateToTeams();
			
			tpo= new TeamDetailsPageObject(driver);
			System.out.println("team "+teamName);
			boolean isTeamLogoDisplayed= tpo.teamLogoIsDisplayed(teamCode);
			
			if(Boolean.valueOf(expectedLogo)==isTeamLogoDisplayed) {}
			System.out.println("dis"+teamName);
			
			Assert.assertEquals(Boolean.valueOf(expectedLogo), isTeamLogoDisplayed,"Logo of the is not displayed"+teamName);
			
			tpo.hoverOnTeamLogo(teamCode, teamName);
			
			
			String actualWinningYears=tpo.getWinningYears(teamCode);
			if(actualWinningYears.equals("NA")) {
				
				System.out.println("NA "+teamCode+" "+actualWinningYears+" "+expectedYears);
				Assert.assertEquals(actualWinningYears, expectedYears);
				
				
			}
			else {
			
				String[] winningYearsList=actualWinningYears.split("\\s*\\|\\s*");
				
				String[] expectedYearsList=expectedYears.replaceAll(" ","").split(",");
				
				System.out.println("a1 "+Arrays.toString(winningYearsList));
				
				System.out.println("a2 "+Arrays.toString(expectedYearsList));
				
				Assert.assertEquals(winningYearsList, expectedYearsList,"Winning years mismatch for team: " + teamName);
				System.out.println("done "+teamCode);
			
			}
			
	
		
	}

}
