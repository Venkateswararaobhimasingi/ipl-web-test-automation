package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.HomePageObject;

public class TC001_FooterLinks extends BaseTest {

	@Test(groups = {"smoke"})
	public void verifyFooterLinks() {
		
		logger.info("===== Test Case: Verify Footer Links Started =====");
		
		hpo.handleCookies();
		logger.info("Handled cookies popup");
			
		hpo.scrollToBottom();
		logger.info("Scrolled to bottom of the page");

		String sectionNames[] = p.getProperty("sections").split(",");
		
		boolean status=true;

		for (String title : sectionNames) {
			
			logger.info("Verifying section: " + title);

			List<String> sectionLinks = hpo.getSectionLinks(title);

			if (sectionLinks.size() > 0) {
				
				logger.info(title + " links are present");
                logger.info("Link count: " + sectionLinks.size());

                for (String s : sectionLinks) {
                    logger.debug("Link: " + s); 
                }

			} else {	
				status=false;
				logger.error(title + " links are not present");
				break;
			}

		}
		
		if(status) {
			logger.info("Test Case Pass All footer sections have links");
			Assert.assertTrue(true);
		}
		else {
			
			logger.error("Test Case Fail Some footer sections are missing links");
			Assert.assertTrue(false);
		}
		
		logger.info("===== Test Case Finished =====");

	}

}
