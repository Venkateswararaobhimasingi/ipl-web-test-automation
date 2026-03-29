package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.HomePageObject;

public class TC004_SearchFunctionality extends BaseTest {

	@Test(groups = {"smoke", "regression"})
	public void search() {

		logger.info("===Testcase SearchFunctionality is started====");
				
		
		hpo.clickSearchBar();
		logger.info("cicked on searchBar");
		
		hpo.handleCookies();
		logger.info("Handled cookies popup");

		hpo.search("Auction 2026");
		logger.info("searched the 'Auction2026 in search bar' ");

		hpo.searchTypeNews();
		logger.info("selected the search type to news");

		hpo.click_search();
		logger.info("clicked on search button ");
		
		String res=hpo.searchResult();
		logger.info("checked the search result from the search results");
		
		Assert.assertEquals("TATA IPL 2026 Player Auction List Announced", res);
		logger.info("testcase searchFuntionality is compleated");
		
	}
}
