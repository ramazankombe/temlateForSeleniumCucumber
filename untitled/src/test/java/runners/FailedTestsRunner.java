package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author Ismail Koembe
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/failed-html-reports"},
        features = "@target/rerun.txt",
        glue = "step_definitions"
)
public class FailedTestsRunner {

}
