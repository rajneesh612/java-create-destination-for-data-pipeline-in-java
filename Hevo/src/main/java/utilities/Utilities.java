package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	
	public static WebDriver driver;
	
	public static WebElement wait_for(String xpath, WaitConditions conditionType, int wait_time) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(wait_time));
		
		if(conditionType == WaitConditions.PRESENCE) {
			return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		}
		if(conditionType == WaitConditions.VISIBLE) {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		}
		if(conditionType == WaitConditions.CLICKABLE) {
			return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		}
		
		return null;
	}
}
