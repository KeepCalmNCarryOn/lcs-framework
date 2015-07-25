package portal.selenium.functional_tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This page object represents the triggers page for the portal.
 * @author twhyte
 *
 */
public class PayoffsPage extends Page {
	static String URL;

	/**
	 * Check that the payoffs page is open.
	 */
	public PayoffsPage () {
		String currentLocation = Page.DRIVER.getCurrentUrl();
		if (!currentLocation.startsWith(URL)){
			throw new IllegalStateException("This is not the payoffs page, current page is: " 
					+ currentLocation); 
		}
	}

	/**
	 * Open the payoffs page.
	 */
	public static void open() {
		DRIVER.get(CONFIG.getPayoffsURL());
	}

	By emptyListLocator = By.id("empty-payoff-list");

	By linkCreateDateLocator = By
			.xpath("//strong[contains(., 'Created')]/.."); 

	By nextArrowLocator = By.cssSelector("ul.pager i.fa-caret-right");
	By nextArrowDisabledLocator = By.cssSelector("ul.pager li.disabled i.fa-caret-right");

	By pageTitleLocator = By.partialLinkText("Mobile Experiences");

	/**
	 * Get the list of displayed creation dates
	 * @return All creation dates
	 */
	public  List<String> getCreateDates(){
		List<String> dates = new ArrayList<String>();	  

		if (!isListEmpty()){

			List<WebElement> createDates = new WebDriverWait(DRIVER, 5)
			.until(ExpectedConditions.presenceOfAllElementsLocatedBy(linkCreateDateLocator));

			for (WebElement element : createDates){

				// Take off the label leaving just the date
				String regex = "\\s*\\bCreated\\b:\\s*";
				dates.add(element.getText().replaceAll(regex, ""));
			}
		}
		return dates; 
	}

	/**
	 * Check if there are any content in the list.   
	 * 
	 * @return True if the list is empty and false otherwise.  Also false if the current
	 * page doesn't display the list of content. 
	 */
	public boolean isListEmpty() {
		boolean emptyList = false;
		if (!isElementDisplayed(pageTitleLocator)){
			emptyList = true;
		}

		try {
			DRIVER.findElement(emptyListLocator);
		}catch(NoSuchElementException e) {
			emptyList = true; 
		}
		return emptyList;
	}

	/** 
	 * Use the next arrow to move forward in the list of content.
	 * @return The manage page.
	 */
	public PayoffsPage next(){
		click(nextArrowLocator);
		return this;
	}

	/**
	 * Check whether the button to view more links is enabled. 
	 * @return
	 */
	public boolean isNextButtonAvailable() {
		try {
			DRIVER.findElement(nextArrowDisabledLocator);
		}catch(NoSuchElementException e){
			return true; 
		}
		return false;
	}
}
