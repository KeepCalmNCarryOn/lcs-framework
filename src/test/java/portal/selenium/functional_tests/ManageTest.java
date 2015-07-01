package portal.selenium.functional_tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import portal.selenium.functional_tests.Configuration.Stack;

/** 
 * Test the manage tab.
 * @author Tonisha Whyte
 *
 */
public class ManageTest {
	WebDriver driver;
    protected Configuration config;

    @Parameters ({ "browser" })
    @BeforeClass
	public void setup(String browser) {
        config = new Configuration(Stack.DEV);
        driver = config.getDriver(browser);
	}
	
	@AfterClass
	public void cleanUp() {
		driver.quit();
	}

  @Test
  public void manage_page_displaysValidCreateDates() {
	    SimpleDateFormat df = new SimpleDateFormat("MMM d,yyyy");

	    // sign in
	    ManagePage.open();
	    ManagePage manage = new ManagePage();
	    manage.signInFromNavigationBar().enterDefaultCredentials()
        .submitLoginFromManage();
        
        // go to manage page
        ManagePage manage = new ManagePage(driver).open();
        boolean moreDatesToValidate = true;

        // check all creation dates match the appropriate format 
        while (moreDatesToValidate) {
        	List<String> creationDates = manage.getCreateDates();

        	for (String date : creationDates){
        		try {
        			df.parse(date);
        		} catch (ParseException e) {
        			Assert.fail();
        		}
        	}

        	if (manage.isNextButtonAvailable()){
        		manage.next();
        	} else {
        		moreDatesToValidate = false;
        	}
        }
  }
}