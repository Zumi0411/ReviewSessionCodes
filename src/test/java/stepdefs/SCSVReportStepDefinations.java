package stepdefs;

import java.io.FileReader;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Map;
import java.util.List;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.Assert.assertEquals;

public class SCSVReportStepDefinations {
	WebDriver driver;
	String url="https://forms.zohopublic.com/murodil/report/Applicants/reportperma/DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8";
    Map <Integer, Map<String,String>> UIDataMap=new HashMap<>();
    Map<Integer, Map<String, String>> CSVDataMap = new HashMap<>();
	
@Given("^User is on Applicants report page$")
public void user_is_on_Applicants_report_page() throws Throwable {
	WebDriverManager.chromedriver().setup();
    driver=new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.get(url);
    driver.manage().window().maximize();
    
   }

@Given("^User can read applicants data from table$")
public void user_can_read_applicants_data_from_table() throws Throwable {
    Select perPage =new Select(driver.findElement(By.id("recPerPage")));
    perPage.selectByVisibleText("50");
    //perPage.selectByIndex(perPage.getOptions().size()-3);
    WebDriverWait wait=new WebDriverWait(driver, 40);
    wait.until(ExpectedConditions
    		.numberOfElementsToBe(By.xpath("//table[@id='reportTab']/tbody/tr")
    	    , 50));
    int totalRecords=Integer.parseInt(driver.findElement(By.id("total")).getText());
    for(int pageNum=1;pageNum<=totalRecords/50;pageNum++){
    	
   
    int rowsOnPage=driver.findElements(By.xpath("//table[@id='reportTab']/tbody/tr")).size();
    
    for(int row=1;row<  rowsOnPage;row++){
    	Integer empId=null;
    	Map<String, String> empMap=new HashMap<>();
    	
    	for(int cell=1;cell<=5;cell++){
    		String cellValue=driver.findElement(By.
    				xpath("//table[@id='reportTab']/tbody/tr["+row+"]/td["+cell+"]")).getText().trim();
    		
    		switch(cell) {
    		case 1:
    			empId=Integer.valueOf(cellValue);
    			break;
    		case 2:
    			empMap.put("Full name",cellValue);
    			break;
    		case 3:
    			empMap.put("Email",cellValue);
    			break;
    		case 4:
    			empMap.put("Phone",cellValue);
    			break;
    		case 5:
    			empMap.put("Current Annual Salary",cellValue);
    			break;	
    			
    			}
    		}
    	
    	UIDataMap.put(empId, empMap);
    	Thread.sleep(3000);
      }
    
    driver.findElement(By.id("showNext")).click();
  }
    
    System.out.println(UIDataMap.toString());
}

@Given("^cvs report is generated$")
public void cvs_report_is_generated() throws Throwable {
    CSVReader csvReader=new CSVReaderBuilder(new FileReader("CSV report.csv"))
    		                                    .withSkipLines(1).build();
    		                                 
    List<String[]> csvDataList=csvReader.readAll();
    for (String[] rowData : csvDataList) {
		Integer empID=null;
		Map<String, String> empMap=new HashMap<>();
		
		for(int cell=0;cell<rowData.length;cell++){
			String cellValue=rowData[cell];
			switch (cell) {
			case 0:
				empID=Integer.valueOf(cellValue);
				break;
			case 1:
				empMap.put("Full name", cellValue);
				break;
			case 2:
				empMap.put("Email", cellValue);
				break;
			case 3:
				empMap.put("Phone", cellValue);
				break;
			case 4:
				empMap.put("Current Annual Salary", cellValue);
				break;
			}
		}CSVDataMap.put(empID, empMap);
	}System.out.println("==========================");
	System.out.println(CSVDataMap.toString());
    
   
}

/*@Then("^data in ui and csv report should match$")
public void data_in_ui_and_csv_report_should_match() throws Throwable {
    
   for (Integer empID : CSVDataMap.keySet()) {
	   if (UIDataMap.containsKey(empID)) {
		assertEquals(CSVDataMap.get(empID),UIDataMap.get(empID));
	}else{
		System.out.println("Employee data is not found.");
	}
	
  }

 }*/


//  expected:<{Email=nugeybui@gmail.com, Phone=1691177696, Current Annual Salary=120993, Full name=}>
//       was:<{Email=nugeybui@gmail.com, Phone=1691177696, Current Annual Salary=120993, Full name= }>

//expected:<{Email=filizcamci@gmail.com, Phone=866876546, Current Annual Salary=135366, Full name=Rasheed, Mohr}> but 
//     was:<{Email=filizcamci@gmail.com, Phone=0866876546, Current Annual Salary=135366, Full name=Rasheed, Mohr}>


}
