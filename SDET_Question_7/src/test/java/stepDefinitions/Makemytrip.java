package stepDefinitions;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Makemytrip{

	WebDriver driver;
	WebDriverWait wait;
	
@Given("ChromeDriver must be openend")
public void chrome_driver_must_be_openend() {
	WebDriverManager.chromedriver().setup();
	   driver=new ChromeDriver();
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	   wait= new WebDriverWait(driver,Duration.ofSeconds(25));
	
}

@Then("user have to enter in to the MakeMyTrip page")
public void user_have_to_enter_in_to_the_make_my_trip_page() {
	
	driver.get("https://www.makemytrip.com/");

    WebElement popUP = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-cy='closeModal']")));
    popUP.click();
}

@Then("user needs to click on flights")
public void user_needs_to_click_on_flights() {

    WebElement flightsTab = driver.findElement(By.xpath("//li[@data-cy='menu_Flights']"));
    flightsTab.click();
    
    WebElement roundTripButton = driver.findElement(By.xpath("//li[@data-cy='roundTrip']"));
    roundTripButton.click();
}

@Then("user need to select from and to details")
public void user_need_to_select_from_and_to_details() {
	
	WebElement fromLocation = driver.findElement(By.id("fromCity"));
    fromLocation.sendKeys("HYD");

    WebElement autoSuggestOption = driver.findElement(By.xpath("//p[contains(text(),'Hyderabad, India')]"));
    autoSuggestOption.click();

    
    WebElement toLocation = driver.findElement(By.id("toCity"));
    toLocation.sendKeys("MAA");

    
    autoSuggestOption = driver.findElement(By.xpath("//p[contains(text(),'Chennai, India')]"));
    autoSuggestOption.click();

}

@Then("user need to select the departure and return date")
public void user_need_to_select_the_departure_and_return_date() {
	
	String departure_date_xpath="//div[@aria-label='Sun Nov 19 2023']";
    WebElement departureDate = driver.findElement(By.xpath(departure_date_xpath));
    departureDate.click();
    
    
    String return_date_XPATH="//div[@aria-label='Fri Dec 01 2023']";
    WebElement returnDate = driver.findElement(By.xpath(return_date_XPATH));
    returnDate.click();

}

@Then("user need to click search button")
public void user_need_to_click_search_button() {
	WebElement searchButton =wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Search']")));
    searchButton.click();

    WebElement okay_button=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='OKAY, GOT IT!']")));
    okay_button.click();
    
}

@Then("user should be able to see the search results")
public void user_should_be_able_to_see_the_search_results() {
	 WebElement searchResult = driver.findElement(By.xpath("//span[text()='SEARCH']"));
	    String title=driver.getTitle();
	    if(title.equalsIgnoreCase("MakeMyTrip")) {
	    if (searchResult.isDisplayed()) {
	       
	    	System.out.println("Search page displayed as expected.");
	    	assertTrue(true);
	    	driver.close();
	    } else {
	        System.out.println("Search page not displayed as expected.");
	        assertTrue(false);

	    }
	    }
}

}
