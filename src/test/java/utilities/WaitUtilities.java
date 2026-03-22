package utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtilities {
	
	public static WebElement waitForElementPresence(WebDriverWait wait, By locator) {
	    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public static WebElement waitForElementVisible(WebDriverWait wait,By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static List<WebElement> waitForAllElementsVisible(WebDriverWait wait,By locator) {
		
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public static void waitForElementInViewport( WebDriverWait wait, WebElement element) {

	    wait.until(d -> (Boolean) ((JavascriptExecutor) d).executeScript(
	        "var rect = arguments[0].getBoundingClientRect();" +
	        "return rect.top >= 0 && rect.bottom <= window.innerHeight;",
	        element
	    ));
	}
	
	

}
