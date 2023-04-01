@interviewTask
Feature: Verify that flight routes with different travel dates

  Background:
    Given Go to home page
    And I understand Privacy Setting if it appears

  Scenario: Verify flight routes with different travel dates
    When I navigate to "Information"
    And I click "At the airport"
    Then I land on correct url "https://www.eurowings.com/en/information/at-the-airport.html"
    And I click "Enter flight number or route"
    Then I land on correct url "https://www.eurowings.com/en/information/at-the-airport/flight-status.html"
    And I scroll down until element visible
    And I enter "CGN" as the departure airport
    And I enter "BER" as the destination airport
    And I select the travel date
      |today|
      |tomorrow|
      |yesterday|
      |day-3|
      |day3|
    Then I should see flights selected travel date
    Then I should see "Status of your searched flight" table with results
    And I wait "100000" ms
    Then I should see destination "CGN" to "BER" on the selected travel date

  @Negative
  Scenario: Search for a flight status with all fields left blank
    When I click Flight status
    And I click Show flight status
    Then I see "Please enter a date that is between today and" error message
    Then I land on correct url "https://www.eurowings.com/en.html"





