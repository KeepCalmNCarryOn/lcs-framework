package portal.selenium.functional_tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import portal.selenium.functional_tests.Configuration.Stack;

/**
 * Test ability to sign in from all pages in the portal.
 * @author Tonisha Whyte
 *
 */
public class SignInTest {
	WebDriver driver;
    protected Configuration config;

    @Parameters ({ "browser" })
    @BeforeClass
	public void setup(String browser) {
    	config = new Configuration(Stack.DEV);
        Page.configure(config, browser);
	}
	
	@AfterClass
	public void cleanUp() {
		Page.DRIVER.quit();
	} 
	
	@AfterMethod
	public void signOut() {
	   HomePage.open();
	   HomePage home = new HomePage();

	   if (home.isTheSignOutOptionAvailable()){
		   home.signOut();
	   }
	}

	@Test
	public void logInSuccessfully_fromSignInButtonOnHomePageNavigationBar() {
	    HomePage.open();	
	    HomePage resultPage = new HomePage().selectSignInButtonFromNavBar()
	    		.enterDefaultCredentials().submitLoginFromHome();

	    // verify the user is signed in
	    Assert.assertTrue(resultPage.isTheSignOutOptionAvailable());
	   
	    // verify the username shown is the correct one
	    String username = resultPage.selectPersonIconFromNavBar().getUsernameFromDropDown();
	    Assert.assertEquals(username, config.getUsername());
	}

	@Test
	public void logInSuccessfully_whenUserNavigatesDirectlyToMyLinks() {
	    ManagePage.open();	
	    ManagePage resultPage = new ManagePage().selectSignInButtonFromNavBar()
	    		.enterDefaultCredentials().submitLoginFromManage();

	    // verify the user is signed in
        Assert.assertTrue(resultPage.isTheSignOutOptionAvailable());
	}

}
