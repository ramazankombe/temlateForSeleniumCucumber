package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author Ismail Koembe
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json",
                "html:target/default-html-reports",
                "rerun:target/rerun.txt"},
        features = "src/test/resources/features/",
        monochrome=true,
        glue = "stepDefinitions",
        tags = "@All",
        dryRun = false

)
public class Runner {
}
