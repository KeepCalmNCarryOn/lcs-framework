package portal.selenium.functional_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import portal.selenium.functional_tests.Configuration.Stack;

/**
 * The base class for portal page objects. 
 * @author twhyte
 *
 */
public abstract class Page {
    protected final WebDriver DRIVER;
    protected final Configuration CONFIG;
    
    /**
     * Creates a page object with a specified web driver and by default uses the 
     * dev stack configuration.
     * @param driver
     */
    public Page (WebDriver driver){
        this.DRIVER = driver;
        CONFIG = new Configuration(Stack.DEV);
    }
    
    /**
     * Creates a page object.
     * @param driver
     * @param stack The configuration stack.
     */
    public Page (WebDriver driver, Stack stack) {
        this.DRIVER = driver;
        CONFIG = new Configuration(stack);
    }
   
    /** 
     * Navigate to this page using the web driver. 
     */
    public abstract Page open();

    /**
     * Find out whether an element
     */
    protected boolean isElementDisplayed(By locator){
    	boolean displayed;
    	try {
    		displayed = new WebDriverWait(DRIVER, 10)
    		.until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed();
    	}catch (TimeoutException e){
    	    displayed = false;	
    	}

    	return displayed; 
    }
    
    /** 
     * Click an element on the page after waiting for it to load. 
     * @param locator
     */
    protected void click(By locator) {
    	WebElement element = new WebDriverWait(DRIVER, 10)
    	.until(ExpectedConditions.presenceOfElementLocated(locator));
        int retries = 5; 
    	
        // TODO:  Find a better way to wait for an element that isn't ready to be clicked. 
    	while (retries > 0){
    		try{
    		    element.click();
    		    break;
    		}catch (WebDriverException e) {
    			retries--;
    		}
    	}

    }
}