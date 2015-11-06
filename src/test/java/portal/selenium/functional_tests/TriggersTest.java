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
	  protected Configuration config;
	  
        @Parameters ({ "browser" })
		@BeforeClass
		public void setup(String browser) {
        	Page.configure(new Configuration(Stack.DEV), browser);
        }

        @AfterClass
        public void cleanUp() {
        	Page.DRIVER.quit();
        }

	  @Test
	  public void list_valid_create_dates_in_triggers_view() {
		    SimpleDateFormat df = new SimpleDateFormat("MMM d,yyyy");

		    // sign in
		    TriggersPage.open();
	        TriggersPage triggers = new TriggersPage().selectSignInButtonFromNavBar()
	        		.enterDefaultCredentials().submitLoginFromTriggers();

	        boolean moreDatesToValidate = true;

	        // check all creation dates 
	        while (moreDatesToValidate){
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
	        } 
	  }
}
