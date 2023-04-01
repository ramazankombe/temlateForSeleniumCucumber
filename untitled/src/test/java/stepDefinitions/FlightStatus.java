package stepDefinitions;

import lombok.extern.slf4j.Slf4j;
import utilities.DateMapper;
import utilities.PropManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.InformationPage;

import java.util.List;

/**
 * @author Ismail Koembe
 */
@Slf4j
public class FlightStatus {
    private final HomePage homePage = new HomePage();
    private final InformationPage informationPage = new InformationPage();

    @Given("Go to home page")
    public void goToHomePage() {
        homePage.driver.get(PropManager.getProperties(homePage.env, "url"));
    }


    @Given("I understand Privacy Setting if it appears")
    public void i_understand_privacy_setting_if_it_appears() {
        homePage.waitAndClick(homePage.privacySettingConsent);
    }


    @When("Click Signin button at the top")
    public void click_signin_button_at_the_top() {
        homePage.waitAndClick(homePage.information);

    }


    @When("I navigate to {string}")
    public void i_navigate_to(String tabName) {
        homePage.clickOnTab(tabName);
    }


    @And("I wait {string} ms")
    public void i_wait_ms(String millisec) throws InterruptedException {
        homePage.sleep(Integer.parseInt(millisec));
    }

    @When("I click {string}")
    public void clickWebElementWithGivenText(String text) {
        homePage.clickWebElement(text);
    }

    @Then("I land on correct url {string}")
    public void i_land_on_correct_url(String expectedUrl) {
        Assert.assertEquals(informationPage.driver.getCurrentUrl(), expectedUrl,
                "Actual and expected urls should match");

    }


    @And("I enter {string} as the departure airport")
    public void iEnterAsTheDepartureAirport(String departureAirport) {
        informationPage.waitAndClick(informationPage.departure);
        informationPage.sendKey(informationPage.departureInput, departureAirport);
        informationPage.waitAndClick(informationPage.departureCountry);

    }

    @And("I enter {string} as the destination airport")
    public void iEnterAsTheDestinationAirport(String destinationAirport) {
        informationPage.waitAndClick(informationPage.destination);
        informationPage.sendKey(informationPage.destinationInput, destinationAirport);
        informationPage.waitAndClick(informationPage.destinationCountry);
    }

    @And("I scroll down until element visible")
    public void iScrollDownUntilElementVisible() {
        informationPage.scrollDownUntilElementVisible(informationPage.departure);
    }

    @And("I select the travel date")
    public void iSelectTheTravelDate(List<String> dates) {
        dates.forEach(date -> {
                    informationPage.waitAndClick(informationPage.datePicker);
                    informationPage.clickInDatePickerWithText(date);
                    informationPage.clickWebElement("Show flight status");
                    iShouldSeeFlightsSelectedTravelDate();
                    log.info("Asserting flights on {}", date);
                }
        );

    }


    @Then("I should see flights selected travel date")
    public void iShouldSeeFlightsSelectedTravelDate() {
        informationPage.scrollDownUntilElementVisible(informationPage.searchResultsTable);
        String formattedActualDate = DateMapper.dateFormatter(informationPage.datePicker.getAttribute("eventmodelvalue"));
        Assert.assertTrue(informationPage.mapWebElementsToText(informationPage.dateNavigationList)
                .contains(formattedActualDate));
    }

    @Then("I should see {string} table with results")
    public void iShouldSeeTable(String text) {
        Assert.assertEquals(informationPage.getElementText(informationPage.searchResultsTable), text,
                "Search Result table should be visible");
        Assert.assertTrue(informationPage.flightResultListRows.size()>0,
                "Flight result list rows ");
    }


    @Then("I should see destination {string} to {string} on the selected travel date")
    public void iShouldSeeDestinationToOnTheSelectedTravelDate(String from, String to) {
        Assert.assertTrue(informationPage.isInfoFlightResultInTheList(from),
                "Departure airport should be unique in the result list");
        Assert.assertTrue(informationPage.isInfoFlightResultInTheList(to),
                "Destination airport should be unique in the result list");
    }

    @When("I click Flight status")
    public void iClickFlightStatus() {
        homePage.waitAndClick(homePage.flightStatusButton);
    }

    @When("I click Show flight status")
    public void iClickShowFlightStatus() {
        homePage.waitAndClick(homePage.showFlightStatusButton);
    }

    @Then("I see {string} error message")
    public void iSeeErrorMessage(String message) {
        String elementText = homePage.getElementText(homePage.dateRangePickerError);
        log.info("Element text : {}", elementText);
        Assert.assertTrue(elementText.contains(message));
    }
}
