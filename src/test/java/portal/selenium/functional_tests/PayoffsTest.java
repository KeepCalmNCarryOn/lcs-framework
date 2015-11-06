package portal.selenium.functional_tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import portal.selenium.functional_tests.Configuration.Stack;

/**
 * Tests for the payoff page.   
 * @author Tonisha Whyte
 *
 */
public class PayoffsTest {

    @Parameters ({ "browser" })
	@BeforeClass
	public void setup(String browser) {
    	Page.configure(new Configuration(Stack.DEV), browser);
	}

	@AfterClass
	public void tearDown(){
		Page.DRIVER.quit();
	}

    // TODO Create a defect:  Unresponsive script https://d3lxp2916bkdsd.cloudfront.net/dev/shared.js:2 on Firefox. 
    // This could be a problem with Selenium.
	@Test(groups = {"brokenOnFirefox"})
	public void valid_create_dates_in_payoffs_view() {
		SimpleDateFormat df = new SimpleDateFormat("MMM d,yyyy");

		// sign in on the payoffs page
		PayoffsPage.open();
		PayoffsPage payoffs = new PayoffsPage().selectSignInButtonFromNavBar()
				.enterDefaultCredentials().submitLoginFromPayoffs();

		boolean moreDatesToValidate = true;

		// check all creation dates 
		while (moreDatesToValidate){
			List<String> creationDates = payoffs.getCreateDates();

			for (String date : creationDates){
				try {
					df.parse(date);
				} catch (ParseException e) {
					Assert.fail();
				}
			}

			if (payoffs.isNextButtonAvailable()){
				payoffs.next();
			} else {
				moreDatesToValidate = false;
			}
		}
	}
}
