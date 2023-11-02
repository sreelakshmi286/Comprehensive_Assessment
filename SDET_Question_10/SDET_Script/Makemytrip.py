# from telnetlib import EC

import pytest
from selenium import webdriver
from selenium.webdriver import Keys
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support import expected_conditions as EC


@pytest.fixture
def driver():
    # Set up Chrome WebDriver
    driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))
    yield driver
    driver.quit()


def test_makemytrip_flight_search(driver):
    # Open the MakeMyTrip website
    driver.get("https://www.makemytrip.com/")

    # Click on "Flights" tab
    wait= WebDriverWait(driver, 25)
    pop_up=wait.until(EC.element_to_be_clickable((By.XPATH, "//span[@data-cy='closeModal']")))
    # driver.find_element(By.XPATH("//span[@data-cy='closeModal']"))
    pop_up.click()
    flights_tab = wait.until(EC.element_to_be_clickable((By.XPATH, "//li[@data-cy='menu_Flights']")))
    flights_tab.click()

    # Select "Round Trip"
    round_trip_button = wait.until(EC.element_to_be_clickable((By.XPATH, "//li[@data-cy='roundTrip']")))
    round_trip_button.click()

    # Enter "FROM" location as "HYD"
    from_location_input = wait.until(EC.presence_of_element_located((By.ID, "fromCity")))
    # from_location_input.clear()
    from_location_input.send_keys("HYD")
    from_location_input.send_keys(Keys.RETURN)

    # Enter "TO" location as "MAA"
    to_location_input = wait.until(EC.presence_of_element_located((By.ID, "toCity")))
    # to_location_input.clear()
    to_location_input.send_keys("MAA")
    to_select_city = wait.until(EC.element_to_be_clickable((By.XPATH, "//p[text()='Chennai, India']")))
    to_select_city.click();

    # Select Departure Date (Assuming the date picker is visible)
    departure_date_xpath="//div[@aria-label='Sun Nov 19 2023']"
    departure_date_picker = wait.until(EC.element_to_be_clickable((By.XPATH, departure_date_xpath)))
    departure_date_picker.click()

    # TODO: Choose a departure date from the date picker

    # Select Return Date (Assuming the date picker is visible)
    return_date_XPATH="//div[@aria-label='Fri Dec 01 2023']"
    return_date_picker = wait.until(EC.element_to_be_clickable((By.XPATH, return_date_XPATH)))
    return_date_picker.click()

    # TODO: Choose a return date from the date picker

    # Click on the "Search" button
    search_button = wait.until(EC.presence_of_element_located((By.XPATH, "//a[text()='Search']")))
    search_button.click()

    # Wait for the search page to load (you can customize the timeout)
    wait.until(EC.title_contains("MakeMyTrip"))

    # Verify that the search page is displayed as expected
    assert "MakeMyTrip" in driver.title

    if __name__=="__main__":
        pytest.main()
