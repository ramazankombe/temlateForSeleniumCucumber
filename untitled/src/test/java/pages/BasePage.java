package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import stepDefinitions.Hooks;

/**
 * @author Ismail Koembe
 */

@Slf4j
public abstract class BasePage extends Hooks {

    @FindBy(xpath = "//span[normalize-space()='Information']")
    public WebElement information;

    @FindBy(xpath = "//span[normalize-space()='I understand']")
    public WebElement privacySettingConsent;

    public BasePage() {
        PageFactory.initElements(driver, this);
    }


    public void waitAndClick(WebElement element){
        long start = System.currentTimeMillis();
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        log.info("Clicked in {}ms", System.currentTimeMillis() - start);
    }


    public void sleep(int millisec) throws InterruptedException {
        Thread.sleep(millisec);
    }

    public void sendKey(WebElement element, String input){
        long start = System.currentTimeMillis();
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(input);
        log.info("Clicked in {}ms", System.currentTimeMillis() - start);
    }

    public boolean isDisplayed(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    public void clickOnTab(String tabName){
        WebElement element = driver.findElement(By.xpath("//span[normalize-space()='"+tabName+"']"));
        log.debug("xpat {}","/span[normalize-space()='"+tabName+"']" );
        waitAndClick(element);
    }

    public void scrollDownUntilElementVisible(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (!element.isDisplayed()) {
            js.executeScript("window.scrollBy(0,250)", "");
        }
    }

    public void clickWebElement(String textName){
       WebElement element = driver.findElement(By.xpath("//*[contains(text(),'"+textName+"')]"));
       waitAndClick(element);

    }

    public String getElementText(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();

    }

}
