package portal.selenium.functional_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.webdriven.commands.Open;

/**
 * The root or landing page for the portal.
 * @author Tonisha Whyte
 *
 */
public class HomePage extends Page{
	//TODO need to enforce that each page has a URL (what's a good interface name)?
	static String URL;

  /**
   * Check that the current page is the home page.  
   */
  public HomePage () {
	  
	  String currentLocation = Page.DRIVER.getCurrentUrl();
	  if (!currentLocation.startsWith(HomePage.URL)){
		  throw new IllegalStateException("This is not the home page, current page is: " 
				  + currentLocation); 
	  }
  }


  /**
   * Locators 
   */
  By loginButtonLocator = By.className("sign-in");
    
  public static void open(){
	  DRIVER.get(URL);
  }

  /**
   * Allow the user to go to the sign in page from the button on the page.   
   * @return The sign in page 
   */
  public SignInPage signInFromButton() {
	  new WebDriverWait (Page.DRIVER, 5)
	  .until(ExpectedConditions.presenceOfElementLocated(loginButtonLocator))
	  .click();
	 return new SignInPage(); 
  }

}