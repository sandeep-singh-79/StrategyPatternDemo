/**
 * 
 */
package com.demo.POM.strategy.pages;

import com.demo.POM.strategy.base.BasePageObject;
import groovy.util.ConfigObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * @author SANDEEP
 *
 */
public class PDPPage extends BasePageObject {
	
	@FindBy(css="#add_to_cart > button > span")
	private WebElement addToCart;
	
	@FindBy(id = "availability_value")
	private WebElement inStock;

	/**
	 * @param driver
	 */
	public PDPPage(WebDriver driver) {
		super(driver);
		
		PageFactory.initElements(driver, this);
	}

	/**
	 * @see BasePageObject#getUniqueElement()
	 */
	@Override
	protected By getUniqueElement() {
		return By.id("#idTab5");
	}
	

	/**
	 * @return
	 */
	public PDPPage addProductToCart() {
		while(true) {
			if (isInStock()) {
				addToCart.click();
				break;
			}
		}
		
		return this;
	}

	private boolean isInStock() {
		return (inStock.getText().equalsIgnoreCase("In Stock"));
	}
	
	
	/**
	 * The method navigates the user to the Cart page from the overlay displayed
	 * @return instance of Cart page
	 * @throws NoSuchElementException
	 */
	public Cart navigateToCart() throws NoSuchElementException {
		if(IsProductAddedToCart()) {
			// first reset the implicitwait to 0
			((WebDriver)driver).manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			// then use wait for a specific element using WebDriverWait
			WebElement proceedToCheckout = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector("#layer_cart a.btn-default")));
			// then set the implicitwait back to the required time.
			((WebDriver)driver).manage().timeouts().implicitlyWait(
					new Long(((ConfigObject)config).get("IMPLICITWAIT_TIMEOUT").toString()), TimeUnit.SECONDS);
			proceedToCheckout.click();

			return new Cart((WebDriver) driver);
		} else {
			throw new NoSuchElementException("Looks like the cart frame is not displayed.");
		}
	}
	
	private boolean IsProductAddedToCart() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String toCompare = jsExecutor.executeScript("return document.getElementsByTagName(\"H2\")[0].textContent;")
									.toString().trim();
		
		return(toCompare.equalsIgnoreCase("Product successfully added to your shopping cart"));
	}
}
