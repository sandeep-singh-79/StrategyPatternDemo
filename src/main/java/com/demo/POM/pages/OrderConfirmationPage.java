package com.demo.POM.pages;

import com.demo.POM.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by SANDEEP on 1/6/2016.
 */
public class OrderConfirmationPage extends BasePageObject {
    @FindBy(css = ".box")
    private WebElement confirmationText;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    @Override
    protected By getUniqueElement() {
<<<<<<< HEAD
        By uniqueElement = By.cssSelector(".order-confirmation");
=======
        By uniqueElement = By.cssSelector("p.cheque-indent > strong");
>>>>>>> a81c8d39b9b6b6571037fded8d8135b7fbdfbdfb
        wait.until(ExpectedConditions.visibilityOfElementLocated(uniqueElement));
        return uniqueElement;
    }

    public boolean isOrderConfirmed() {
        return (confirmationText.getText().contains("Your order on My Store is complete."));
    }
}
