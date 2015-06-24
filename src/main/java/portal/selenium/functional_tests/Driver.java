package portal.selenium.functional_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * 
 * Provide a browser driver. 
 * @author Tonisha Whyte  
 *
 */
public class Driver {
	private WebDriver driver;
	
  public Driver(String browser){
			try {
				if (browser.equalsIgnoreCase("Firefox")) {
					driver = new FirefoxDriver();
				} else if (browser.equalsIgnoreCase("Chrome")) {
					driver = new ChromeDriver();
				} else if (browser.equalsIgnoreCase("IE")) {
					driver = new InternetExplorerDriver();
				} else if (browser.equalsIgnoreCase("Safari")){
					driver = new SafariDriver();
				}

			
			} catch (WebDriverException e) {
				System.out.println(e.getMessage());
			}
			
  } 
}
