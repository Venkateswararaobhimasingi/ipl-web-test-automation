package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class TC004_SearchFunctionality extends BaseTest {

	@Test(groups = { "smoke", "regression" })
	public void search() {

		logger.info("=== Testcase SearchFunctionality started ===");

		hpo.clickSearchBar();
		logger.info("Clicked on search bar");

		hpo.handleCookies();

		hpo.search("Auction 2026");
		logger.info("Entered search text");

		hpo.searchTypeNews();
		logger.info("Selected News filter");

		hpo.click_search();
		logger.info("Clicked search button");
		
		hpo.handleCookiesBtn();
		logger.info("cookiee handled");
		
		hpo.clickLoadMoreBtn();
		logger.info("clicked on loadmore button");

		List<String> results = hpo.searchResults();
		System.out.println(results.toString());
		logger.info("Search results: " + results);

		Assert.assertTrue(results.contains("TATA IPL 2026 Player Auction List Announced"),"Expected result not found!");

		logger.info("=== Testcase completed successfully ===");
	}

}
