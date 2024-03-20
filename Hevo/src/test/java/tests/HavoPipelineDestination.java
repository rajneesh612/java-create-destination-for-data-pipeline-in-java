package tests;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.DriverFactory;
import utilities.Utilities;
import utilities.WaitConditions;

public class HavoPipelineDestination {
	
	WebDriver driver;
	DriverFactory driverFactory;
	WaitConditions wait_type;
	
	@BeforeTest
	public void init() throws IOException {
		
		driverFactory = new DriverFactory();
		driver = driverFactory.getDriver();
		Utilities.driver = driver;
	}
	
	
	@Test
	public void createDestination() throws TimeoutException {
		
		driver.get("https://hevodata.com/login/credentials/");
		
		WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='name@company.com']"));
		emailField.sendKeys("fobin43393@cmheia.com");
		WebElement continueBtn = driver.findElement(By.xpath("//button//span[normalize-space()='Continue']"));
		continueBtn.click();
		WebElement pswdField = driver.findElement(By.xpath("//input[@id='password']"));
		pswdField.sendKeys("Test@123");
		WebElement logInBtn = driver.findElement(By.xpath("//button[text()='Log In']"));
		logInBtn.click();
		
		// To close the tour or tutorial box if visible.
		Utilities.wait_for("//div[contains(@class, 'tourBox')]//button[@data-id='product-tour-close-icon-button']", 
				wait_type.CLICKABLE, 10).click();
		
		
		// Clicking on the destination icon in the side panel
		WebElement destinationIcon = driver.findElement(By.id("product-tour-destination"));
		destinationIcon.click();
		
		// Clicking on the create button for creating destination
		WebElement createBtn = driver.findElement(By.xpath("//button[normalize-space()='Create']"));
		createBtn.click();
		
		
		// Selecting the Postgres tab
		WebElement postgresTab= driver.findElement(By.xpath("//h5[normalize-space()='PostgreSQL']"));
		postgresTab.click();
		
		
		//Searching Destination name element.
		WebElement destinamationNameTextField = driver.findElement(By.id("destinationName"));
		destinamationNameTextField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		destinamationNameTextField.sendKeys(Keys.BACK_SPACE);
		destinamationNameTextField.sendKeys("destination_test");
		
		
		// Searching host element
		WebElement hostTextField= driver.findElement(By.id("host"));
		hostTextField.clear();
		hostTextField.sendKeys("0.tcp.in.ngrok.io");
		
		// Searching the port field
		WebElement portTextField = driver.findElement(By.id("port"));
		portTextField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		portTextField.sendKeys(Keys.BACK_SPACE);
		portTextField.sendKeys("16211");
		
		// Searching data base user field
		WebElement databaseuserTextField = driver.findElement(By.id("user"));
		databaseuserTextField.clear();
		databaseuserTextField.sendKeys("postgres");
		
		// Searching password field
		WebElement passwordTextField = driver.findElement(By.id("password"));
		passwordTextField.clear();
		passwordTextField.sendKeys("admin");
		
		
		// Searching data base name field
		WebElement databasenameTextField = driver.findElement(By.id("databaseName"));
		databasenameTextField.clear();
		databasenameTextField.sendKeys("postgres");
		
					
		// Searching data base name field
		WebElement schemaNameTextField = driver.findElement(By.id("schemaName"));
		schemaNameTextField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		schemaNameTextField.sendKeys(Keys.BACK_SPACE);
		schemaNameTextField.sendKeys("public");
				
		// Searching for test connection button
		WebElement testConnectionBtn = driver.findElement(By.xpath("//button[normalize-space()='Test Connection']"));
		testConnectionBtn.click();
		
		// Wait for connection successsful message to appear
		Utilities.wait_for("//div[text()= 'Connection successful']", wait_type.VISIBLE, 10);
						
		// Searching for save and continue button
		WebElement saveBtn = driver.findElement(By.xpath("//button[@data-id='destination-config-save-button']"));
		saveBtn.click();
		
		
		
		
		
		
}
	
}
