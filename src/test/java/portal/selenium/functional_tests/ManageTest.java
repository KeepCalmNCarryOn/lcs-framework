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
  public void manage_page_displaysValidCreateDates() {
	    SimpleDateFormat df = new SimpleDateFormat("MMM d,yyyy");

	    // sign in from the manage page
	    ManagePage.open();
	    ManagePage manage = new ManagePage();
	    manage.selectSignInButtonFromNavBar().enterDefaultCredentials()
        .submitLoginFromManage();
        
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