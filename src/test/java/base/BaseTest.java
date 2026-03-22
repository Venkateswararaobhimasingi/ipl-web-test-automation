package base;


import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import pageObjects.HomePageObject;

public class BaseTest {

	protected WebDriver driver;
	protected Properties p;
	protected HomePageObject hpo;
	protected Logger logger;

	@BeforeClass
	@Parameters({ "browser" })
	public void setUp(String browser) {
		
		try {
			
		logger=LogManager.getLogger(this.getClass());	
		
		logger.info("===== Test Execution Started =====");
		
		FileReader file=new FileReader("./src//test//resources//config.properties");
		
		p=new Properties();
		p.load(file);
		
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;
			
		case "firefox":
			driver = new FirefoxDriver();
			break;

		default:
			logger.error("Invalid browser name provided: " + browser);
			throw new RuntimeException("Browser not supported");

		}
		
		logger.info("Launching "+ browser.toUpperCase() +" Browser");
		driver.manage().window().maximize();

		driver.get(p.getProperty("url"));
		logger.info("Navigated to URL: " + p.getProperty("url"));
		
		hpo=new HomePageObject(driver);
		hpo.handleCookies();
		logger.info("Handled cookies popup");
		
		}
		catch (IOException e) {
			logger.error("Error while loading config file", e);
		}
		

	}

	@AfterClass
	public void tearDown() {

		if (driver!= null) {
            driver.quit();
            logger.info("Browser closed");
        }

        logger.info("===== Test Execution Finished =====");

	}

}
