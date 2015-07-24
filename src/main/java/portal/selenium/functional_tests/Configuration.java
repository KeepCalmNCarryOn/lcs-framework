package portal.selenium.functional_tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/** 
 * This class provides details of the test environment.  
 * Sets the URLs for each of the pages in the portal. 
 * @author Tonisha Whyte
 *
 */
public class Configuration {
	private final String username; 
	private final String password;
	private Map<String, String> urls;
	
    private static final String HOME_URL_KEY = "home";
	private static final String MANAGE_URL_KEY = "manage";
	private static final String TRIGGERS_URL_KEY = "triggers";
	private static final String PAYOFFS_URL_KEY = "payoffs";
    private static final String SIGNIN_URL_KEY = "login";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String URL_LIST_KEY = "urls"; 

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * Portal stacks.
	 * @author twhyte
	 *
	 */
	public enum Stack {
	    DEV("dev_stack"), 
	    PROD("prod_stack");	

	    private String value;

	    private Stack(String value){
	    	this.value = value;
	    }

	    public String toString() {
	    	return value;
	    }
	}

	/**
	 * Set user name, password and URLS for a specified portal stack.
	 * @param stack a portal stack.
	 */
	public Configuration (Stack stack) {
		 JSONParser configParser = new JSONParser();
		 JSONObject config = null; 
		 JSONObject portalStack = null;
		 

		  try {
			  config = (JSONObject)configParser.parse(new InputStreamReader(
					  new FileInputStream("src/main/config/config.json"), "UTF-8"));
			  portalStack = (JSONObject)config.get(stack.toString());
		  } catch (IOException e) {
			  e.printStackTrace();
		  } catch (ParseException e) {
			  e.printStackTrace();
		  }

		  this.username = (String)portalStack.get(USERNAME_KEY);
		  this.password = (String)portalStack.get(PASSWORD_KEY);
		  this.urls = (Map<String, String>)portalStack.get(URL_LIST_KEY);
 
		  // tell each page in the portal its url
		  HomePage.URL = getHomeURL();
		  SignInPage.URL = getSignInURL();
		  ManagePage.URL = getManageURL();
		  PayoffsPage.URL = getPayoffsURL();
		  TriggersPage.URL = getTriggersURL();
		  
	}
	
	public String getHomeURL(){
		return (String)urls.get(HOME_URL_KEY);
	}

	public String getSignInURL(){
		return (String)urls.get(SIGNIN_URL_KEY);
	}

	public String getManageURL() {
		return (String)urls.get(MANAGE_URL_KEY);
	}

	public String getTriggersURL() {
		return (String)urls.get(TRIGGERS_URL_KEY);
	}
	
	public String getPayoffsURL() {
		return (String)urls.get(PAYOFFS_URL_KEY);
	}

	/**
	 * @param browser The name of the browser whose driver is returned.
	 * @return
	 */
	public WebDriver getDriver(String browser){
		WebDriver driver = null;
			try {
				if (browser.equalsIgnoreCase("Firefox")) {
					driver = new FirefoxDriver();
				} else if (browser.equalsIgnoreCase("Chrome")) {
					driver = new ChromeDriver();
				} else if (browser.equalsIgnoreCase("IE")) {
					driver = new InternetExplorerDriver();
				} else if (browser.equalsIgnoreCase("Safari")){
					driver = new SafariDriver();
				} else {
					throw new IllegalArgumentException();
				}
			} catch (WebDriverException e) {
				System.out.println(e.getMessage());
			}
			return driver;
	  }
}