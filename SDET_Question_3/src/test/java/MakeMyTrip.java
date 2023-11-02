import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MakeMyTrip {

	WebDriver driver;
	WebDriverWait wait;
	 
	@BeforeMethod
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		   driver=new ChromeDriver();
		   driver.manage().window().maximize();
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   wait= new WebDriverWait(driver,Duration.ofSeconds(25));
		   
	}
	
	@Test
	public void SearchFlight() {
		try {
			
		driver.get("https://www.makemytrip.com/");

        WebElement popUP = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-cy='closeModal']")));
        popUP.click();
        
        WebElement flightsTab = driver.findElement(By.xpath("//li[@data-cy='menu_Flights']"));
        flightsTab.click();

        // Select ROUND TRIP
        WebElement roundTripButton = driver.findElement(By.xpath("//li[@data-cy='roundTrip']"));
        roundTripButton.click();

        // Enter From Location as HYD
        WebElement fromLocation = driver.findElement(By.id("fromCity"));
        fromLocation.sendKeys("HYD");

        // Select the first option from the auto-suggest dropdown
        WebElement autoSuggestOption = driver.findElement(By.xpath("//p[contains(text(),'Hyderabad, India')]"));
        autoSuggestOption.click();

        // Enter To Location as MAA
        WebElement toLocation = driver.findElement(By.id("toCity"));
        toLocation.sendKeys("MAA");

        // Select the first option from the auto-suggest dropdown
        autoSuggestOption = driver.findElement(By.xpath("//p[contains(text(),'Chennai, India')]"));
        autoSuggestOption.click();

        // Select DEPARTURE Date (Assuming the input field has an ID)
        String departure_date_xpath="//div[@aria-label='Sun Nov 19 2023']";
        WebElement departureDate = driver.findElement(By.xpath(departure_date_xpath));
        departureDate.click();
        
        // Select RETURN Date (Assuming the input field has an ID)
        String return_date_XPATH="//div[@aria-label='Fri Dec 01 2023']";
        WebElement returnDate = driver.findElement(By.xpath(return_date_XPATH));
        returnDate.click();

        // Click on Search Button
        WebElement searchButton =wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Search']")));
        searchButton.click();

        WebElement okay_button=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='OKAY, GOT IT!']")));
        okay_button.click();
        // Verify the Search page is displayed as expected (You can add more verification as needed)
        WebElement searchResult = driver.findElement(By.xpath("//span[text()='SEARCH']"));
        String title=driver.getTitle();
        if(title.equalsIgnoreCase("MakeMyTrip")) {
        if (searchResult.isDisplayed()) {
            System.out.println("Search page displayed as expected.");
            assertTrue(true);
        } else {
            System.out.println("Search page not displayed as expected.");
            assertTrue(false);
        }
        }

    }catch (Exception e) {
        System.out.println("Exception caught");
    }

	}
	
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
}
