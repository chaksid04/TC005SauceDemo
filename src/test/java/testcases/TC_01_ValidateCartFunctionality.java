package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;

public class TC_01_ValidateCartFunctionality extends TestBase {
	
	public TC_01_ValidateCartFunctionality() {
		super();
	}

	private WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	CheckoutPage checkoutPage;
	
	
	
	@BeforeClass
	public void setUp() {
		
		driver = getDriver();
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		checkoutPage = new CheckoutPage(driver);		
	}
	
	
	@Test(priority = 0)
	public void verifyLogin() {
		loginPage.validateLogin(prop.getProperty("username"),prop.getProperty("password"));
		
		Assert.assertTrue(homePage.verifyTitle(prop.getProperty("pagetitle")));
	}

	
	
	@Test(priority = 1)
	public void verifyCreateOrder() throws Exception {
		
		waitSafe(5000);
		homePage.selectAZOption(prop.getProperty("option"));
		
		waitSafe(5000);
		// Order - Sauce Labs Onesie
		homePage.addtoCart(prop.getProperty("orderitem"));
		
		// Order - Sauce Labs Bike Light
		homePage.addtoCart(prop.getProperty("orderitem2"));
		waitSafe(5000);
		Assert.assertEquals(homePage.verifycartAddSuccess(), true);
		
		homePage.clickCart();
		waitSafe(5000);
		
		
	}
	
	@Test(priority = 2)
	public void verifyCheckout() {
		
		if(checkoutPage.getcartCount()>1) {
			
			Assert.assertTrue(true, "Multiple items exists in Cart");
			waitSafe(5000);
		}	
	}
	
	
	@Test(priority = 3)
	public void verifyItemRemoveFromCart() {
		
		checkoutPage.removeFromCart(prop.getProperty("orderitem"));
		
		checkoutPage.removeFromCart(prop.getProperty("orderitem2"));
		waitSafe(5000);
		checkoutPage.clickContinueshopping();
		waitSafe(5000);
	}
	
	
	@Test(priority = 4)
	public void logoutTest() {
		waitSafe(5000);
		homePage.logout();
		
	}
	
	
}
