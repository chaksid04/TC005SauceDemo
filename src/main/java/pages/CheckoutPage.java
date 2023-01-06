package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.TestBase;

public class CheckoutPage extends TestBase {

	private WebDriver driver;
	private JavascriptExecutor js;

	// Constructor that will be automatically called as soon as the object of the
	// class is created
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		js = ((JavascriptExecutor) driver);
	}

	// Locator for homepageLogo field
	By homepageLogo = By.xpath("//span[contains(text(),'Products')]");

	// Locator for A-Z field
	By azcontainer = By.xpath("//select[@class='product_sort_container']");

	// Locator for login button
	By activeshoppingcart = By.xpath("//span[@class='shopping_cart_badge']");

	By shoppingcart = By.xpath("//a[@class='shopping_cart_link']");

	By cartItem = By.xpath("//div[@class='cart_item']");

	By continueshopping = By.id("continue-shopping");

	By checkout = By.xpath("//button[text()='Checkout']");

	By checkoutfname = By.id("first-name");

	By checkoutlname = By.id("last-name");

	By checkoutzip = By.id("postal-code");

	By checkoutcontinue = By.id("continue");

	By checkoutfinish = By.id("finish");

	By validatecheckoutfinish = By.xpath("//h2[contains(text(),'THANK YOU FOR YOUR ORDER')]");

	public int getcartCount() {

		int cartCount = driver.findElements(cartItem).size();

		return cartCount;

	}

	// Method to enter username

	public boolean verifyTitle(String title) {

		if (driver.getTitle().equals(title)) {
			return true;
		}

		else {
			return false;
		}
	}

	public void removeFromCart(String orderItemToRemove) {

		WebElement removeOrderItem = driver.findElement(By.xpath("//div[contains(text(),'" + orderItemToRemove
				+ "')]/ancestor::div[@class='cart_item_label']/descendant::button[contains(text(),'Remove')]"));
		
		js.executeScript("arguments[0].click();", removeOrderItem);
		// orderItem.click();
	}

	public void clickContinueshopping() {

		driver.findElement(continueshopping).click();

	}

	public void clickCheckout() {

		driver.findElement(checkout).click();
	}

	public void enterCheckoutDetails(String fname, String lname, String pcode) {

		waitSafe(3000);
		driver.findElement(checkoutfname).sendKeys(fname);
		driver.findElement(checkoutlname).sendKeys(lname);
		driver.findElement(checkoutzip).sendKeys(pcode);
		waitSafe(3000);

		driver.findElement(checkoutcontinue).click();
	}

	public boolean finishCheckout() {

		WebElement removeOrderItem = driver.findElement(checkoutfinish);

		js.executeScript("arguments[0].click();", removeOrderItem);
		waitSafe(3000);
		WebElement completeCheckoutConfirm = driver.findElement(validatecheckoutfinish);

		waitSafe(3000);
		return completeCheckoutConfirm.isDisplayed();
	}

}
