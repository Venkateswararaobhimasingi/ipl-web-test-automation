package base;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {

	protected WebDriver driver;
	protected Properties p;

	@BeforeClass
	@Parameters({ "browser" })
	public void setUp(String browser) {
		
		try {
		
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
			System.out.println("Browser name is not found");
			return;

		}
		
		

		driver.manage().window().maximize();

		driver.get(p.getProperty("url"));
		
		}
		catch (IOException e) {
			 e.printStackTrace();
		}
		

	}

	@AfterClass
	public void tearDown() {

		driver.quit();

	}

}
