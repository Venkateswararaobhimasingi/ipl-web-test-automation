package base;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	protected WebDriver driver;
	
	@BeforeClass
	@Parameters({"browser"})
	public void setUp(String browser) {
		
		switch (browser.toLowerCase()) {
		case "chrome":
			driver=new ChromeDriver();
			break;
			
		case "edge":
			driver=new EdgeDriver();
			break;
		case "firefox":
			driver=new FirefoxDriver();
			break;

		default:
			System.out.println("Browser name is not found");
			return ;
		
			
		}
		
		driver.manage().window().maximize();
		
		driver.get("https://www.iplt20.com/");
		
		
		
	}
	
	@AfterClass
	public void tearDown() {
		
		driver.quit();
		
	}

}
