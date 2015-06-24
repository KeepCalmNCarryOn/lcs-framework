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
 * Tests for the payoff view.   
 * @author Tonisha Whyte
 *
 */
public class PayoffsTest {
	WebDriver driver;
	protected Configuration config;

    @Parameters ({ "browser" })
	@BeforeClass
//	public void setup() {
	public void setup(String browser) {
		config = new Configuration(Stack.DEV);
		driver = config.getDriver(browser);
//		driver = config.getDriver("safari");

	}

	@AfterClass
	public void tearDown(){
		driver.quit();
	}

    // TODO Create a defect:  Unresponsive script https://d3lxp2916bkdsd.cloudfront.net/dev/shared.js:2 on Firefox. 
    // This could be a problem with Selenium.
	@Test(groups = {"brokenOnFirefox"})
	public void payoffs_displaysValidCreateDates() {
		SimpleDateFormat df = new SimpleDateFormat("MMM d,yyyy");

		// verify that sign in succeeds
		LoginPage login = new LoginPage(driver);
		login.open()
		.enterCredentials()
		.submitLogin(new HomePage(driver));
		

		// go to payoffs page
		PayoffsPage payoffs = new PayoffsPage(driver).open();
		boolean moreDatesToValidate = true;

		// check all creation dates 
		do{
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
		} while (moreDatesToValidate);
	}
}