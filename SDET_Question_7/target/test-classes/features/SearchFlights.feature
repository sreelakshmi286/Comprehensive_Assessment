Feature: Search flights

  Scenario: user should be able to search the flights when user enters the details
    Given ChromeDriver must be openend
    Then user have to enter in to the MakeMyTrip page
    Then user needs to click on flights
    Then user need to select from and to details
    Then user need to select the departure and return date
    Then user need to click search button
    Then user should be able to see the search results
