package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DateMapper;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ismail Koembe
 */
@Slf4j
public class InformationPage extends BasePage {

    @FindBy(xpath = "//span[normalize-space()='Departure airport']")
    public WebElement departure;

    @FindBy(id = "station-select-200-input")
    public WebElement departureInput;

    @FindBy(xpath = "(//span[@class='o-station-select__new-station-list__label-text'])[1]")
    public WebElement departureCountry;

    @FindBy(xpath = "//span[normalize-space()='Destination airport']")
    public WebElement destination;

    @FindBy(id = "station-select-201-input")
    public WebElement destinationInput;

    @FindBy(xpath = "(//span[@class='o-station-select__new-station-list__label-text'])[1]")
    public WebElement destinationCountry;

    @FindBy(id = "form-field-ofi5x88zn1n")
    public WebElement dateInput;

    @FindBy(xpath = "(//div[starts-with(@class,'m-fieldset')])[5]")
    public WebElement datePicker;

    @FindBy(xpath = "//input[starts-with( @name, 'ew-calendar-id')]")
    public List<WebElement> dates;

    @FindBy(xpath = "//h5[normalize-space()='Status of your searched flight']")
    public WebElement searchResultsTable;

    @FindBy(xpath = "//div[@class='o-search-flight-status__search-results']//li")
    public List<WebElement> dateNavigationList;

    @FindBy(xpath = "//p[@data-component-name='paragraph']")
    public List<WebElement> flightResultListAllElements;

    @FindBy(xpath = "//div[@class='o-card-component__section--no-padding o-card-component__content']")
    public List<WebElement> flightResultListRows;

    @FindBy(xpath = "//div[@class='o-search-flight-status__card-time-details']")
    public List<WebElement> flightStatusCardTimeDetails;

    @FindBy(xpath = "(//div[@class='o-search-flight-status__card-airports'])[1]")
    public List<WebElement> flightStatusCardAirportsDetails;

    @FindBy(xpath = "(//div[@class='o-search-flight-status__card-gate-information']")
    public List<WebElement> flightStatusCardGateInformation;

    public InformationPage(){
        PageFactory.initElements(driver, this);
    }


    public void clickInDatePickerWithText(String dateString){

        log.debug("Generate 7 days LocaleDate with relative date name");
        Map<String, LocalDate> localDateMap = DateMapper.dateMapper(-3, 4);

        log.debug("Map Web elements with their text values");
        Map<WebElement, Set<String>> elementDateCouples = dates.stream()
                .collect(Collectors.toMap(
                        element -> element,
                        element -> {
                            Set<String> valueSet = new HashSet<>();
                            valueSet.add(element.getAttribute("value"));
                            return valueSet;
                        }
                ));

        elementDateCouples.entrySet().stream()
                .filter(entry -> entry.getValue().contains(localDateMap.get(dateString).toString()))
                .findFirst()
                .ifPresent(entry -> entry.getKey().click());
        log.info("Click date from Date picker with given text {}",dateString );

    }

    public List<String> mapWebElementsToText(List<WebElement> elements){
        return elements.stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isInfoFlightResultInTheList(String query){

        return mapWebElementsToText(flightResultListAllElements)
                .stream().collect(Collectors.toSet()).contains(query);

    }
}
