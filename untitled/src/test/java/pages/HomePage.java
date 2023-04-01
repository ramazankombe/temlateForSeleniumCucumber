package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Ismail Koembe
 */
public class HomePage extends BasePage{
    @FindBy(xpath = "//a[contains(text(),'At the airport')]")
    public WebElement atTheAirport;

    @FindBy (xpath = "//span[@class='o-tab-container__flex-btn-wrapper'][normalize-space()='Flight status']")
    public WebElement flightStatusButton;

    @FindBy (xpath = "(//span[@class='a-cta__text'][normalize-space()='Show flight status'])[1]")
    public WebElement showFlightStatusButton;

    @FindBy (xpath = "(//span[starts-with(@id,'daterange-picker')])[1]")
    public WebElement dateRangePickerError;

    public HomePage(){
        PageFactory.initElements(driver, this);
    }

}
