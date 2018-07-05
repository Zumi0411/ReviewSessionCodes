package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import gherkin.formatter.model.Feature;

	@RunWith(Cucumber.class)
	@CucumberOptions(
			dryRun=false,
			features="src/test/resources/features/",
			glue="stepdefs",
			tags= "@csvReportDataValidation"
			)
	

public class CukesRunner {

}
	
	