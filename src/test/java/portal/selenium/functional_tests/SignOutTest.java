package portal.selenium.functional_tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import portal.selenium.functional_tests.Configuration.Stack;

public class SignOutTest {
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
	
	@BeforeMethod
	public void signIn() {
		SignInPage.open();
		SignInPage page = new SignInPage().enterDefaultCredentials().
	}
}
