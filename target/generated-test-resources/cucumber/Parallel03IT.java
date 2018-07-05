import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"C:/Users/Zulimire/workspace/ReviewSessionCodes/src/test/resources/features/Etsysearch3.feature"},
        plugin = {"json:C:/Users/Zulimire/workspace/ReviewSessionCodes/target/cucumber-parallel/3.json"},
        monochrome = false,
        tags = {},
        glue = {"stepdefs"})
public class Parallel03IT {
}
