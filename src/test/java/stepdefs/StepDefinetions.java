package stepdefs;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.Assert;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.EtsyPage;

public class StepDefinetions {

	    private WebDriver driver;
	    private EtsyPage etsyPage;
	    private String keyword;
	    
		@Given("^User in on Etsy homepage$")
		public void user_in_on_Etsy_homepage() throws Throwable {
            // WebDriverManager.chromedriver().setup();
            // driver=new ChromeDriver();
			DesiredCapabilities capabilities=DesiredCapabilities.chrome();
			capabilities.setPlatform(Platform.ANY);
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            //driver.manage().window().maximize();
             driver.get("https://www.etsy.com/");
             Assert.assertEquals("Etsy.com | Shop for anything from creative people everywhere",
            		        driver.getTitle());
		
		}

		@When("^User searches for \"([^\"]*)\"$")
		public void user_searches_for(String keyword) throws Throwable {
		    this.keyword=keyword;
		    etsyPage=new EtsyPage(driver);
		    etsyPage.searchBox.sendKeys(keyword+Keys.ENTER);
		    System.out.println("Searching for "+keyword);
		    
		}

		@Then("^Search results should be displayed$")
		public void search_results_should_be_displayed() throws Throwable {
		   Assert.assertTrue(driver.getTitle().toLowerCase().startsWith(keyword));
		   driver.close();
		}


	}


