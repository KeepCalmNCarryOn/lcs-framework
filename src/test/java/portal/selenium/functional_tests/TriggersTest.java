package portal.selenium.functional_tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import portal.selenium.functional_tests.Configuration.Stack;

/**
 *  Tests for the triggers page. 
 * @author Tonisha Whyte
 *
 */
public class TriggersTest {
	  WebDriver driver;
	  protected Configuration config;
	  
        @Parameters ({ "browser" })
		@BeforeClass
		public void setup(String browser) {
		//	driver = new ChromeDriver();
	        config = new Configuration(Stack.DEV);
            driver = config.getDriver(browser);
		}

        @AfterClass
        public void cleanUp() {
    		driver.quit();
    	}
	  @Test
	  public void triggers_displaysValidCreateDates() {
		    SimpleDateFormat df = new SimpleDateFormat("MMM d,yyyy");

		    // sign in
		    SignInPage login = new SignInPage(driver);
	        login.open()
	        .enterDefaultCredentials()
	        .submitLogin(new HomePage(driver));

	        // go to triggers page
	        TriggersPage triggers = new TriggersPage(driver).open();
	        boolean moreDatesToValidate = true;

	        // check all creation dates 
	        do{
	        	List<String> creationDates = triggers.getCreateDates();

	        	for (String date : creationDates){
	        		try {
	        			df.parse(date);
	        		} catch (ParseException e) {
	        			Assert.fail();
	        		}
	        	}

	        	if (triggers.isNextButtonAvailable()){
	        		triggers.next();
	        	} else {
	        		moreDatesToValidate = false;
	        	}
	        } while (moreDatesToValidate);
	  }
}
