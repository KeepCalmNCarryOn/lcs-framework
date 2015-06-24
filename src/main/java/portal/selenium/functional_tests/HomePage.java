package portal.selenium.functional_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This page object represents the root or landing page for the portal.
 * @author twhyte
 *
 */
public class HomePage extends Page{
  
  /**
   * Creates a page object for the home page.  Use the {@link #open()} method
   * to navigate to the page. 
   * @param driver
   */
  public HomePage (WebDriver driver) {
	  super(driver);
  }
  
  @Override
  public HomePage open(){
	  DRIVER.get(CONFIG.getHomeURL());
	  return this;
  }
  
  /**
   * Locators 
   */
  By headerLoginLinkLocator = By.id("signInLink");
  By loginButtonLocator = By.className("sign-in");
  By signOutDropDownMenuLocator = By.id("dropdownMenu1");
    
  /**
   * Allow the user to go to the sign in page from the header.
   * @return The login page. 
   */
  public LoginPage startSignInFromHeader() {
	  new WebDriverWait (DRIVER, 5)
	  .until (ExpectedConditions.presenceOfElementLocated(headerLoginLinkLocator))
	  .click();

      return new LoginPage(DRIVER);  
  }
  
  /**
   * Allow the user to go to the sign in page from the button on the page.   
   */
  public LoginPage startSignInFromButton() {
	  new WebDriverWait (DRIVER, 5)
	  .until(ExpectedConditions.presenceOfElementLocated(loginButtonLocator))
	  .click();
	 return new LoginPage(DRIVER); 
  }

  /**
   * Determine whether or not the sign out drop down menu is present on the page. 
   * @return Whether the drop down menu is displayed.
   */
  public boolean isUserSignedIn(){
	  return isElementDisplayed(signOutDropDownMenuLocator);
  }
  

}