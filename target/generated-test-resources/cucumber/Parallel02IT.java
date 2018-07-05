import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"C:/Users/Zulimire/workspace/ReviewSessionCodes/src/test/resources/features/Etsysearch2.feature"},
        plugin = {"json:C:/Users/Zulimire/workspace/ReviewSessionCodes/target/cucumber-parallel/2.json"},
        monochrome = false,
        tags = {},
        glue = {"stepdefs"})
public class Parallel02IT {
}
