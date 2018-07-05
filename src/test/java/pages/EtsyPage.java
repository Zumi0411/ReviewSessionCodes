package pages;

import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EtsyPage {

	
	public EtsyPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy (id="search-query")
	public WebElement searchBox;
}
