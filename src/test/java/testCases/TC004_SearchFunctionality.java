package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.HomePageObject;

public class TC004_SearchFunctionality extends BaseTest {

	@Test
	public void search() {

		HomePageObject hm = new HomePageObject(driver);
		
		hm.clickSearchBar();
		hm.search("Auction 2026");
		hm.searchTypeNews();
		hm.click_search();
		
		String res=hm.searchResult();
		
		Assert.assertEquals("TATA IPL 2026 Player Auction List Announced", res);
	}
}
