package stepDefinitions;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utilities.Driver;
import utilities.Environments;
import utilities.PropManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * @author Ismail Koembe
 */
@Slf4j
public class Hooks{
    public final String env = Environments.PRODUCTION.name();
    public final WebDriver driver = Driver.get(env);
    public final Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofMillis(Long.parseLong(PropManager.getProperties(env,"duration"))))
            .pollingEvery(Duration.ofMillis(1000))
            .ignoring(NoSuchElementException.class);

    /**
     Hooks is used to run before and after each SCENARIO or SCENARIO OUTLINE
     */
    @Before
    public void setUpScenario(){
        log.info("Before Method");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
        driver.manage().window().maximize();
    }
    @After
    public void tearDownScenario(Scenario scenario){
        log.info("After Method");
        if (scenario.isFailed()) {
            log.info("capturing the screenshot when a sceraio fails and attaching it to the report");
            final byte[] failedScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(failedScreenshot, "image/png", "failed_scenario" + scenario.getName() + "");
        }
        Driver.closeDriver();
    }

    /**
     * This Before hooks ONLY RUNS for @smoke_test TAGGED SCENARIOS
     * */
    @Before("@smoke_tests")
    public void setUpSmokeScenarios(){
        System.out.println("RUN FOR ONLY SMOKE TEST SCENARIOS");
    }

    /**
     * This After hooks ONLY RUNS for @smoke_test TAGGED SCENARIOS
     * */
    @After("@smoke_tests")
    public void tearDownSmokeScenarios(){
        System.out.println("RUN FOR ONLY SMOKE TEST SCENARIOS");
    }


}

