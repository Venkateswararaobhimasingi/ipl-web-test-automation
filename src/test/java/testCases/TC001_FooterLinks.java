package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.FooterLinksPageObject;

public class TC001_FooterLinks extends BaseTest {
	
	@Test
	public void  verifyFooterLinks() {
		FooterLinksPageObject flpo=new FooterLinksPageObject(driver);
		
		String sectionNames[]= {"TEAM","ABOUT","GUIDELINES","CONTACT"};
		
		for(String title: sectionNames) {
		
		List<String> sectionLinks=flpo.getSectionLinks(title);
		
		if(sectionLinks.size()>0) {
			System.out.println(title+" links are present");
			System.out.println("count is: "+sectionLinks.size());
			for(String s:sectionLinks) {
				System.out.println(s);
			}
			
		}
		else {
			System.out.println(title+" links are not present");
			
			Assert.assertTrue(false);
		}
		
		}
		
		Assert.assertTrue(true);
		
		
		
		
	}

}
