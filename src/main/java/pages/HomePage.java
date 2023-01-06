package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import base.TestBase;

public class HomePage extends TestBase {

	private WebDriver driver;
	private JavascriptExecutor js;

	// Constructor that will be automatically called as soon as the object of the
	// class is created
	public HomePage(WebDriver driver) {
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
	
	By menulogout = By.id("react-burger-menu-btn");
	
	By logoutlink = By.id("logout_sidebar_link");
	
	
	// Method to enter username

	public boolean verifyTitle(String title) {

		if (driver.getTitle().equals(title)) {
			return true;
		}

		else {
			return false;
		}
	}

	public void selectAZOption(String option) {

		WebElement azContain = driver.findElement(azcontainer);

		Select objselect = new Select(azContain);
		objselect.selectByVisibleText(option);
	}

	public void addtoCart(String orderitemName) {

		WebElement orderItem = driver.findElement(By.xpath("//div[contains(text(),'" + orderitemName
				+ "')]/ancestor::div[@class='inventory_item_description']/descendant::button[contains(text(),'Add to cart')]"));

		js.executeScript("arguments[0].click();", orderItem);
		// orderItem.click();
	}

	public boolean verifycartAddSuccess() {

		return driver.findElement(activeshoppingcart).isDisplayed();
	}

	public CheckoutPage clickCart() {

		WebElement cart = driver.findElement(shoppingcart);
		cart.click();
		return new CheckoutPage(driver);
	}
	
	public void logout() {
		
		driver.findElement(menulogout).click();
		waitSafe(2000);
		driver.findElement(logoutlink).click();
		
	}
}
