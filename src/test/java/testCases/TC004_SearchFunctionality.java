package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.HomePageObject;

public class TC004_SearchFunctionality extends BaseTest {

	@Test
	public void search() {

		logger.info("===Testcase SearchFunctionality is started====");
				
		HomePageObject hm = new HomePageObject(driver);
		logger.info("Homepage Object is created sucessfully");

		hm.clickSearchBar();
		logger.info("cicked on searchBar");

		hm.search("Auction 2026");
		logger.info("searched the 'Auction2026 in search bar' ");

		hm.searchTypeNews();
		logger.info("selected the search type to news");

		hm.click_search();
		logger.info("clicked on search button ");
		
		String res=hm.searchResult();
		logger.info("checked the search result from the search results");
		
		Assert.assertEquals("TATA IPL 2026 Player Auction List Announced", res);
		logger.info("testcase searchFuntionality is compleated");
		
	}
}
