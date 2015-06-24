package portal.selenium.functional_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This page object represents the sign in page for the portal. 
 * @author twhyte
 *
 */
public class LoginPage extends Page{
    
    /**
     * Navigate to the page where the user can log in to the dev stack.
     * @param driver
     */
	public LoginPage(WebDriver driver){
		  super(driver);
	}

	@Override
	public LoginPage open(){
		  String url = CONFIG.getLoginURL();
		  DRIVER.get(url);
		  return this;
	}
	
	final By signInSubmitButtonLocator = By.id("signInSubmit");
	final By passwordFieldLocator = By.id("passwordid");
	final By usernameFieldLocator = By.id("emailid");
	final By loginErrorLocator = By.id("serverErroremail_id");

	/**
	 * Enter a username intot the username field.
	 * @param username
	 * @return The login page.
	 */
	public LoginPage enterUsername (String username) {
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
	public LoginPage enterPassword (String password) {
        WebDriverWait wait = new WebDriverWait (DRIVER, 5);

		wait.until (ExpectedConditions.presenceOfElementLocated(passwordFieldLocator))
		.sendKeys(password);

		return this;
	}

	/** 
	 * Enters a valid username and password for the stack set up in the configuration.
	 * @return The login page.
	 */
	public LoginPage enterCredentials () {
        WebDriverWait wait = new WebDriverWait (DRIVER, 10);
        
		wait.until (ExpectedConditions.presenceOfElementLocated(usernameFieldLocator))
		.sendKeys(CONFIG.getUsername());
		wait.until (ExpectedConditions.presenceOfElementLocated(passwordFieldLocator))
		.sendKeys(CONFIG.getPassword());

		return this;
	}
	
	/**
	 * Allows the user to log in from the home page using the link in the header.   
	 * @param resultPage The page the user expects when login succeeds. 
	 * @return A page object representing the page where the user started the login process.
	 */
	public Page submitLogin(Page resultPage) {
		new WebDriverWait (DRIVER, 5)
		.until (ExpectedConditions.presenceOfElementLocated(signInSubmitButtonLocator))
		.click();
		return resultPage;
	}

	/**
	 * Allows the user to attempt to log in but expects that the login process will fail. 
	 * @param initiator
	 * @return The login page.
	 */
	public LoginPage submitUnsuccessfulLogin() {
		new WebDriverWait (DRIVER, 5)
		.until (ExpectedConditions.presenceOfElementLocated(signInSubmitButtonLocator))
		.click();
		return this;
	}
	
	public boolean isLoginErrorDisplayed() {
		return isElementDisplayed(loginErrorLocator);
	}
}