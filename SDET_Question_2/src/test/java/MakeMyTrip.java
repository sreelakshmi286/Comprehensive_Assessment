

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MakeMyTrip {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		   WebDriver driver=new ChromeDriver();
		   driver.manage().window().maximize();
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(25));
		   
		   try {
	            driver.get("https://www.makemytrip.com/");

	            WebElement popUP = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-cy='closeModal']")));
	            popUP.click();
	            
	            WebElement flightsTab = driver.findElement(By.xpath("//li[@data-cy='menu_Flights']"));
	            flightsTab.click();

	            WebElement roundTripButton = driver.findElement(By.xpath("//li[@data-cy='roundTrip']"));
	            roundTripButton.click();

	            WebElement fromLocation = driver.findElement(By.id("fromCity"));
	            fromLocation.sendKeys("HYD");

	            WebElement autoSuggestOption = driver.findElement(By.xpath("//p[contains(text(),'Hyderabad, India')]"));
	            autoSuggestOption.click();

	            
	            WebElement toLocation = driver.findElement(By.id("toCity"));
	            toLocation.sendKeys("MAA");

	            
	            autoSuggestOption = driver.findElement(By.xpath("//p[contains(text(),'Chennai, India')]"));
	            autoSuggestOption.click();

	            
	            String departure_date_xpath="//div[@aria-label='Sun Nov 19 2023']";
	            WebElement departureDate = driver.findElement(By.xpath(departure_date_xpath));
	            departureDate.click();
	            
	            
	            String return_date_XPATH="//div[@aria-label='Fri Dec 01 2023']";
	            WebElement returnDate = driver.findElement(By.xpath(return_date_XPATH));
	            returnDate.click();

	            WebElement searchButton =wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Search']")));
	            searchButton.click();

	            WebElement okay_button=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='OKAY, GOT IT!']")));
	            okay_button.click();
	            
	            WebElement searchResult = driver.findElement(By.xpath("//span[text()='SEARCH']"));
	            String title=driver.getTitle();
	            if(title.equalsIgnoreCase("MakeMyTrip")) {
	            if (searchResult.isDisplayed()) {
	               
	            	System.out.println("Search page displayed as expected.");
	            } else {
	                System.out.println("Search page not displayed as expected.");
	
	            }
	            }

	        } catch (Exception e) {
	        	System.out.println("Exception caught");
	        } finally {
	     
	            driver.quit();
	        }

	}

}
