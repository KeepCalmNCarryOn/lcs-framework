package portal.selenium.functional_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This page object represents the sign in page for the portal. 
 * @author twhyte
 *
 */
public class SignInPage extends Page{
    static String URL;

    /**
     * Check that the current page is the sign in page.  
     * @param driver
     */
	public SignInPage(){

		String currentLocation = Page.DRIVER.getCurrentUrl();
		if (!currentLocation.startsWith(URL)){
			throw new IllegalStateException("This is not the sign in page, current page is: " 
					+ currentLocation); 
		}
	}

	final By signInSubmitButtonLocator = By.id("signInSubmit");
	final By passwordFieldLocator = By.id("passwordid");
	final By usernameFieldLocator = By.id("emailid");
	final By loginErrorLocator = By.id("serverErroremail_id");

	public static void open(){
		String url = CONFIG.getSignInURL();
		DRIVER.get(url);
	}

	/**
	 * Enter a user name in the user name field.
	 * @param username
	 * @return The login page.
	 */
	public SignInPage enterUsername (String username) {
        WebDriverWait wait = new WebDriverWait (DRIVER, 5);

		wait.until (ExpectedConditions.presenceOfElementLocated(usernameFieldLocator))
		.sendKeys(username);

		return this;
	}

	/**
	 * Enter a password into the password field.
	 * @param password
	 * @return The login page.  
	 */
	public SignInPage enterPassword (String password) {
        WebDriverWait wait = new WebDriverWait (DRIVER, 5);

		wait.until (ExpectedConditions.presenceOfElementLocated(passwordFieldLocator))
		.sendKeys(password);

		return this;
	}

	/** 
	 * Enters a valid user name and password for the stack set up in the configuration.
	 * @return The sign in page.
	 */
	public SignInPage enterDefaultCredentials () {
        WebDriverWait wait = new WebDriverWait (DRIVER, 10);
        
		wait.until (ExpectedConditions.presenceOfElementLocated(usernameFieldLocator))
		.sendKeys(CONFIG.getUsername());
		wait.until (ExpectedConditions.presenceOfElementLocated(passwordFieldLocator))
		.sendKeys(CONFIG.getPassword());

		return this;
	}
	
	/**
	 * Allows the user to log in from the home page using the link in the navigation bar.   
	 * @return The home page. 
	 */
	public HomePage submitLoginFromHome() {
		click(signInSubmitButtonLocator);
		return new HomePage();
	}

	/**
	 * Allows the user to log in from the manage page using the link in the navigation bar.   
	 * @return The manage page. 
	 */
	public ManagePage submitLoginFromManage() {
		click(signInSubmitButtonLocator);
		return new ManagePage();
	}
	
	/**
	 * Allows the user to log in from the triggers page using the link in the navigation bar.   
	 * @return The triggers page. 
	 */
	public TriggersPage submitLoginFromTriggers() {
		clickThenWait(signInSubmitButtonLocator, signInSubmitButtonLocator);
		return new TriggersPage();
	}
	
	/**
	 * Allows the user to log in from the payoffs page using the link in the navigation bar.   
	 * @return The payoffs page. 
	 */
	public PayoffsPage submitLoginFromPayoffs() {
		click(signInSubmitButtonLocator);
		return new PayoffsPage();
	}
	
	/**
	 * Allows the user to attempt to log in but expects that the login process will fail. 
	 * @param initiator
	 * @return The login page.
	 */
	public SignInPage submitUnsuccessfulLogin() {
		click(signInSubmitButtonLocator);
		return this;
	}
	
	public boolean isLoginErrorDisplayed() {
		return isElementDisplayed(loginErrorLocator);
	}
}